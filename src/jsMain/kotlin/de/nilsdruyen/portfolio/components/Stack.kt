/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun stack() {
  Div({ classes(TerminalStyle.card) }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ cat stack.kt") }
    Div({ classes(TerminalStyle.codeLines) }) {
      Span {
        Span({ classes(TerminalStyle.keyword) }) { Text("listOf") }
        Text("(")
        literal("Kotlin")
        Text(", ")
        literal("Compose")
        Text(",")
      }
      Span({ classes(TerminalStyle.codeIndent) }) {
        literal("KMP")
        Text(", ")
        literal("Gradle")
        Text(", ")
        literal("CI/CD")
        Text(")")
      }
    }
  }
}

@Composable
private fun literal(value: String) {
  Span({ classes(TerminalStyle.string) }) { Text("\"$value\"") }
}