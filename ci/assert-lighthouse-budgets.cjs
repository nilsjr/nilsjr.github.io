const { execSync } = require('child_process');
const fs = require('fs');
const path = require('path');

const OUT_DIR = 'docs/lh-reports';
const OUT = path.join(OUT_DIR, 'lh-mobile.report.json');

fs.mkdirSync(OUT_DIR, { recursive: true });

execSync(
  `npx lighthouse http://127.0.0.1:8137/ --output=json --output-path=${OUT} ` +
    '--only-categories=performance,accessibility,best-practices,seo ' +
    '--chrome-flags="--headless=new --no-sandbox"',
  { stdio: 'inherit' },
);

const report = JSON.parse(fs.readFileSync(OUT, 'utf8'));

const categoryBudgets = {
  performance: 0.85,
  accessibility: 1.0,
  'best-practices': 1.0,
  seo: 0.95,
};

let failed = false;

for (const [category, minScore] of Object.entries(categoryBudgets)) {
  const score = report.categories[category].score;
  const status = score >= minScore ? 'OK' : 'FAIL';
  console.log(`[${status}] ${category}: ${score} (budget: >= ${minScore})`);
  if (score < minScore) failed = true;
}

const tbt = report.audits['total-blocking-time'].numericValue;
const tbtBudget = 350;
const tbtStatus = tbt <= tbtBudget ? 'OK' : 'FAIL';
console.log(`[${tbtStatus}] total-blocking-time: ${tbt}ms (budget: <= ${tbtBudget}ms)`);
if (tbt > tbtBudget) failed = true;

if (failed) {
  console.error('\nLighthouse budget check failed.');
  process.exit(1);
}
console.log('\nAll Lighthouse budgets passed.');
