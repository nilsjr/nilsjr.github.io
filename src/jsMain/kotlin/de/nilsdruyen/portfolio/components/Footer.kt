/*
 * Created by Nils Druyen on 07-09-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.rise
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun footer() {
  Div({
    classes(TerminalStyle.footer)
    style { rise(780) }
  }) {
    val year = kotlin.js.Date().getFullYear()
    Div { Text("built with love & kotlin multiplatform <3 © $year Nils Druyen") }
  }
}
