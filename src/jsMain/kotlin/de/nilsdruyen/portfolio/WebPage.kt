/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.components.aboutMe
import de.nilsdruyen.portfolio.components.footer
import de.nilsdruyen.portfolio.components.projects
import de.nilsdruyen.portfolio.components.work
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gridColumn
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun page() {
  divider()
  title()
  aboutMe()
  work()
  projects()
  footer()
}

@Composable
private fun divider() {
  gridRow {
    Div({
      classes(
        Style.gridTop,
        Style.maxWidth,
        Style.mxAuto,
      )
    }) {
      Div({
        classes(Style.borderX, Style.borderGray)
        style { gridColumn("span 4/span 4") }
      }) {}
      Div({
        style { gridColumn("span 2/span 2") }
        classes(Style.borderR, Style.borderGray)
      }) {}
      Div({
        style { gridColumn("span 1/span 1") }
        classes(Style.borderR, Style.borderGray)
      }) {}
      Div({
        style { gridColumn("span 5/span 5") }
        classes(Style.borderR, Style.borderGray)
      }) {}
    }
  }
}

@Composable
private fun title() {
  Header({
    classes(
      Style.borderB,
      Style.borderGray,
    )
  }) {
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
        style {
          gridColumn("span 6/span 6")
          display(DisplayStyle.Flex)
          alignItems("center")
        }
      }) {
        P({ classes(Style.title) }) {
          Text("nilsjr.")
        }
      }
      Div({
        style { gridColumn("span 1/span 1") }
        classes(Style.borderR, Style.borderGray)
      }) {}
      Div({
        style { gridColumn("span 5/span 5") }
        classes(Style.borderR, Style.borderGray)
      }) {}
    }
  }
}