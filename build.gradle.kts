import org.jetbrains.compose.compose

plugins {
  kotlin("multiplatform") version "1.6.10"
  id("org.jetbrains.compose") version "1.1.0-alpha1-dev536"
  id("com.github.ben-manes.versions") version "0.39.0"
}

group = "de.nilsdruyen"
version = "0.0.1"

repositories {
  google()
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

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
