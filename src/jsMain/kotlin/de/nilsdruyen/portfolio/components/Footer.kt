/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun footer() {
  Footer({
    classes(
      Style.borderB,
      Style.borderGray,
    )
  }) {
    Div({
      style {
        height(140.px)
      }
      classes(
        Style.gridCol12,
        Style.maxWidth,
        Style.mxAuto,
      )
    }) {
      Div({
        classes(
          Style.borderX,
          Style.borderGray,
          Style.Grid.col12,
        )
        style {
          display(DisplayStyle.Flex)
          justifyContent(JustifyContent.Center)
        }
      }) {
        P({
          style {
            textAlign("center")
            property("margin", "auto")
          }
        }) {
          Text("© 2022 Nils Druyen")
        }
      }
    }
  }
  gridRow {
    Div({
      classes(
        Style.gridCol12,
        Style.maxWidth,
        Style.mxAuto,
      )
      style {
        height(40.px)
      }
    }) {
      Div({
        classes(
          Style.borderX,
          Style.borderGray,
          Style.Grid.col12,
        )
      }) {}
    }
  }
}