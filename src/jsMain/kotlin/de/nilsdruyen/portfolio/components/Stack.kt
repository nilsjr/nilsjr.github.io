/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun stack() {
  Div({ classes(TerminalStyle.card) }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ cat stack.kt") }
    Div({ classes(TerminalStyle.stackTools) }) {
      tool("assets/android-robot.svg", TerminalStyle.glowGreen, "android")
      tool("assets/kodee/kodee-greetings.gif", TerminalStyle.glowPurple, "kotlin", large = true)
      tool("assets/compose/Compose Multiplatform icon.svg", TerminalStyle.glowBlue, "compose")
      tool("assets/multiplatform/Kotlin Multiplatform icon.svg", TerminalStyle.glowPurple, "kmp")
      tool("assets/gradle/gradle.svg", TerminalStyle.glowBlue, "gradle")
    }
  }
}

@Composable
private fun tool(src: String, glowClass: String, name: String, large: Boolean = false) {
  Div({ classes(TerminalStyle.tool) }) {
    Img(src = src, attrs = {
      classes(if (large) TerminalStyle.toolIconLarge else TerminalStyle.toolIcon, glowClass)
    })
    Span({ classes(TerminalStyle.toolName) }) { Text(name) }
  }
}
