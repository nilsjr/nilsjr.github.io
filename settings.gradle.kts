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
    google()
    maven("https://androidx.dev/storage/compose-compiler/repository")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}
rootProject.name = "nils.github.io"
