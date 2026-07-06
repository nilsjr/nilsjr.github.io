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
    classes(TerminalStyle.card, TerminalStyle.heroGap)
    style { rise(240) }
  }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ ls ~/open-source") }
    A(href = "https://github.com/nilsjr/Snappy", { classes(TerminalStyle.repoLink) }) {
      Span({ classes(TerminalStyle.repoName) }) { Text("Snappy/") }
      Span({ classes(TerminalStyle.repoDesc) }) {
        Text("📸 CameraX library — snapshots fast & simple · Kotlin · Compose")
      }
    }
    A(href = "https://github.com/nilsjr", { classes(TerminalStyle.arrowLink) }) {
      Text("→ github.com/nilsjr")
    }
  }
}