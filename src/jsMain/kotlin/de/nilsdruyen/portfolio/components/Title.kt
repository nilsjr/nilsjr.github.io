/*
 * Created by Nils Druyen on 10-11-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.Style
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun title() {
  Header({ classes(Style.Grid.borderB, Style.borderGray) }) {
    Div({
      classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto)
      style { height(160.px) }
    }) {
      Div({
        classes(
          Style.Grid.borderX,
          Style.borderGray,
          Style.Grid.title,
          Style.Flex.container,
          Style.Flex.alignLeft,
        )
      }) {
        P({ classes(Style.title) }) { Text("nilsjr.") }
      }
      Div({ classes(Style.Grid.borderR, Style.borderGray, Style.Grid.span1) }) {}
      Div({
        classes(
          Style.Grid.borderR,
          Style.borderGray,
          Style.Section.gradient,
          Style.Grid.span5,
          Style.Grid.titleHidden,
        )
      }) {
        P({
          style {
            fontWeight(500)
          }
        }) {
          Text("Coming soon...")
        }
      }
    }
  }
}