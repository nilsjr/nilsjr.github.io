const { execSync } = require('child_process');
const fs = require('fs');
const path = require('path');

const OUT_DIR = 'docs/lh-reports';
const RUNS = 3;
const MIN_SUCCESSFUL_RUNS = 2;

fs.mkdirSync(OUT_DIR, { recursive: true });

function median(values) {
  const sorted = [...values].sort((a, b) => a - b);
  const mid = Math.floor(sorted.length / 2);
  return sorted.length % 2 === 0 ? (sorted[mid - 1] + sorted[mid]) / 2 : sorted[mid];
}

// Lighthouse's simulated CPU/network throttling multiplier is highly sensitive to the
// host machine (see docs/lighthouse-audit-2026-08.md). On GitHub-hosted runners the
// performance score and TBT swing wildly from run to run and read far worse than on a
// real machine or production. So: run several times and take the median, then treat the
// deterministic categories (a11y / best-practices / SEO) as hard gates and the
// throttling-sensitive performance metrics as warn-only signal.
const reports = [];
for (let i = 1; i <= RUNS; i++) {
  const out = path.join(OUT_DIR, `lh-mobile.${i}.report.json`);
  try {
    execSync(
      `npx lighthouse http://127.0.0.1:8137/ --output=json --output-path="${out}" ` +
        '--only-categories=performance,accessibility,best-practices,seo ' +
        '--chrome-flags="--headless=new --no-sandbox"',
      { stdio: 'inherit' },
    );
    reports.push(JSON.parse(fs.readFileSync(out, 'utf8')));
  } catch (err) {
    console.warn(`Lighthouse run ${i}/${RUNS} failed: ${err.message}`);
  }
}

if (reports.length < MIN_SUCCESSFUL_RUNS) {
  console.error(
    `\nOnly ${reports.length}/${RUNS} Lighthouse runs succeeded (need >= ${MIN_SUCCESSFUL_RUNS}).`,
  );
  process.exit(1);
}

// Keep the last successful run at the historical path for anything that still looks for
// it. It is one representative run, not the median.
fs.writeFileSync(
  path.join(OUT_DIR, 'lh-mobile.report.json'),
  JSON.stringify(reports[reports.length - 1]),
);

// Each metric's median is taken independently, so printed values may come from different
// runs — fine here, since the hard categories are deterministic and the rest is warn-only.
function categoryScore(category) {
  const scores = reports.map((r) => r.categories[category].score);
  if (scores.some((s) => typeof s !== 'number' || !Number.isFinite(s))) {
    console.error(`\nLighthouse returned no score for category "${category}" (category errored).`);
    process.exit(1);
  }
  return median(scores);
}

const tbtValues = reports.map((r) => r.audits['total-blocking-time'].numericValue);
const tbt = tbtValues.every((v) => typeof v === 'number' && Number.isFinite(v))
  ? median(tbtValues)
  : NaN;

// Hard budgets — deterministic audits, a real regression here should block the PR.
const hardBudgets = {
  accessibility: 1.0,
  'best-practices': 1.0,
  seo: 0.95,
};

// Soft budgets — reported for visibility, never fail the build (runner-dependent).
const softBudgets = {
  performance: 0.85,
};
const tbtBudget = 350;

let failed = false;

console.log(`Median of ${reports.length} Lighthouse runs:\n`);

for (const [category, minScore] of Object.entries(hardBudgets)) {
  const value = categoryScore(category);
  const ok = value >= minScore;
  console.log(`[${ok ? 'OK' : 'FAIL'}] ${category}: ${value} (budget: >= ${minScore})`);
  if (!ok) failed = true;
}

console.log('');

for (const [category, minScore] of Object.entries(softBudgets)) {
  const value = categoryScore(category);
  const ok = value >= minScore;
  console.log(`[${ok ? 'OK' : 'WARN'}] ${category}: ${value} (target: >= ${minScore}, not enforced)`);
}

const tbtOk = tbt <= tbtBudget;
console.log(
  `[${tbtOk ? 'OK' : 'WARN'}] total-blocking-time: ${tbt}ms (target: <= ${tbtBudget}ms, not enforced)`,
);

if (failed) {
  console.error('\nLighthouse budget check failed (hard budgets).');
  process.exit(1);
}
console.log('\nAll enforced Lighthouse budgets passed.');
