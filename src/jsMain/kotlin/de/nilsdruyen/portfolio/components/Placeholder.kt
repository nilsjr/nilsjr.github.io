/*
 * Created by Nils Druyen on 10-11-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.dom.Div

@Composable
fun placeholder() {
  gridRow {
    Div({
      classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto)
      style { height(3.cssRem) }
    }) {
      Div({ classes(Style.borderX, Style.borderGray, Style.Grid.span4) }) {}
      Div({ classes(Style.borderR, Style.borderGray, Style.Grid.span2) }) {}
      Div({ classes(Style.borderR, Style.borderGray, Style.Grid.span1) }) {}
      Div({ classes(Style.borderR, Style.borderGray, Style.Grid.span5) }) {}
    }
  }
}