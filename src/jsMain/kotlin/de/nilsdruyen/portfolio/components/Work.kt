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
fun work() {
  gridRow {
    Div({
      classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto)
      style { height(200.px) }
    }) {
      Div({ classes(Style.borderX, Style.borderGray, Style.Grid.span4, Style.pad1) }) {
        P({ classes(Style.Section.title2) }) { Text("Work") }
        P({ classes(Style.Section.subtitle, Style.smallMargin) }) { Text("tbd.") }
      }
      Div({ classes(Style.borderR, Style.borderGray, Style.dotted, Style.Grid.span8, Style.pad1) }) {
        P({ classes(Style.Section.title2) }) { Text("Interests") }
        P({ classes(Style.Section.subtitle, Style.smallMargin) }) { Text("tbd.") }
      }
    }
  }
}