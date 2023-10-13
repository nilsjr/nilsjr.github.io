/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.model.Model
import de.nilsdruyen.portfolio.ui.Style
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun work() {
  gridRow {
    Div({ classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto) }) {
      Div({ classes(Style.borderX, Style.borderGray, Style.Grid.span5, Style.pad1) }) {
        P({ classes(Style.Section.title) }) { Text("Work") }
        P({ classes(Style.Section.subtitle, Style.smallMargin) }) {
          Model.timeline.forEach {
            P({ classes(Style.AboutMe.description) }) { Text(it.timeFrame + " " + it.name) }
          }
        }
      }
      interests()
    }
  }
}

@Composable
private fun interests() {
  Div({
    classes(
      Style.borderR,
      Style.borderGray,
      Style.Section.gradient,
      Style.Grid.span7,
      Style.pad1,
      Style.Flex.column,
      Style.Flex.center,
      Style.Flex.gap1,
    )
    style { paddingBottom(1.cssRem) }
  }) {
    P({ classes(Style.Section.title) }) { Text("Interests") }
    Div({
      classes(Style.Flex.row, Style.smallMarginBottom)
      style { width(80.percent) }
    }) {
      Model.interests.forEach {
        Div({ classes(Style.Flex.stretch) }) {
          A(
            href = it.link,
            attrs = {
              target(ATarget.Blank)
              classes(Style.Work.interestLink)
            }
          ) {
            Img(
              src = "assets/${it.iconName}.svg",
              attrs = {
                style {
                  height(it.iconHeight.px)
                }
              }
            )
          }
        }
      }
    }
  }
}