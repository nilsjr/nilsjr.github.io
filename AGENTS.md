# Project Analysis: nilsjr.github.io

This project is a personal portfolio website built with **Kotlin Multiplatform (JS target)** and **Compose for Web**. The entire UI is written in Kotlin and compiled to JavaScript via the IR compiler.

## Common Commands

```bash
# Development server (http://localhost:8080)
./gradlew jsBrowserDevelopmentRun

# Production build (outputs to build/dist/)
./gradlew jsBrowserProductionWebpack moveExecutable

# Lint check (detekt + ktlint)
./gradlew detekt ktlintCheck

# Auto-format code
./gradlew ktlintFormat

# Update yarn.lock after dependency changes
./gradlew kotlinUpgradeYarnLock

# Check for dependency updates
./gradlew dependencyUpdates
```

There are no tests in this project — it is a purely presentational frontend with no business logic.

## Code Style

- 2-space indentation (enforced by ktlint via detekt)
- Max line length: 120 characters
- No wildcard imports
- `@Composable` functions are exempt from standard Kotlin naming conventions (detekt is configured to allow this)
- Config lives in `detekt.yml` and `detekt-formatting.yml`

## Architecture

The app follows a straightforward Compose HTML pattern:

- **Entry point:** `src/jsMain/kotlin/Main.kt` — mounts the Compose runtime to `<div id="root">` in `index.html`
- **Page orchestration:** `WebPage.kt` contains the top-level `page()` composable that composes all sections
- **Components:** `de.nilsdruyen.portfolio.components` — one file per UI section (AboutMe, Work, Projects, etc.)
- **Models:** `de.nilsdruyen.portfolio.model` — plain data classes for content
- **UI utilities:** `de.nilsdruyen.portfolio.ui` — colors, constants, CSS extensions, icons, global styles
- **State:** Dark mode preference persisted via `browser.localStorage`

## Technologies

- **Kotlin**: Primary programming language.
- **JetBrains Compose for Web**: Specifically `compose.html.core` and `compose.html.svg` for the frontend.
- **Kotlinx Coroutines**: Used for handling asynchronous tasks if needed.
- **Detekt**: Integrated for static code analysis and code style enforcement.
- **Gradle Versions Plugin**: Used for managing and checking for dependency updates.

## Deployment

CI runs on `.github/workflows/`:
- `check-and-build.yml` — detekt + build on PRs to `main` and pushes to `develop`
- `publish.yml` — builds production artifacts and deploys to `gh-pages` branch (GitHub Pages)
- `scheduled-deployment.yml` — monthly automated version bump PR from `develop` → `main`

Versions follow the format `YYYY.MINOR.PATCH` and are set in `build.gradle.kts`.
