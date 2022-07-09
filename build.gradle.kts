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

// configure detekt
extensions.configure<DetektExtension> {
  toolVersion = "1.19.0"
  source = files("src/jsMain/kotlin")
  parallel = true
  config = files("$rootDir/detekt.yml")
  buildUponDefaultConfig = true
}
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
val detektVersion = libs.findVersion("detekt").get().toString()
dependencies {
  "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
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