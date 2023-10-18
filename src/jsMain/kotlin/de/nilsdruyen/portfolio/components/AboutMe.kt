/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.model.AboutMe
import de.nilsdruyen.portfolio.model.Experiment
import de.nilsdruyen.portfolio.model.Model
import de.nilsdruyen.portfolio.ui.Style
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun aboutMe() {
  gridRow {
    Div({ classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto) }) {
      description()
      image()
      experiments()
    }
  }
}

@Composable
private fun description() {
  Div({
    classes(
      Style.borderGray,
      Style.paddingMedium,
      Style.Grid.borderX,
      Style.Grid.description,
      Style.Flex.column,
    )
  }) {
    P({ classes(Style.Section.title) }) { Text(AboutMe.TITLE) }
    P({ classes(Style.AboutMe.description) }) { Text(AboutMe.FIRST_LINE) }
    P({ classes(Style.AboutMe.description) }) { Text(AboutMe.SECOND_LINE) }
    P({ classes(Style.AboutMe.description) }) { Text(AboutMe.THIRD_LINE) }
    Div({
      style {
        height(100.percent)
        minHeight(2.cssRem)
      }
    }) {}
    social()
  }
}

@Composable
private fun social() {
  Div({ classes(Style.AboutMe.social) }) {
    Model.links.forEachIndexed { index, socialLink ->
      A(
        href = socialLink.link,
        attrs = {
          target(ATarget.Blank)
          classes(Style.AboutMe.profileLink)
        }
      ) {
        Div({
          style {
            Style.AboutMe.socialFadeIn(this, 150 * index)
          }
        }) { socialLink.icon() }
      }
    }
  }
}

@Composable
private fun experiments() {
  Div({
    classes(
      Style.borderGray,
      Style.paddingMedium,
      Style.Section.blue,
      Style.Section.dotted,
      Style.Grid.borderR,
      Style.Grid.borderL,
      Style.Grid.borderT,
      Style.Grid.experiments,
    )
  }) {
    P({ classes(Style.Grid.span6, Style.Section.title) }) { Text("Experiments") }
    Div({ classes(Style.Grid.experimentsContainer) }) {
      Model.experiments.forEach { experiment(it) }
    }
  }
}

@Composable
private fun experiment(experiment: Experiment) {
  A(
    href = experiment.link,
    attrs = {
      classes(Style.Grid.span6, Style.Experiment.container)
    }
  ) {
    Div({ classes(Style.Experiment.containerOverlay) }) {
      P({ classes(Style.Section.title2) }) { Text(experiment.title) }
      P({ classes(Style.Section.subtitle, Style.smallMargin) }) { Text(experiment.subtitle) }
    }
  }
}