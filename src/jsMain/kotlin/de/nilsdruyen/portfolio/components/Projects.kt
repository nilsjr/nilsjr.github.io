/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.Model
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.model.Project
import de.nilsdruyen.portfolio.styles.Colors
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg
import org.jetbrains.compose.web.svg.fill
import org.jetbrains.compose.web.svg.height
import org.jetbrains.compose.web.svg.width

@Composable
fun projects() {
  gridRow {
    Div({ classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto) }) {
      Div({ classes(Style.borderX, Style.borderGray, Style.dotted, Style.Grid.span12, Style.pad1) }) {
        P({ classes(Style.Section.title) }) { Text("Projects") }
        Div({ classes(Style.Projects.grid) }) {
          Model.projects.forEach { project(it) }
        }
      }
    }
  }
}

@Composable
private fun project(project: Project) {
  Div({ classes(Style.Grid.span2, Style.Projects.container) }) {
    A(
      href = project.link,
      attrs = {
        target(ATarget.Blank)
        classes(Style.Projects.containerOverlay)
      }
    ) {
      Div({ classes(Style.Projects.containerInner) }) {
        P({ classes(Style.Section.title2) }) { Text(project.title) }
        P({ classes(Style.Section.subtitle, Style.Section.flexItem, Style.smallMargin) }) { Text(project.subtitle) }
        viewMore()
      }
    }
  }
}

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
private fun viewMore() {
  Div({ classes(Style.Projects.Button.container, Style.smallMargin) }) {
    Div({ classes(Style.Projects.Button.text) }) { Text("View more") }
    Div({ classes(Style.Projects.Button.icon) }) {
      Svg(
        viewBox = "0 0 8 8",
        attrs = {
          width(.5.cssRem)
          height(.5.cssRem)
          fill("none")
        }
      ) {
        Path(
          d = "M6.8291 6.82849L6.8291 1.17163M6.8291 1.17163L1.17225 1.17163M6.8291 1.17163L1.17188 6.82849",
          attrs = {
            style {
              property("stroke", Colors.Blue)
              property("stroke-width", "1.5")
              property("stroke-linecap", "round")
              property("stroke-linejoin", "round")
            }
          },
        )
      }
    }
  }
}