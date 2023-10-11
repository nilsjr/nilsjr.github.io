/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.AboutMe
import de.nilsdruyen.portfolio.data.Model
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.model.Experiment
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
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
        classes(
          Style.borderX,
          Style.borderGray,
          Style.Grid.col4,
        )
      }) {
        P({ classes(Style.title2) }) { Text(AboutMe.TITLE) }
        P({ classes(Style.subtitle) }) { Text(AboutMe.FIRST_LINE) }
        P({ classes(Style.subtitle) }) { Text(AboutMe.SECOND_LINE) }
        P({ classes(Style.subtitle) }) { Text(AboutMe.THIRD_LINE) }
      }
      Div({
        style {
          display(DisplayStyle.Flex)
          justifyContent(JustifyContent.Center)
          alignItems(AlignItems.Center)
          padding(16.px)
        }
        classes(
          Style.borderR,
          Style.borderGray,
          Style.Grid.col4,
        )
      }) {
        Img(
          src = "assets/nils.jpg",
          attrs = {
            classes(Style.profileImage)
          }
        )
      }
      Div({
        style {
          padding(16.px)
          display(DisplayStyle.Grid)
          gridTemplateColumns("repeat(6,minmax(0,1fr))")
          gap(1.cssRem)
        }
        classes(
          Style.borderR,
          Style.borderGray,
          Style.dotted,
          Style.Grid.col4,
        )
      }) {
        P({
          classes(
            Style.Grid.col6,
            Style.Section.title,
          )
        }) { Text("Experiments") }
        Model.experiments.forEach { experiment(it) }
      }
    }
  }
}

@Composable
private fun experiment(experiment: Experiment) {
  A(
    href = experiment.link,
    attrs = {
      classes(
        Style.Grid.col6,
        Style.Experiment.container,
      )
    }
  ) {
    Div({
      classes(Style.Experiment.containerOverlay)
    }) {
      P({ classes(Style.Section.title2) }) { Text(experiment.title) }
      P({ classes(Style.Section.subtitle, Style.Section.margin) }) { Text(experiment.subtitle) }
    }
  }
}