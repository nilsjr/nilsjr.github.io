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
fun career() {
  Div({
    classes(TerminalStyle.card, TerminalStyle.heroGap)
    style { rise(240) }
  }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ git log --reverse career") }
    Div({ classes(TerminalStyle.timeline) }) {
      station("2010 - 2016", "Angewandte Informatik", "Universität Duisburg-Essen")
      station("2016 - 2021", "Software Entwickler", "NanoGiants GmbH")
      station("since 2022", "Software Engineer", "Fressnapf | Maxizoo")
    }
  }
}

@Composable
private fun station(period: String, title: String, company: String) {
  Div {
    Span({ classes(TerminalStyle.timelinePeriod) }) { Text(period) }
    Span({ classes(TerminalStyle.bright) }) { Text(title) }
    Span({ classes(TerminalStyle.muted) }) { Text(" · $company") }
  }
}