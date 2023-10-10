/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.gridColumn
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun work() {
  gridRow {
    Div({
      style {
        height(200.px)
      }
      classes(
        Style.gridCol12,
        Style.maxWidth,
        Style.mxAuto,
      )
    }) {
      Div({
        classes(Style.borderX, Style.borderGray)
        style { gridColumn("span 4/span 4") }
      }) {
        Text("work")
      }
      Div({
        style {
          gridColumn("span 8/span 8")
        }
        classes(
          Style.borderR,
          Style.borderGray,
          Style.dotted,
        )
      }) {

      }
    }
  }
}