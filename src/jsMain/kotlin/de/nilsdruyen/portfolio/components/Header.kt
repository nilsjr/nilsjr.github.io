/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.rise
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun terminalHeader() {
  Div({
    classes(TerminalStyle.header)
    style { rise() }
  }) {
    Div({ classes(TerminalStyle.label) }) { Text("~/nilsjr 🐷") }
    Div({ classes(TerminalStyle.dots) }) {
      Span({ classes(TerminalStyle.dot) })
      Span({ classes(TerminalStyle.dot) })
      Span({ classes(TerminalStyle.dot, TerminalStyle.dotAccent) })
    }
  }
}