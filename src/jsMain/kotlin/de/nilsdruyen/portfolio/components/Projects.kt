/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun projects() {
  gridRow {
    Div({
      classes(
        Style.gridCol12,
        Style.maxWidth,
        Style.mxAuto,
      )
      style {
        height(250.px)
      }
    }) {
      Div({
        classes(
          Style.borderX,
          Style.borderGray,
          Style.dotted,
          Style.Grid.col12,
        )
      }) {
        P {
          Text("Projects")
        }
      }
    }
  }
}