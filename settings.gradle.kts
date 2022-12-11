pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
  }
}
dependencyResolutionManagement {
  repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    maven("https://androidx.dev/storage/compose-compiler/repository")
  }
}
rootProject.name = "NilsPortfolio"
