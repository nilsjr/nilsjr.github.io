/*
 * Created by Nils Druyen on 10-11-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.model.Model
import de.nilsdruyen.portfolio.ui.Style
import de.nilsdruyen.portfolio.ui.fadeIn
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun title() {
  Header({
    classes(Style.Grid.borderB, Style.borderGray)
  }) {
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
        Div({ style { width(2.cssRem) } }) { }
        Model.TITLE.forEachIndexed { index, c ->
          P({
            classes(Style.title)
            style {
              fadeIn(200 * index)
            }
          }) {
            Span {
              Text(c.toString())
            }
          }
        }
      }
      Div({ classes(Style.Grid.borderR, Style.borderGray, Style.Grid.span1, Style.Grid.hidden) }) {}
      Div({
        classes(
          Style.Section.gradient,
          Style.borderGray,
          Style.Grid.borderR,
          Style.Grid.span5,
          Style.Grid.titleHidden,
        )
      }) {
        P({ classes(Style.Section.subtitle) }) {
          Text("Coming soon...")
        }
      }
    }
  }
}