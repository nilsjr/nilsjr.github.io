/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textAlign
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
      Div({ classes(Style.borderX, Style.borderGray, Style.Grid.span12, Style.Flex.container) }) {
        // FIXME: cleanup code & move to stylesheet
        P({
          style {
            textAlign("center")
            property("margin", "auto")
            opacity(.5)
            fontWeight(700)
          }
        }) {
          Text("built with kotlin multiplatform")
        }
        P({
          style {
            textAlign("center")
            property("margin", "auto")
            opacity(.5)
            fontWeight(700)
          }
        }) {
          Text("© 2023 Nils Druyen")
        }
      }
    }
  }
}