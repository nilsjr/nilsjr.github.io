/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.components.career
import de.nilsdruyen.portfolio.components.codeRain
import de.nilsdruyen.portfolio.components.contact
import de.nilsdruyen.portfolio.components.contributions
import de.nilsdruyen.portfolio.components.footer
import de.nilsdruyen.portfolio.components.hero
import de.nilsdruyen.portfolio.components.openSource
import de.nilsdruyen.portfolio.components.prompt
import de.nilsdruyen.portfolio.components.stack
import de.nilsdruyen.portfolio.components.terminalHeader
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.rise
import org.jetbrains.compose.web.dom.Div

@Composable
fun page() {
  codeRain()
  Div({ classes(TerminalStyle.content) }) {
    terminalHeader()
    hero()
    career()
    openSource()
    contributions()
    Div({
      classes(TerminalStyle.twoCol, TerminalStyle.sectionGap)
      style { rise(600) }
    }) {
      stack()
      contact()
    }
    prompt()
    footer()
  }
}