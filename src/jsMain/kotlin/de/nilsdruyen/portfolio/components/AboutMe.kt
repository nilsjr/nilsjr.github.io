/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.model.AboutMe
import de.nilsdruyen.portfolio.model.Experiment
import de.nilsdruyen.portfolio.model.Model
import de.nilsdruyen.portfolio.ui.Style
import kotlinx.coroutines.delay
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

val images = listOf(
  "nils",
  "nils02",
  "nils03",
  "nils04",
)

@Composable
private fun image() {
  var image by remember { mutableStateOf("nils") }

  LaunchedEffect(Unit) {
    while (true) {
      delay(5_000)
      val randomList = images.filter { it != image }
      image = randomList.random()
    }
  }

  Div({ classes(Style.borderR, Style.borderGray, Style.Grid.span4, Style.pad2, Style.Flex.container) }) {
    Img(
      src = "assets/nils.jpg",
      attrs = { classes(Style.profileImage, addStyle(image == "nils")) }
    )
    Img(
      src = "assets/nils02.jpg",
      attrs = { classes(Style.profileImage, addStyle(image == "nils02")) }
    )
    Img(
      src = "assets/nils03.jpg",
      attrs = { classes(Style.profileImage, addStyle(image == "nils03")) }
    )
    Img(
      src = "assets/nils04.jpg",
      attrs = { classes(Style.profileImage, addStyle(image == "nils04")) }
    )
  }
}

fun addStyle(show: Boolean) = if (show) Style.visible else Style.hidden

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