/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.Style
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun footer() {
  Footer({ classes(Style.borderB, Style.borderGray) }) {
    Div({
      classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto)
      style { height(120.px) }
    }) {
      Div({ classes(Style.borderX, Style.borderGray, Style.Grid.span12, Style.Flex.column, Style.Flex.alignCenter) }) {
        P({ classes(Style.Footer.text) }) { Text("built with love & kotlin multiplatform <3") }
        Br { }
        P({ classes(Style.Footer.text) }) { Text("© 2023 Nils Druyen") }
      }
    }
  }
}