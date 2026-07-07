/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.openInNewTab
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun contact() {
  Div({ classes(TerminalStyle.card) }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ contact --all") }
    Div({ classes(TerminalStyle.contactList) }) {
      contactLink("https://github.com/nilsjr", "github", "nilsjr")
      contactLink("https://twitter.com/NilsJr", "twitter", "NilsJr")
      contactLink("https://www.instagram.com/nils.j.r/?hl=de", "instagram", "nils.j.r")
      contactLink("https://www.linkedin.com/in/nils-druyen-235b6024b", "linkedin", "nils-druyen")
      contactLink("https://www.xing.com/profile/Nils_Druyen/cv", "xing", "Nils_Druyen")
    }
  }
}

@Composable
private fun contactLink(href: String, prefix: String, name: String) {
  A(href = href, {
    classes(TerminalStyle.contactLink)
    openInNewTab()
  }) {
    Text(prefix)
    Span({ classes(TerminalStyle.muted) }) { Text("/") }
    Text(name)
  }
}