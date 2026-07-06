/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.rise
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Pre
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

private val ANDROID_ART = """
  |  ;,          ,;
  |   ';,.----.,;'
  |  ,'          ',
  | /   O      O   \
  | |              |
  | '--------------'
""".trimMargin()

private val KOTLIN_ART = """
  |██████████
  |████████
  |██████
  |████
  |██████
  |████████
  |██████████
""".trimMargin()

private val GRADLE_ART = """
  |      _.-----.
  |   .-'        '.
  |  /    .--.     \
  |  \    '--' .-._/
  |   |  |    |  |
  |   '--'    '--'
""".trimMargin()

@Composable
fun tools() {
  Div({
    classes(TerminalStyle.card, TerminalStyle.sectionGap)
    style { rise(480) }
  }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ neofetch --tools") }
    Div({ classes(TerminalStyle.toolsRow) }) {
      tool(ANDROID_ART, TerminalStyle.asciiAndroid, "android")
      tool(KOTLIN_ART, TerminalStyle.asciiKotlin, "kotlin")
      tool(GRADLE_ART, TerminalStyle.asciiGradle, "gradle")
    }
  }
}

@Composable
private fun tool(art: String, artClass: String, name: String) {
  Div({ classes(TerminalStyle.tool) }) {
    Pre({ classes(TerminalStyle.ascii, artClass) }) { Text(art) }
    Span({ classes(TerminalStyle.toolName) }) { Text(name) }
  }
}