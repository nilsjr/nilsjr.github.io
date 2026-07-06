---
name: verify
description: Build, serve, and visually verify the Compose HTML site in a headless browser.
---

# Verifying nilsjr.github.io

1. Build: `.\gradlew.bat build` (also runs detekt/ktlint via `check`). The fresh
   production bundle lands in `build/dist/js/productionExecutable/` — the files at the
   `build/dist/` **root** are stale leftovers from an old `moveExecutable` run; don't
   serve those.
2. Serve: `python -m http.server 8137 --directory build/dist` (background), page at
   `http://localhost:8137/js/productionExecutable/`.
3. Drive: plain `msedge --headless=new --screenshot` freezes CSS animations at initial
   paint (even with `--virtual-time-budget`), so the staggered entrance animations make
   the page look empty. Instead `npm i puppeteer-core` in the scratchpad and launch Edge
   (`C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe`) via
   `puppeteer.launch({ executablePath, headless: 'new' })`, `goto` + real `setTimeout`
   wait ~3s, then screenshot.
4. Worth checking: console/pageerror messages (should be clean), canvas rain actually
   painting (`getImageData` lit-pixel count > 0), 420px-wide viewport (single-column
   layout), and `page.emulateMediaFeatures` with `prefers-reduced-motion: reduce`.
