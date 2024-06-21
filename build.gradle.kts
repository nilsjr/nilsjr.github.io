import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
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
version = "2023.6.0"

kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }
  sourceSets {
    commonMain {
      dependencies {
        implementation(libs.kotlinx.coroutines)
      }
    }
    val jsMain by getting {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.html.core)
        implementation(compose.html.svg)
      }
    }
  }
}

composeCompiler {
  enableStrongSkippingMode = true
}

tasks.withType<Kotlin2JsCompile>().configureEach {
  compilerOptions {
    allWarningsAsErrors.set(false)
    progressiveMode.set(true)
    freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    target.set("es2015")
  }
}

rootProject.plugins.withType<YarnPlugin> {
  rootProject.the<YarnRootExtension>().apply {
    lockFileDirectory = project.rootDir.resolve(".kotlin-js-store")

    resolution("socket.io-parser", "4.2.3")
    resolution("qs", "6.9.7")
    resolution("braces", "3.0.3")
    resolution("ws", "8.17.1")
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

tasks.register<Copy>("moveAssets") {
  mustRunAfter("jsProcessResources")
  from(layout.buildDirectory.dir("processedResources/js/main/assets"))
  into(layout.buildDirectory.dir("dist/assets"))
}

tasks.register<Copy>("moveExecutable") {
  mustRunAfter("jsBrowserProductionWebpack")
  dependsOn("jsBrowserProductionWebpack", "moveAssets")
  from(
    layout.buildDirectory.file("kotlin-webpack/js/productionExecutable/nils.github.io.js"),
    layout.buildDirectory.file("kotlin-webpack/js/productionExecutable/nils.github.io.js.map"),
    layout.buildDirectory.file("processedResources/js/main/index.html"),
  )
  into(layout.buildDirectory.dir("dist"))
}