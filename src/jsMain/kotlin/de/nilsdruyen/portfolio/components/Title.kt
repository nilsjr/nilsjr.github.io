/*
 * Created by Nils Druyen on 10-11-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun title() {
  Header({ classes(Style.borderB, Style.borderGray) }) {
    Div({
      classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto)
      style { height(200.px) }
    }) {
      Div({ classes(Style.borderX, Style.borderGray, Style.Grid.span6, Style.Flex.container, Style.Flex.alignLeft) }) {
        P({ classes(Style.title) }) { Text("nilsjr.") }
      }
      Div({ classes(Style.borderR, Style.borderGray, Style.Grid.span1) }) {}
      Div({ classes(Style.borderR, Style.borderGray, Style.Section.gradient, Style.Grid.span5) }) {}
    }
  }
}