/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.model.AboutMe
import de.nilsdruyen.portfolio.model.Experiment
import de.nilsdruyen.portfolio.model.Model
import de.nilsdruyen.portfolio.ui.Style
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
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
  Div({ classes(Style.borderX, Style.borderGray, Style.Grid.span4, Style.pad2, Style.Flex.column) }) {
    P({ classes(Style.Section.title) }) { Text(AboutMe.TITLE) }
    P({ classes(Style.AboutMe.description) }) { Text(AboutMe.FIRST_LINE) }
    P({ classes(Style.AboutMe.description) }) { Text(AboutMe.SECOND_LINE) }
    P({ classes(Style.AboutMe.description) }) { Text(AboutMe.THIRD_LINE) }
    social()
  }
}

@Composable
private fun social() {
  Div({ classes(Style.AboutMe.social) }) {
    Model.links.forEach {
      A(
        href = it.link,
        attrs = {
          target(ATarget.Blank)
          classes(Style.AboutMe.profileLink)
        }
      ) { it.icon() }
    }
  }
}

@Composable
private fun image() {
  Div({ classes(Style.borderR, Style.borderGray, Style.Grid.span4, Style.pad2, Style.Flex.container) }) {
    Img(
      src = "assets/nils.jpg",
      attrs = { classes(Style.profileImage) }
    )
  }
}

@Composable
private fun experiments() {
  Div({
    classes(
      Style.borderR,
      Style.borderGray,
      Style.Section.blue,
      Style.Section.dotted,
      Style.Grid.span4,
      Style.Grid.col6,
      Style.pad2,
    )
  }) {
    P({ classes(Style.Grid.span6, Style.Section.title) }) { Text("Experiments") }
    Model.experiments.forEach { experiment(it) }
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