/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun stack() {
  Div({ classes(TerminalStyle.card) }) {
    H2({ classes(TerminalStyle.cardLabel) }) { Text("$ cat stack.kt") }
    Div({ classes(TerminalStyle.stackTools) }) {
      tool("assets/android-robot.svg", TerminalStyle.glowGreen, "android", width = 1274, height = 718)
      tool(
        "assets/kodee/Kodee_Assets_Digital_Kodee-greeting.svg",
        TerminalStyle.glowPurple,
        "kotlin",
        width = 800,
        height = 800,
        large = true,
      )
      tool("assets/compose/Compose Multiplatform icon.svg", TerminalStyle.glowBlue, "compose", width = 50, height = 56)
      tool(
        "assets/multiplatform/Kotlin Multiplatform icon.svg",
        TerminalStyle.glowPurple,
        "kmp",
        width = 48,
        height = 48,
      )
      tool("assets/gradle/gradle.svg", TerminalStyle.glowBlue, "gradle", width = 87, height = 64)
    }
  }
}

@Composable
private fun tool(src: String, glowClass: String, name: String, width: Int, height: Int, large: Boolean = false) {
  Div({ classes(TerminalStyle.tool) }) {
    Img(src = src, attrs = {
      classes(if (large) TerminalStyle.toolIconLarge else TerminalStyle.toolIcon, glowClass)
      attr("width", width.toString())
      attr("height", height.toString())
    })
    Span({ classes(TerminalStyle.toolName) }) { Text(name) }
  }
}