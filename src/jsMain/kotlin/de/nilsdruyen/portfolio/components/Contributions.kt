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
import de.nilsdruyen.portfolio.data.fallbackContributions
import de.nilsdruyen.portfolio.data.loadContributions
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.rise
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun contributions() {
  Div({
    classes(TerminalStyle.card, TerminalStyle.sectionGap)
    style { rise(480) }
  }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ ls ~/contributions") }
    var repos by remember { mutableStateOf(fallbackContributions) }
    LaunchedEffect(Unit) {
      loadContributions()?.let { repos = it }
    }
    Div({ classes(TerminalStyle.repoList) }) {
      repos.forEach { repo(it.url, "${it.name}/", it.description) }
    }
  }
}