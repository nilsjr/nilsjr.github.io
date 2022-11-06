import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.detekt)
  alias(libs.plugins.gradleVersions)
}

group = "de.nilsdruyen"
version = "0.0.2"

kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }
  sourceSets {
    val jsMain by getting {
      dependencies {
        implementation(compose.web.core)
        implementation(compose.runtime)
      }
    }
  }
}

compose {
  kotlinCompilerPlugin.set("1.3.2.1")
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    val arguments = listOf(
      "-progressive",
      "-Xopt-in=kotlin.RequiresOptIn"
    )
    freeCompilerArgs = freeCompilerArgs + arguments
    jvmTarget = "11"
  }
}

rootProject.plugins.withType<YarnPlugin> {
  rootProject.the<YarnRootExtension>().apply {
    lockFileDirectory = project.rootDir.resolve(".kotlin-js-store")
    resolution("async", "2.6.4")
    resolution("minimist", "1.2.6")
    resolution("eventsource", "1.1.1")
    resolution("url-parse", "1.5.8")
    resolution("loader-utils", "2.0.3")
  }
  rootProject.the<NodeJsRootExtension>().apply {
    versions.webpackDevServer.version = "4.9.3"
    versions.webpack.version = "5.73.0"
    versions.webpackCli.version = "4.10.0"
    versions.karma.version = "6.4.0"
    versions.mocha.version = "10.0.0"
  }
}

// configure detekt
extensions.configure<DetektExtension> {
  toolVersion = "1.21.0"
  source = files("src/jsMain/kotlin")
  parallel = true
  config = files("$rootDir/detekt.yml")
  buildUponDefaultConfig = true
}
dependencies {
  "detektPlugins"(libs.detekt.formatting)
}

// configure dependency updates
tasks.dependencyUpdates.configure {
  gradleReleaseChannel = "current"
}