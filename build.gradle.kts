import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.detekt)
  alias(libs.plugins.gradleVersions)
}

group = "de.nilsdruyen"
version = "2023.5.0"

kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(libs.kotlinx.coroutines)
      }
    }
    val jsMain by getting {
      dependencies {
        implementation(compose.html.core)
        implementation(compose.html.svg)
        implementation(compose.runtime)
      }
    }
  }
}

val compilerVersion: String = libs.versions.jetbrainsComposeCompiler.get()
compose {
  kotlinCompilerPlugin.set(compilerVersion)
}

tasks.withType<Kotlin2JsCompile>().configureEach {
  compilerOptions {
    allWarningsAsErrors.set(false)
    progressiveMode.set(true)
  }
  kotlinOptions {
    val arguments = listOf(
      "-opt-in=kotlin.RequiresOptIn",
    )
    freeCompilerArgs = freeCompilerArgs + arguments
  }
}

rootProject.plugins.withType<YarnPlugin> {
  rootProject.the<YarnRootExtension>().apply {
    lockFileDirectory = project.rootDir.resolve(".kotlin-js-store")

//    resolution("async", "2.6.4")
//    resolution("engine.io", "6.4.2")
//    resolution("eventsource", "1.1.1")
//    resolution("express", "4.17.3")
//    resolution("follow-redirects", "1.14.8")
//    resolution("json5", "2.2.2")
//    resolution("loader-utils", "2.0.4")
//    resolution("minimatch", "3.0.5")
//    resolution("minimist", "1.2.6")
//    resolution("node-forge", "1.3.0")
//    resolution("qs", "6.9.7")
//    resolution("socket.io-parser", "4.2.3")
//    resolution("ua-parser-js", "0.7.33")
//    resolution("url-parse", "1.5.9")
  }
  rootProject.the<NodeJsRootExtension>().apply {
    versions.webpackDevServer.version = libs.versions.webpackDevServer.get()
    versions.webpack.version = libs.versions.webpack.get()
    versions.webpackCli.version = libs.versions.webpackCli.get()
    versions.karma.version = "6.4.0"
    versions.mocha.version = "10.0.0"
  }
}

// configure detekt
extensions.configure<DetektExtension> {
  parallel = true
  source.setFrom(files("src/jsMain/kotlin"))
  config.setFrom(files("$rootDir/detekt.yml"))
  buildUponDefaultConfig = true
}
dependencies {
  "detektPlugins"(libs.detekt.formatting)
}

tasks.register<Detekt>("ktlintCheck") {
  description = "Run detekt ktlint wrapper"
  parallel = true
  setSource(files("src/jsMain/kotlin"))
  config.setFrom(files("$rootDir/detekt-formatting.yml"))
  buildUponDefaultConfig = true
  disableDefaultRuleSets = true
  autoCorrect = false
  reports {
    xml {
      required.set(true)
      outputLocation.set(layout.buildDirectory.file("reports/detekt/detektFormatting.xml"))
    }
    html.required.set(false)
    txt.required.set(false)
  }
  include(listOf("**/*.kt", "**/*.kts"))
  exclude("build/")
  dependencies {
    "detektPlugins"(libs.detekt.formatting)
  }
}

// configure dependency updates
tasks.dependencyUpdates.configure {
  gradleReleaseChannel = "release-candidate"
}