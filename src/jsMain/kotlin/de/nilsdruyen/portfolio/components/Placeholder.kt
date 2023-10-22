/*
 * Created by Nils Druyen on 10-11-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.Style
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.dom.Div

@Composable
fun placeholder(iconBlock: @Composable () -> Unit = {}) {
  gridRow {
    Div({ classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto, Style.Grid.placeholder) }) {
      Div({ classes(Style.Grid.borderX, Style.borderGray, Style.Grid.span4) }) {}
      Div({ classes(Style.Grid.borderR, Style.borderGray, Style.Grid.span2) }) {}
      Div({ classes(Style.Grid.borderR, Style.borderGray, Style.Grid.span1) }) {}
      Div({
        classes(Style.Grid.borderR, Style.borderGray, Style.Grid.span5)
        style {
          display(DisplayStyle.Flex)
          justifyContent(JustifyContent.End)
          alignItems(AlignItems.Center)
        }
      }) {
        iconBlock()
      }
    }
  }
}