# AGENTS.md

This file provides guidance to AI assistants (Claude Code, and any tool that reads
`AGENTS.md`) when working in this repository. It is the single source of truth for project
documentation; [CLAUDE.md](CLAUDE.md) points here.

## Project Overview

`nilsjr.github.io` is a personal portfolio website built entirely in **Kotlin** using
**Kotlin Multiplatform (JS target)** and **JetBrains Compose for Web** (Compose HTML).
The whole UI is written in Kotlin and compiled to JavaScript via the Kotlin/JS IR
compiler, then bundled with webpack. It doubles as a showcase for building static web
pages with Compose. The site is published to GitHub Pages at https://nilsjr.github.io/.

There is **no application/business logic and no tests** — it is a purely presentational
single-page frontend. (`./gradlew build` still runs the standard `test`/`check` tasks,
but no test sources exist.)

## Common Commands

```bash
# Run the dev server with hot reload (http://localhost:8080)
./gradlew jsBrowserDevelopmentRun

# Production build → outputs to build/dist/ (JS bundle, source map, index.html, assets)
./gradlew jsBrowserProductionWebpack moveExecutable

# Static analysis + lint (detekt rules + ktlint wrapper)
./gradlew detekt ktlintCheck

# Auto-format Kotlin sources in place (ktlint wrapper with autoCorrect)
./gradlew ktlintFormat

# Full build (compile + check); matches CI's build job
./gradlew build

# Regenerate .kotlin-js-store/yarn.lock after JS dependency changes
./gradlew kotlinUpgradeYarnLock

# Report available dependency updates
./gradlew dependencyUpdates
```

Requires JDK 21 (CI uses Temurin/Zulu 21). The Gradle wrapper (`./gradlew`) is committed.

## Architecture

Standard Compose HTML rendering pattern. All Kotlin sources live under
`src/jsMain/kotlin/`; static assets and the HTML shell under `src/jsMain/resources/`.

- **Entry point:** `Main.kt` — `main()` calls `renderComposable(rootElementId = "root")`,
  mounting Compose into `<div id="root">` from `index.html`. It manages dark-mode state
  with `remember { mutableStateOf(...) }`, persists it to `browser.localStorage` under the
  key `nilsjr.darkmode` via a `LaunchedEffect`, and wraps the page in a `Div` whose CSS
  class switches between `Style.dark` / `Style.light`.
- **Page orchestration:** `de.nilsdruyen.portfolio.WebPage.kt` — the top-level `page()`
  composable that stacks all sections: `placeholder() → title() → aboutMe() → work() →
  projects() → contributions() → footer() → placeholder()`.
- **Components:** `de.nilsdruyen.portfolio.components` — one composable per UI section/piece
  (`AboutMe`, `Work`, `Projects`, `Project`, `Contributions`, `Footer`, `Title`,
  `ProfileImage`, `GridRow`, `Placeholder`). Composable function names are lowercase
  (e.g. `aboutMe()`), which detekt is configured to allow.
- **Models:** `de.nilsdruyen.portfolio.model` — plain immutable data classes for content
  (`Data`, `Project`, `Work`, `Experiment`, `Interest`, `ProfileLink`). Static content
  data lives here, not fetched at runtime.
- **UI utilities:** `de.nilsdruyen.portfolio.ui` — `Colors`, `Constants`, `CssExt` (CSS
  helper extensions), `Icons` (inline SVG), and `Style` (a Compose `StyleSheet` defining
  global styles plus light/dark variants).
- **HTML shell:** `src/jsMain/resources/index.html` — loads JetBrains Mono and Rubik Dirt
  Google Fonts, the favicon, and the compiled `nils.github.io.js` bundle.
- **Assets:** `src/jsMain/resources/assets/` — images and SVGs. The `moveAssets` Gradle
  task copies these into `build/dist/assets` during the production build.

## Tech Stack

- **Kotlin 2.4.0** (Multiplatform, JS/IR target, compiling to `es2015`)
- **JetBrains Compose for Web 1.11.1** — `compose.runtime`, `compose.html.core`,
  `compose.html.svg`
