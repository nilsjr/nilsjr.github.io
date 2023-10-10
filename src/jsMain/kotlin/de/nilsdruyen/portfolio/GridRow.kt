/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.dom.Div

@Composable
fun gridRow(content: @Composable () -> Unit) {
  Div({
    classes(
      Style.borderB,
      Style.borderGray,
    )
  }) {
    content()
  }
}