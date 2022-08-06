import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.detekt)
  alias(libs.plugins.gradleVersions)
}

group = "de.nilsdruyen"
version = "0.0.1"

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

//rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
//  rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().apply {
//    lockFileDirectory = project.rootDir.resolve(".kotlin-js-store")
//    resolution("got", "12.1.0")
//  }
//  rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().apply {
//    versions.webpackDevServer.version = "4.9.3"
//    versions.webpack.version = "5.73.0"
//    versions.webpackCli.version = "4.10.0"
//    versions.karma.version = "6.4.0"
//    versions.mocha.version = "10.0.0"
//  }
//}

// configure detekt
extensions.configure<DetektExtension> {
  toolVersion = "1.19.0"
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
  rejectVersionIf {
    isNonStable(candidate.version) && !isNonStable(currentVersion)
  }
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
  val regex = "^[0-9,.v-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}