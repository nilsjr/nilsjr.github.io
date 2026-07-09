import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.detekt)
  alias(libs.plugins.gradleVersions)
}

group = "de.nilsdruyen"
version = "2026.5.0"

kotlin {
  js {
    browser()
    binaries.executable()
  }
  sourceSets {
    commonMain {
      dependencies {
        implementation(libs.kotlinx.coroutines)
        implementation(libs.kotlinx.datetime)
      }
    }
    jsMain {
      dependencies {
        implementation(libs.compose.runtime)
        implementation(libs.compose.html.core)
        implementation(libs.compose.html.svg)
      }
    }
  }
}

tasks.withType<Kotlin2JsCompile>().configureEach {
  compilerOptions {
    allWarningsAsErrors.set(false)
    progressiveMode.set(true)
    target.set("es2015")
  }
}

rootProject.plugins.withType<YarnPlugin> {
  rootProject.the<YarnRootExtension>().apply {
    lockFileDirectory = project.rootDir.resolve(".kotlin-js-store")

    resolution("body-parser", "1.20.5")
    resolution("braces", "3.0.3")
    resolution("cross-spawn", "7.0.6")
    resolution("flatted", "3.4.2")
    resolution("glob", "10.5.0")
    resolution("http-proxy-middleware", "2.0.10")
    resolution("minimatch", "9.0.7")
    resolution("node-forge", "1.3.2")
    resolution("path-to-regexp", "0.1.13")
    resolution("qs", "6.15.2")
    resolution("serialize-javascript", "7.0.3")
    resolution("shell-quote", "1.8.4")
    resolution("socket.io-parser", "4.2.6")
    resolution("ws", "8.21.0")
  }
  rootProject.the<NodeJsRootExtension>().apply {
    versions.webpackDevServer.version = libs.versions.webpackDevServer.get()
    versions.webpack.version = libs.versions.webpack.get()
    versions.webpackCli.version = libs.versions.webpackCli.get()
    versions.karma.version = libs.versions.karma.get()
    versions.mocha.version = libs.versions.mocha.get()
  }
}

// configure detekt
extensions.configure<DetektExtension> {
  parallel.set(true)
  buildUponDefaultConfig.set(true)
  source.setFrom(files("src/jsMain/kotlin"))
  config.setFrom(files("$rootDir/detekt.yml"))
}
dependencies {
  "detektPlugins"(libs.detekt.ktlint.wrapper)
}

tasks.register<Detekt>("ktlintCheck") {
  description = "Run detekt ktlint wrapper"
  parallel.set(true)
  setSource(files("src/jsMain/kotlin"))
  config.setFrom(files("$rootDir/detekt-formatting.yml"))
  buildUponDefaultConfig.set(true)
  disableDefaultRuleSets.set(true)
  autoCorrect.set(false)
  reports {
    checkstyle {
      required.set(true)
      outputLocation.set(layout.buildDirectory.file("reports/detekt/ktlint-check.xml"))
    }
    html.required.set(false)
  }
  include(listOf("**/*.kt", "**/*.kts"))
  exclude("build/")
}

tasks.register<Detekt>("ktlintFormat") {
  description = "Run detekt ktlint wrapper"
  parallel.set(true)
  setSource(files("src/jsMain/kotlin"))
  config.setFrom(files("$rootDir/detekt-formatting.yml"))
  buildUponDefaultConfig.set(true)
  disableDefaultRuleSets.set(true)
  autoCorrect.set(true)
  reports {
    checkstyle {
      required.set(true)
      outputLocation.set(layout.buildDirectory.file("reports/detekt/ktlint-format.xml"))
    }
    html.required.set(false)
  }
  include(listOf("**/*.kt", "**/*.kts"))
  exclude("build/")
}

// configure dependency updates
tasks.withType<DependencyUpdatesTask> {
  gradleReleaseChannel = "release-candidate"
  rejectVersionIf {
    isNonStable(candidate.version) && !isNonStable(currentVersion)
  }
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
  val regex = "^[0-9,.v-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}