- **kotlinx-coroutines** and **kotlinx-datetime** (declared in `commonMain`)
- **detekt 2.0.0-alpha.5** with the ktlint-wrapper ruleset for static analysis/formatting
- **Gradle Versions Plugin** for dependency-update reporting
- **webpack** (dev server + production bundling, versions pinned in the version catalog)
- Dependencies are managed via a **Gradle version catalog** at `gradle/libs.versions.toml`

## Build Configuration Notes

- `build.gradle.kts` holds the project `version` (format `YYYY.MINOR.PATCH`, currently
  `2026.4.0`). The scheduled-deploy workflow bumps this automatically.
- Yarn resolutions and pinned webpack/karma/mocha versions are configured in
  `build.gradle.kts` to keep transitive JS deps patched; the lockfile lives in
  `.kotlin-js-store/yarn.lock`.
- `webpack.config.d/devServerConfig.js` enables `historyApiFallback` for the dev server.
- Gradle config cache, build cache, and parallel execution are enabled in
  `gradle.properties`.

## Code Style

- **2-space indentation**, enforced by ktlint via detekt (`detekt-formatting.yml`).
- **No wildcard imports**; imports must be ordered.
- **No final newline** at end of files (`FinalNewline.insertFinalNewLine: false`).
- `MaximumLineLength` is disabled in the ktlint config (no hard line-length failure), but
  keep lines reasonable (~120 chars).
- `@Composable` functions are exempt from standard function-naming rules — they use
  lowercase camelCase names.
- `MagicNumber` and `NewLineAtEndOfFile` detekt rules are disabled (`detekt.yml`).
- `kotlin.code.style=official`.
- Config files: `detekt.yml` (analysis rules) and `detekt-formatting.yml` (ktlint
  wrapper). Run `./gradlew ktlintFormat` before committing.

## Git Workflow

This project follows **Gitflow**:

- `main` — production-ready code only; deployed to GitHub Pages. Updated via PRs from
  `develop` (typically the scheduled monthly release).
- `develop` — integration branch; all feature/fix branches target this.
- `feature/<description>` — new features, branched from `develop`.
- `fix/<description>` — bug or security fixes, branched from `develop`.
- `hotfix/<description>` — urgent fixes branched from `main`, merged back to both `main`
  and `develop`.

**Always open PRs targeting `develop`, not `main`** (except hotfixes).

### Commit Messages

Use **Conventional Commits**: `<type>(<scope>): <short summary>`. Common types: `feat`,
`fix`, `chore`, `docs`, `refactor`, `ci`, `build`. Examples:

- `feat(ui): add dark mode toggle`
- `fix(deps): upgrade webpack-dev-server to resolve CVE-2025-30359`
- `chore(deps): bump kotlin to 2.4.0`

Dependency updates are automated via **Renovate** (`renovate.json5`).

## CI/CD

GitHub Actions workflows in `.github/workflows/` (all run on JDK 21):

- **`check-and-build.yml`** — runs `detekt ktlintCheck` and `./gradlew build` plus a
  Gradle dependency-graph submission. Triggers on pushes to `develop`, PRs to `main`/
  `develop`, and the `pr_created_by_automation` repository dispatch.
- **`publish.yml`** — builds production artifacts
  (`jsBrowserProductionWebpack moveExecutable`) and, on push to `main`, deploys
  `build/dist` to the `gh-pages` branch (GitHub Pages).
- **`scheduled-deployment.yml`** — runs on the 1st of each month (and manual dispatch);
  if `develop` is ahead of `main`, it bumps the version in `build.gradle.kts`, pushes to
  `develop`, and opens (or updates) an auto-merge `develop → main` release PR.
- **`update-yarn-lock.yml`** — on Renovate PRs touching `build.gradle.kts`, regenerates
  `.kotlin-js-store/yarn.lock` (`kotlinUpgradeYarnLock`) and commits it back.

## Versioning

Versions follow `YYYY.MINOR.PATCH` and are defined by the `version` property in
`build.gradle.kts`. The scheduled workflow resets to `<year>.0.0` when the year rolls
over, otherwise increments the minor component.
