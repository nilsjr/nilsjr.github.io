# Lighthouse Audit & Improvement Plan — August 2026

Audit of the deployed portfolio (`gh-pages` @ `9741edb`, built from `main` @ `026f241`)
with Lighthouse 13.4.1 / Chrome 141, plus a prioritised plan for what to change.

## TL;DR

| | Mobile | Desktop |
| --- | --- | --- |
| **Performance** | **0.72** | 0.99 |
| **Accessibility** | **0.87** | 0.87 |
| **Best Practices** | 0.96 \* | 0.96 \* |
| **SEO** | **0.91** | 0.91 |

\* The only failing best-practices audit is `errors-in-console`, and all three console
errors are artefacts of the sandboxed audit environment (see
[Methodology](#methodology)). On the real deployment this category is expected to be 1.00.

Desktop is essentially perfect. **Every real problem is a mobile problem**, and it comes
from two independent causes:

1. **The background animations burn ~6 s of mobile main-thread time.** Turning them off
   takes main-thread work from 7.1 s → 1.0 s and TBT from 770 ms → 230 ms.
2. **Nothing paints until the 174 KB Kotlin/JS bundle has downloaded and evaluated.**
   FCP is 0.6 s (the flat background colour), but LCP is 3.5 s — the entire content of
   the page is produced by Compose after bundle evaluation.

Neither is fixed by the other, and both are fixable without changing how the site looks.

## Methodology

The audit could not be run against the live URL or a locally-compiled build: this
session's egress policy returns `403` for both `https://nilsjr.github.io` and
`dl.google.com` (Google Maven), so `./gradlew jsBrowserDistribution` cannot resolve
`androidx.compose.runtime:runtime`. Instead the **deployed production artifact** was
taken from the `gh-pages` branch and served locally from a static server that mirrors
GitHub Pages' response headers (gzip for text, `cache-control: max-age=600`).

- URL under test: `http://127.0.0.1:8137/` (byte-identical to production)
- Mobile: Lighthouse default throttling (Slow 4G, 4× CPU slowdown)
- Desktop: `--preset=desktop` (no CPU throttling)

**Known environment artefacts — not site defects:**

| Symptom | Cause |
| --- | --- |
| `ERR_CERT_AUTHORITY_INVALID` on `api.github.com` (×2) | Headless Chromium does not trust the sandbox's TLS-inspecting proxy CA |
| `ERR_CONNECTION_RESET` on `fonts.googleapis.com` | Same proxy, connection dropped after 12.9 s |

Consequence: the page rendered with its hardcoded fallback repo lists and a fallback
monospace font. Layout and content volume are effectively identical, so the metrics
remain representative — but treat "3 console errors" as noise.

## Metrics (mobile)

| Metric | Value | Score |
| --- | --- | --- |
| First Contentful Paint | 0.6 s | 1.00 |
| Largest Contentful Paint | 3.5 s | 0.64 |
| Total Blocking Time | 770 ms | 0.38 |
| Cumulative Layout Shift | 0 | 1.00 |
| Speed Index | 2.5 s | 0.98 |
| Time to Interactive | 3.5 s | 0.92 |
| Main-thread work | 7.1 s | 0 |
| JS bootup time | 2.6 s | 0 |

Long tasks: 7, the largest **611 ms**, all attributed to `nils.github.io.js`.
Bundle: 586 KB raw → **174 KB gzipped**; `unused-javascript` reports ~89 KB unused.

## Experiments

Two prototypes were measured to attribute the cost, rather than guessing.

| Run | Perf | LCP | TBT | Main-thread |
| --- | --- | --- | --- | --- |
| Production as-is | 0.72 | 3.5 s | 770 ms | 7.1 s |
| `--force-prefers-reduced-motion` (rain + terminal disabled) | **0.89** | 3.2 s | **230 ms** | **1.0 s** |
| Static hero markup injected into `index.html` | **0.99** | **0.6 s** | 110 ms | 4.1 s |

Reading these together matters:

- The reduced-motion run isolates the **animation cost**: ~6 s of main-thread work and
  ~540 ms of blocking time, on a page where the animations are pure decoration.
- The prerender run isolates the **render-architecture cost**: LCP collapses from 3.5 s
  to 0.6 s.
- The prerender run's TBT of 110 ms is **misleading**. Main-thread work is still 4.1 s —
  the rain keeps running, it just runs *after* TTI, outside the window TBT measures.
  Prerendering alone would buy a 0.99 Lighthouse score while leaving real users with the
  same battery drain, scroll jank and input latency. Do both fixes, and don't let the
  score talk you out of the second one.

## Findings

### P0 — Animations dominate the mobile main thread

`CodeRain` (`components/CodeRain.kt`) repaints a full-viewport canvas every 40 ms:
a `fillRect` over the whole DPR-scaled surface plus one `fillText` per column, forever,
regardless of screen size, device class or whether the tab is even visible.

`MiniTerminal` (`components/MiniTerminal.kt`) runs an infinite `LaunchedEffect` that
mutates Compose state roughly every 55 ms during typing, causing a recomposition and DOM
write each time. It is **hidden below 1120px by CSS only** — on every phone the loop
still runs, still recomposes, still writes to the DOM, for a widget nobody can see.

### P0 — Client-only rendering puts LCP behind the bundle

`index.html` ships an empty `<div id="root">`. All content — headings, career, repos,
contact — exists only after `nils.github.io.js` (174 KB gzipped) downloads, parses and
evaluates, then Compose builds the DOM. On Slow 4G + 4× CPU that is ~3 s of blank
terminal-coloured screen. The staggered `rise(delayMs)` entrance animations (up to 780 ms
of `animation-delay` with `opacity: 0`) add to it.

This also affects SEO and link previews: `view-source` contains no content at all.

### P1 — Accessibility: 22 contrast failures

| Colour | Used for | On `#0E0D12` | On card `#121117` | Needed |
| --- | --- | --- | --- | --- |
| `Muted` `#6B6580` | card labels, timeline meta | 3.50 | 3.40 | 4.5 |
| `Purple` `#7F52FF` | repo names (normal-size text) | 4.20 | 4.07 | 4.5 |

Suggested replacements, keeping the palette's character:

- `Muted` → **`#8A83A3`** (5.24 on card background, still clearly below `Body #A9A3BC`)
- add a separate **`PurpleText = #A78BFF`** (6.96) for text, leaving `#7F52FF` for
  borders, glows and the code rain where the 3:1 non-text threshold applies

### P1 — Accessibility: no landmarks, no heading structure

`WebPage.kt` renders everything into plain `Div`s. There is no `<main>`, and every
section label (`$ ls ~/open-source`, `$ cat stack.kt`, …) is a `Div`, so the document has
exactly one heading (`<h1>`) and no landmarks. A screen-reader user gets a flat wall of
text with nothing to navigate by. Fixing this changes no pixels:

- wrap the content container in `Main`
- render each card label as `H2` (the `cardLabel` class already supplies all styling)
- mark the rain canvas `aria-hidden="true"`

### P1 — SEO: no meta description, no social metadata

`meta-description` fails. There is also no `og:*`/`twitter:*` metadata, no canonical URL,
no `robots.txt` and no `sitemap.xml`. For a portfolio that gets shared on LinkedIn and
Twitter, the missing OG image and description are the costly ones.

### P2 — GitHub API fan-out at runtime

`data/GitHubRepos.kt` issues, on every page view:

1. `GET /users/nilsjr/repos?per_page=100` — full repo objects for every repo, only to
   filter by the `portfolio` topic
2. `GET /search/issues?q=author:nilsjr+is:pr+is:merged&per_page=100`
3. **one `GET /repos/{owner}/{name}` per distinct external repo** found in (2)

That is `2 + N` unauthenticated requests per visitor. The unauthenticated GitHub API
allows 60 requests/hour per IP (search: 10/minute), so a handful of page views from one
network exhausts the budget and every visitor after that silently gets the hardcoded
fallback lists — while still paying the network and main-thread JSON-parse cost.

Better: resolve the lists at build time (or in a scheduled workflow) into a small JSON
asset shipped with the site. Runtime API calls drop to zero, the data is still current,
and the fallback lists stop being load-bearing.

### P2 — Images

- `kodee-greetings.gif` is 59 KB for a 56×56 rendering (`image-delivery` savings 24 KB) —
  animated WebP, or one of the existing Kodee SVGs
- five stack icons have no `width`/`height` attributes (`unsized-images`); their CSS sizes
  are fixed, so add the matching intrinsic attributes
- `favicon-32x32.png` is 8 KB for 32×32, and is the only icon — no SVG favicon, no
  `apple-touch-icon`, no `theme-color`

### P2 — `prefers-reduced-motion` override is incomplete

`TerminalStyle.init` overrides `animation-duration` and `animation-iteration-count` but
not `animation-delay`. Under reduced motion the footer still waits 780 ms at `opacity: 0`
before appearing. Add `animation-delay: 0.01ms !important` and
`transition-duration: 0.01ms !important` to that block.

### Not actionable

- **`cache-insight` (211 KB)** — GitHub Pages fixes `cache-control: max-age=600` and
  offers no way to change it. Only relevant if the site ever moves behind a CDN.
- **`legacy-javascript` (10 KB)** — `Array.prototype.fill`, `Math.clz32` and friends come
  from the Kotlin/JS stdlib, not from application code.
- **`forced-reflow` (48 ms)** — inside the compiled bundle; not worth chasing at this size.

## Plan

Ordered by value per unit of effort. Each step is independently shippable.

### Step 1 — Stop paying for invisible animation (P0, small)

1. Don't compose `miniTerminal()` at all below 1120px: read the breakpoint with
   `window.matchMedia` into Compose state and gate the call, so the coroutine never
   starts on phones. Keep the CSS rule as a belt-and-braces measure.
2. Pause `CodeRain` on `document.visibilitychange` — cancel the rAF when hidden, restart
   when visible.
3. Scale the rain down on small/low-power screens: fewer columns (raise `COLUMN_WIDTH`),
   a longer `FRAME_INTERVAL_MS`, and cap the DPR at 2 in `setSize()`.

Expected: TBT 770 ms → ~250 ms, main-thread ~7 s → ~1.5 s, mobile performance ≈ 0.88.

### Step 2 — Paint the hero before the bundle (P0, medium)

Put a static copy of the hero block (the `// who am i` line, the `<h1>`, the first
paragraph) directly into `src/jsMain/resources/index.html`, styled by a small inline
`<style>` so it needs no external CSS. On mount, remove that node before/as Compose
renders — Compose HTML has no hydration, so the honest approach is a swap, not a merge.

Also drop `rise()`'s delay for the hero specifically (or start the sequence at 0 ms) so
the first meaningful paint is never behind an `animation-delay`.

Expected: LCP 3.5 s → ~0.7 s, mobile performance ≈ 0.99 once combined with Step 1.
Bonus: real content in `view-source` for crawlers and link unfurlers.

### Step 3 — Accessibility pass (P1, small)

1. `Colors.Muted` → `#8A83A3`; add `Colors.PurpleText = #A78BFF` and use it for
   `repoName` and any other normal-size purple text.
2. `Main` wrapper in `WebPage.kt`; `H2` for every `cardLabel`; `aria-hidden` on the canvas.
3. Complete the reduced-motion override (`animation-delay`, `transition-duration`).

Expected: accessibility 0.87 → 1.00.

### Step 4 — SEO & social metadata (P1, small)

Add to `index.html`: `<meta name="description">`, canonical link, `og:title` /
`og:description` / `og:image` / `og:url` / `og:type`, `twitter:card=summary_large_image`,
`theme-color`. Add `robots.txt` and a one-URL `sitemap.xml` to
`src/jsMain/resources/`. Produce a 1200×630 OG image (the terminal header over the rain
would do nicely).

Expected: SEO 0.91 → 1.00, and shared links stop rendering as a bare URL.

### Step 5 — Build-time GitHub data (P2, medium)

Replace the runtime fan-out with a generated `assets/repos.json`:

- a scheduled GitHub Action (weekly) runs the two queries *with* `GITHUB_TOKEN`, writes
  the trimmed JSON, commits it
- `GitHubRepos.kt` fetches that one same-origin file; keep the current hardcoded lists as
  the failure path

Expected: `2 + N` cross-origin requests → 1 same-origin request of a few KB, no rate
limiting, less main-thread JSON parsing.

### Step 6 — Asset polish (P2, small)

`kodee-greetings.gif` → animated WebP or SVG; intrinsic `width`/`height` on the five stack
icons; SVG favicon + `apple-touch-icon`.

### Step 7 — Keep it measured (P2, small)

Add a Lighthouse CI job to `check-and-build.yml` that builds
`jsBrowserDistribution`, serves it, and asserts mobile budgets (e.g. performance ≥ 0.90,
accessibility = 1.00, TBT ≤ 300 ms). Without this, Step 1 quietly regresses the first
time a new animation lands.

## Re-audit — 2026-08-22 (real production build)

The original audit above could not build the project or reach the live site (sandboxed
egress). This session's environment has working network access, so the audit was redone
against **an actual local production build** rather than an inference from the deployed
artifact:

```bash
./gradlew jsBrowserDistribution   # → build/dist/js/productionExecutable/
# served locally with a small Node static server replicating GitHub Pages headers
# (gzip for text assets, cache-control: max-age=600) — plain `python -m http.server`
# does not gzip and gives misleadingly slow numbers, see caveat below
```

| | Mobile | Desktop |
| --- | --- | --- |
| **Performance** | **0.88** | 0.99 |
| **Accessibility** | 0.87 | 0.87 |
| **Best Practices** | **1.00** | 1.00 |
| **SEO** | 0.91 | 0.91 |

With real network access, `errors-in-console` passes outright (the two `api.github.com`
calls and the Google Fonts request all succeed for real), confirming the original audit's
best-practices asterisk.

**Accessibility and SEO findings reproduce exactly**: same `color-contrast` failure (card
labels/muted text and repo-name purple), same missing `<main>` landmark, same missing
`meta-description`. The Step 3/4 fixes above are still the right ones.

**Performance is real but smaller than the original estimate, and the animation-cost
finding does not reproduce here.** Re-running the same `--force-prefers-reduced-motion`
experiment against this build:

| Run | Perf | LCP | TBT | Main-thread |
| --- | --- | --- | --- | --- |
| Production as-is | 0.88 | 3.1 s | 290 ms | 1.6 s |
| `--force-prefers-reduced-motion` | 0.86 | 3.0 s | 270 ms | **1.1 s** |

Turning the animations off only saves ~0.5 s of main-thread time here, not the ~6 s the
original audit measured on the deployed `gh-pages` artifact. That's too large a gap to be
noise. Since this run's other numbers line up closely with the original where they should
(unused-JS is 89 KB in both, gzip bundle is 174 KB in both, the same three a11y/SEO
audits fail identically), the likely explanation is that Lighthouse's simulated
CPU-throttling multiplier is sensitive to the host machine, not that the original
animation-cost finding was wrong — **treat the P0 "animations dominate the main thread"
finding as unverified in absolute terms, but keep Step 1 anyway**: pausing `CodeRain` on
`visibilitychange` and gating `miniTerminal()` out of composition below 1120px cost
nothing and stop real, measured waste (a fully hidden coroutine still ticking on every
phone) regardless of how big the number reads on any given machine.

**The GitHub API fan-out is confirmed for real, not just theorized.** With network access
working, the trace shows the full `2 + N` sequence firing exactly as documented:
`GET /users/nilsjr/repos`, `GET /search/issues?...`, then one `GET /repos/{owner}/{name}`
per external contribution (6 in the current fallback/live list — Flocon, Maestro,
azure-gradle-plugins, postman-code-generators, android-livetemplates, rollbar-java). Step
5 remains the fix; this just moves it from "inferred from code reading" to "measured on
the wire."

Reports: `docs/lh-reports/lh-mobile.report.html` and `lh-desktop.report.html` (git-ignored
scratch output, not committed).

## Implementation — 2026-08-22

All 7 plan steps above have been implemented and verified against a real local
production build (same gzip/cache-control setup as the re-audit above).

| | Mobile | Desktop |
| --- | --- | --- |
| **Performance** | 0.88 → **0.93** | 0.99 → **0.99** |
| **Accessibility** | 0.87 → **1.00** | 0.87 → **1.00** |
| **Best Practices** | 1.00 → **1.00** | 1.00 → **1.00** |
| **SEO** | 0.91 → **1.00** | 0.91 → **1.00** |

| Metric | Before | After |
| --- | --- | --- |
| LCP (mobile) | 3.1 s | **2.5 s** |
| TBT (mobile) | 290 ms | **240 ms** |
| CLS (mobile / desktop) | 0 / 0 | **0.025 / 0.072** |

`color-contrast`, `landmark-one-main`, and `meta-description` all now pass. The
`ci/assert-lighthouse-budgets.cjs` check from Step 7 passes against this build
(performance 0.95, accessibility/best-practices 1.00, SEO 1.00, TBT 192ms — one more
run, slightly different from the table above due to normal run-to-run variance in
Lighthouse's simulated throttling).

**One accepted trade-off**: the static-hero swap (Step 2) introduced a small CLS where
there was previously an exact 0 — 0.025 mobile / 0.072 desktop, both comfortably inside
Google's "good" (<0.1) band. Root cause: the real `<h1>`/hero paragraph, once swapped in
for the static markup, can render with a very slightly different box (a web-font
FOUT/FOIT reflow, or a sub-pixel difference between the static markup's plain CSS and
Compose's generated classes) than what was on screen a moment before. This is an inherent
limitation of a hydration-less prerender-then-swap technique, not a bug in the swap logic
itself — it was investigated by reproducing it twice and inspecting Lighthouse's
`layout-shifts` audit detail, and traded deliberately against eliminating ~2.4s of prior
LCP wait (nothing painted at all until the JS bundle evaluated). Chasing it fully to zero
would mean either giving up the immediate-opacity hero (reintroducing a flash-to-blank
relative to the static content) or hand-tuning the static markup's font metrics to match
Compose's output pixel-for-pixel under every font-load timing — not worth it at this
margin.

Deliberate deviations from the plan's literal wording, and why:
- The static prerender (Step 2) also mirrors the header dots row, not just the hero text,
  so the swap doesn't visibly push content down by the header's height.
- Step 2 removes the hero/header `rise()` fade-in entirely (not just its delay) so the
  real Compose content appears at full opacity the instant it swaps in, matching the
  already-visible static content with no flash. The hero's *second* paragraph (not
  covered by the static markup) keeps a `rise()` call specifically to hide the font-swap
  reflow described above — every other section's stagger is untouched.
- Step 5's scheduled data refresh pushes straight to `develop` with the default
  `GITHUB_TOKEN` (mirroring the existing version-bump workflow's pattern), not through a
  PR — so freshly-fetched repo data reaches production on the existing monthly release
  cadence, not instantly. Given the goal was eliminating client-side API fan-out risk, not
  guaranteeing real-time freshness, this was judged an acceptable fit with how this repo
  already ships changes.

## Reproducing

```bash
# Serve the deployed artifact exactly as GitHub Pages does
git fetch origin gh-pages && git archive FETCH_HEAD | tar -x -C ./site
# ...serve ./site with gzip + cache-control: max-age=600 on :8137

npx lighthouse http://127.0.0.1:8137/ --output=html --output-path=./lh-mobile.html \
  --only-categories=performance,accessibility,best-practices,seo

npx lighthouse http://127.0.0.1:8137/ --preset=desktop --output=html \
  --output-path=./lh-desktop.html \
  --only-categories=performance,accessibility,best-practices,seo
```
