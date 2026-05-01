# Project Analysis: nilsjr.github.io

This project is a personal portfolio website built using Kotlin Multiplatform and Compose HTML.

## Architecture

- **Kotlin Multiplatform (JS)**: The project targets Kotlin/JS for the browser using the IR compiler.
- **Compose HTML**: UI is built using the `compose.html` libraries, which provide a declarative way to define the DOM
  and CSS.
- **Modular UI Components**: The UI is structured into discrete, reusable Composable functions found in
  `de.nilsdruyen.portfolio.components`.
- **State Persistence**: Uses `browser.localStorage` to persist user preferences like dark mode across sessions.
- **Root Rendering**: The application entry point (`Main.kt`) binds the Compose runtime to a `<div id="root">` in the
  `index.html`.

## Technologies

- **Kotlin**: Primary programming language.
- **JetBrains Compose for Web**: Specifically `compose.html.core` and `compose.html.svg` for the frontend.
- **Kotlinx Coroutines**: Used for handling asynchronous tasks if needed.
- **Detekt**: Integrated for static code analysis and code style enforcement.
- **Gradle Versions Plugin**: Used for managing and checking for dependency updates.

## Key Gradle Tasks

- `jsBrowserProductionWebpack`: Compiles and bundles the application for production.
- `moveAssets`: Copies assets to the distribution directory.
- `moveExecutable`: Custom task that assembles the final `index.html`, JS bundles, and assets into `build/dist/` for
  easy deployment (e.g., to GitHub Pages).
- `ktlintCheck`: Runs detekt with a focus on formatting/linting rules.
- `./gradlew kotlinUpgradeYarnLock`: Updates yarn.lock for current state.
- `./gradlew jsBrowserDevelopmentRun`: Runs current application and serves it at http://localhost:8080

## Project Structure

- `src/jsMain/kotlin`: Contains the main application logic and UI components.
- `src/jsMain/resources`: Contains static assets like `index.html` and images.
- `detekt.yml` & `detekt-formatting.yml`: Configuration for static analysis.
