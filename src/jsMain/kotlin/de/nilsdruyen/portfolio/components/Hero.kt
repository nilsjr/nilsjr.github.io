/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.rise
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
private fun rememberIsWideViewport(): State<Boolean> {
  val mediaQuery = remember { window.matchMedia("(min-width: 1121px)") }
  val isWide = remember { mutableStateOf(mediaQuery.matches) }
  DisposableEffect(Unit) {
    val listener: (org.w3c.dom.events.Event) -> Unit = { isWide.value = mediaQuery.matches }
    mediaQuery.addEventListener("change", listener)
    onDispose { mediaQuery.removeEventListener("change", listener) }
  }
  return isWide
}

@Composable
fun hero() {
  Div({
    classes(TerminalStyle.hero)
  }) {
    val isWideViewport by rememberIsWideViewport()
    if (isWideViewport) {
      miniTerminal()
    }
    Div({ classes(TerminalStyle.muted) }) { Text("// who am i") }
    H1({ classes(TerminalStyle.heroTitle) }) {
      Span({ classes(TerminalStyle.keyword) }) { Text("val") }
      Text(" nils = Druyen()")
    }
    P({ classes(TerminalStyle.heroText) }) {
      Text("Passionate Android developer since 2012, working on several B2B & B2C apps with over ")
      Text("10m users worldwide — currently as Software Engineer at ")
      Span({ classes(TerminalStyle.bright) }) { Text("Fressnapf | Maxizoo") }
      Text(".")
      Br()
      Text("Writing Kotlin for a living — and for fun.")
    }
    P({
      classes(TerminalStyle.heroText)
      style { rise() }
    }) {
      Text("I live on a small farm on the beautiful Lower Rhine 🇩🇪 and love to work from the home office. ")
      Text("I like to explore and dig deep in new stuff like Kotlin Multiplatform, Compose Multiplatform and beyond.")
    }
  }
}