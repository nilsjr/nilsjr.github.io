/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
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
    Div({ classes(TerminalStyle.repoList) }) {
      repo(
        "https://github.com/nilsjr/Snappy",
        "Snappy/",
        "📸 Android CameraX Library",
      )
      repo(
        "https://github.com/nilsjr/gradle-ftp-upload-plugin",
        "gradle-ftp-upload-plugin/",
        "Gradle plugin for uploading files via ftp",
      )
      repo(
        "https://github.com/nilsjr/WoWMythicPlus",
        "WoWMythicPlus/",
        "🔑 WoW M+ overview displaying data from raider.io api",
      )
      repo(
        "https://github.com/nilsjr/Koncept",
        "Koncept/",
        "🔨 Android playground app for architecture, new frameworks & other stuff",
      )
      repo(
        "https://github.com/nilsjr/ComposeParty",
        "ComposeParty/",
        "🎉 Compose playground project on steroids",
      )
      repo(
        "https://github.com/nilsjr/nilsjr.github.io",
        "nilsjr.github.io/",
        "🧍 My portfolio webpage",
      )
    }
    A(href = "https://github.com/nilsjr", { classes(TerminalStyle.arrowLink) }) {
      Text("→ github.com/nilsjr")
    }
  }
}

@Composable
internal fun repo(href: String, name: String, description: String) {
  A(href = href, { classes(TerminalStyle.repoLink) }) {
    Span({ classes(TerminalStyle.repoName) }) { Text(name) }
    Span({ classes(TerminalStyle.repoDesc) }) { Text(description) }
  }
}
