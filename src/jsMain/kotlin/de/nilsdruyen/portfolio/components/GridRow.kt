/*
 * Created by Nils Druyen on 10-13-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.Style
import org.jetbrains.compose.web.dom.Div

@Composable
fun gridRow(content: @Composable () -> Unit) {
  Div({ classes(Style.Grid.borderB, Style.borderGray) }) {
    content()
  }
}