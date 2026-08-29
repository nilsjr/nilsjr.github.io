/*
 * Created by Nils Druyen on 07-09-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.data

import kotlinx.browser.window
import kotlinx.coroutines.await

private const val REPOS_ASSET_URL = "assets/repos.json"

data class Repo(val url: String, val name: String, val description: String)

val fallbackRepos = listOf(
  Repo(
    "https://github.com/nilsjr/Snappy",
    "Snappy",
    "📸 Android CameraX Library",
  ),
  Repo(
    "https://github.com/nilsjr/gradle-ftp-upload-plugin",
    "gradle-ftp-upload-plugin",
    "Gradle plugin for uploading files via ftp",
  ),
  Repo(
    "https://github.com/nilsjr/WoWMythicPlus",
    "WoWMythicPlus",
    "🔑 WoW M+ overview displaying data from raider.io api",
  ),
  Repo(
    "https://github.com/nilsjr/Koncept",
    "Koncept",
    "🔨 Android playground app for architecture, new frameworks & other stuff",
  ),
  Repo(
    "https://github.com/nilsjr/ComposeParty",
    "ComposeParty",
    "🎉 Compose playground project on steroids",
  ),
  Repo(
    "https://github.com/nilsjr/nilsjr.github.io",
    "nilsjr.github.io",
    "🧍 My portfolio webpage",
  ),
)

/**
 * Loads all public repos tagged with the "portfolio" topic from the build-time-generated
 * repos.json asset. Returns null on network errors so callers can fall back.
 */
@Suppress("TooGenericExceptionCaught")
suspend fun loadPortfolioRepos(): List<Repo>? = try {
  val response = window.fetch(REPOS_ASSET_URL).await()
  if (!response.ok) {
    null
  } else {
    val data = JSON.parse<dynamic>(response.text().await())
    (data.portfolioRepos as? Array<dynamic>)
      ?.map { repo ->
        Repo(
          url = repo.url as String,
          name = repo.name as String,
          description = (repo.description as? String).orEmpty(),
        )
      }
      ?.ifEmpty { null }
  }
} catch (e: Throwable) {
  console.warn("Failed to load repos.json", e.message)
  null
}

val fallbackContributions = listOf(
  Repo(
    "https://github.com/mobile-dev-inc/maestro",
    "Maestro",
    "Painless Mobile UI Automation",
  ),
  Repo(
    "https://github.com/postmanlabs/postman-code-generators",
    "postman-code-generators",
    "Common repository for all code generators shipped with Postman",
  ),
  Repo(
    "https://github.com/google/accompanist",
    "accompanist",
    "A collection of extension libraries for Jetpack Compose",
  ),
  Repo(
    "https://github.com/microsoft/azure-gradle-plugins",
    "azure-gradle-plugins",
    "About Azure Plugins for Gradle",
  ),
)

/**
 * Loads all external repos with merged pull requests authored by the user from the
 * build-time-generated repos.json asset. Returns null on network errors so callers can
 * fall back.
 */
@Suppress("TooGenericExceptionCaught")
suspend fun loadContributions(): List<Repo>? = try {
  val response = window.fetch(REPOS_ASSET_URL).await()
  if (!response.ok) {
    null
  } else {
    val data = JSON.parse<dynamic>(response.text().await())
    (data.contributions as? Array<dynamic>)
      ?.map { repo ->
        Repo(
          url = repo.url as String,
          name = repo.name as String,
          description = (repo.description as? String).orEmpty(),
        )
      }
      ?.ifEmpty { null }
  }
} catch (e: Throwable) {
  console.warn("Failed to load contributions from repos.json", e.message)
  null
}