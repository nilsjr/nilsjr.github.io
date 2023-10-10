/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.AboutMe
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gridColumn
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun aboutMe() {
  gridRow {
    Div({
      classes(
        Style.gridCol12,
        Style.maxWidth,
        Style.mxAuto,
      )
    }) {
      Div({
        classes(Style.borderX, Style.borderGray)
        style { gridColumn("span 8/span 8") }
      }) {
        P({ classes(Style.title2) }) { Text(AboutMe.TITLE) }
        P({ classes(Style.subtitle) }) { Text(AboutMe.FIRST_LINE) }
        P({ classes(Style.subtitle) }) { Text(AboutMe.SECOND_LINE) }
        P({ classes(Style.subtitle) }) { Text(AboutMe.THIRD_LINE) }
      }
      Div({
        style {
          gridColumn("span 4/span 4")
          display(DisplayStyle.Flex)
          justifyContent(JustifyContent.Center)
          alignItems(AlignItems.Center)
          padding(16.px)
        }
        classes(
          Style.borderR,
          Style.borderGray,
        )
      }) {
        Img(
          src = "assets/nils.jpg",
          attrs = {
            classes(Style.profileImage)
          }
        )
      }
    }
  }
}