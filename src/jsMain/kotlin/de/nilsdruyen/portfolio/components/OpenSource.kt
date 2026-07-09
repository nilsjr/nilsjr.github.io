/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.nilsdruyen.portfolio.data.fallbackRepos
import de.nilsdruyen.portfolio.data.loadPortfolioRepos
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.openInNewTab
import de.nilsdruyen.portfolio.ui.rise
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun openSource() {
  Div({
    classes(TerminalStyle.card, TerminalStyle.sectionGap)
    style { rise(360) }
  }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ ls ~/open-source") }
    var repos by remember { mutableStateOf(fallbackRepos) }
    LaunchedEffect(Unit) {
      loadPortfolioRepos()?.let { repos = it }
    }
    Div({ classes(TerminalStyle.repoList) }) {
      repos.forEach { repo(it.url, "${it.name}/", it.description) }
    }
    A(href = "https://github.com/nilsjr", {
      classes(TerminalStyle.arrowLink)
      openInNewTab()
    }) {
      Text("→ github.com/nilsjr")
    }
  }
}

@Composable
internal fun repo(href: String, name: String, description: String) {
  A(href = href, {
    classes(TerminalStyle.repoLink)
    openInNewTab()
  }) {
    Span({ classes(TerminalStyle.repoName) }) { Text(name) }
    Span({ classes(TerminalStyle.repoDesc) }) { Text(description) }
  }
}