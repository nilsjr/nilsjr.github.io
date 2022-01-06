import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.compose.compose

plugins {
  kotlin("multiplatform") version "1.6.10"
  id("org.jetbrains.compose") version "1.1.0-alpha1-dev536"
  id("com.github.ben-manes.versions") version "0.41.0"
  id("io.gitlab.arturbosch.detekt") version "1.19.0"
}

group = "de.nilsdruyen"
version = "0.0.1"

kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }
  sourceSets {
    @Suppress("UNUSED_VARIABLE")
    val jsMain by getting {
      dependencies {
        implementation(compose.web.core)
        implementation(compose.runtime)
      }
    }
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
    val arguments = listOf(
      "-progressive",
      "-Xopt-in=kotlin.RequiresOptIn"
    )
    freeCompilerArgs = freeCompilerArgs + arguments
    jvmTarget = "11"
  }
}

// configure detekt
extensions.configure<DetektExtension> {
  toolVersion = "1.19.0"
  source = files("src/jsMain/kotlin")
  parallel = true
  config = files("$rootDir/detekt.yml")
  buildUponDefaultConfig = true
}
dependencies {
  "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

// configure dependency updates
tasks.dependencyUpdates.configure {
  gradleReleaseChannel = "current"
  rejectVersionIf {
    isNonStable(candidate.version)
  }
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
  val regex = "^[0-9,.v-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}