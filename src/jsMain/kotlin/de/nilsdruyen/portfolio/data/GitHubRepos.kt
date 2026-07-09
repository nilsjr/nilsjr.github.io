/*
 * Created by Nils Druyen on 07-09-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.data

import kotlinx.browser.window
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

private const val USER = "nilsjr"
private const val REPOS_URL = "https://api.github.com/users/$USER/repos?per_page=100"
private const val MERGED_PRS_URL = "https://api.github.com/search/issues?q=author:$USER+is:pr+is:merged&per_page=100"
private const val PORTFOLIO_TOPIC = "portfolio"

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
 * Loads all public repos tagged with the "portfolio" topic on GitHub.
 * Returns null on network errors or rate limiting so callers can fall back.
 */
@Suppress("TooGenericExceptionCaught")
suspend fun loadPortfolioRepos(): List<Repo>? = try {
  val response = window.fetch(REPOS_URL).await()
  if (!response.ok) {
    null
  } else {
    val repos = JSON.parse<Array<dynamic>>(response.text().await())
    repos
      .filter { repo -> (repo.topics as? Array<String>)?.contains(PORTFOLIO_TOPIC) == true }
      .sortedByDescending { repo -> (repo.stargazers_count as? Int) ?: 0 }
      .map { repo ->
        Repo(
          url = repo.html_url as String,
          name = repo.name as String,
          description = (repo.description as? String).orEmpty(),
        )
      }
      .ifEmpty { null }
  }
} catch (e: Throwable) {
  console.warn("Failed to load repos from GitHub", e.message)
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
 * Loads all external repos with merged pull requests authored by the user.
 * Returns null on network errors or rate limiting so callers can fall back.
 */
@Suppress("TooGenericExceptionCaught", "ReturnCount")
suspend fun loadContributions(): List<Repo>? = try {
  val response = window.fetch(MERGED_PRS_URL).await()
  if (!response.ok) {
    null
  } else {
    val result = JSON.parse<dynamic>(response.text().await())
    val prs = result.items as? Array<dynamic> ?: emptyArray()
    val externalRepoUrls = prs
      .map { pr -> pr.repository_url as String }
      .distinct()
      .filterNot { url -> url.contains("/repos/$USER/") }
    coroutineScope {
      externalRepoUrls.map { url -> async { fetchRepo(url) } }.awaitAll()
    }
      .filterNotNull()
      .sortedByDescending { it.second }
      .map { it.first }
      .ifEmpty { null }
  }
} catch (e: Throwable) {
  console.warn("Failed to load contributions from GitHub", e.message)
  null
}

@Suppress("TooGenericExceptionCaught")
private suspend fun fetchRepo(url: String): Pair<Repo, Int>? = try {
  val response = window.fetch(url).await()
  if (!response.ok) {
    null
  } else {
    val repo = JSON.parse<dynamic>(response.text().await())
    Repo(
      url = repo.html_url as String,
      name = repo.name as String,
      description = (repo.description as? String).orEmpty(),
    ) to ((repo.stargazers_count as? Int) ?: 0)
  }
} catch (e: Throwable) {
  console.warn("Failed to load repo details", url, e.message)
  null
}