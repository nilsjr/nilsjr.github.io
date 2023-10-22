/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.model.Model
import de.nilsdruyen.portfolio.ui.Style
import de.nilsdruyen.portfolio.ui.fadeIn
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun work() {
  gridRow {
    Div({ classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto) }) {
      Div({ classes(Style.Grid.borderX, Style.borderGray, Style.Grid.work, Style.paddingMedium) }) {
        P({
          classes(Style.Section.title)
          style { fadeIn(100) }
        }) { Text("Work") }
        P({ classes(Style.Section.subtitle, Style.smallMargin) }) {
          Model.timeline.forEachIndexed { index, work ->
            P({
              classes(Style.AboutMe.description)
              style { fadeIn(200 + 200 * index) }
            }) { Text(work.timeFrame + " " + work.name) }
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
      Style.borderGray,
      Style.paddingMedium,
      Style.Section.gradient,
      Style.Grid.borderR,
      Style.Grid.borderMediumL,
      Style.Grid.interests,
      Style.Flex.column,
      Style.Flex.center,
      Style.Flex.gapSmall,
    )
    style { paddingBottom(1.cssRem) }
  }) {
    P({
      classes(Style.Section.title)
      style { fadeIn(200) }
    }) { Text("Interests") }
    Div({ classes(Style.smallMarginBottom, Style.Grid.interestsContainer) }) {
      Model.interests.forEachIndexed { index, interest ->
        Div({
          classes(Style.Flex.stretch)
          style { fadeIn(400 + 200 * index) }
        }) {
          A(
            href = interest.link,
            attrs = {
              target(ATarget.Blank)
              classes(Style.Work.interestLink)
            }
          ) {
            Img(
              src = "assets/${interest.iconName}.svg",
              attrs = {
                style {
                  height(interest.iconHeight.px)
                }
              }
            )
          }
        }
      }
    }
  }
}