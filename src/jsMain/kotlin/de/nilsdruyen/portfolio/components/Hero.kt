/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.rise
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun hero() {
  Div({
    classes(TerminalStyle.hero)
    style { rise(120) }
  }) {
    Div({ classes(TerminalStyle.muted) }) { Text("// who am i") }
    H1({ classes(TerminalStyle.heroTitle) }) {
      Span({ classes(TerminalStyle.keyword) }) { Text("val") }
      Text(" nils = Druyen()")
    }
    P({ classes(TerminalStyle.heroText) }) {
      Text("Android developer at ")
      Span({ classes(TerminalStyle.bright) }) { Text("Fressnapf") }
      Text(", Krefeld.")
      Br()
      Text("Writing Kotlin for a living — and for fun.")
    }
  }
}