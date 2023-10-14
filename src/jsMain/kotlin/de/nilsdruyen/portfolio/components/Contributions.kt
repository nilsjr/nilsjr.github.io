/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.model.Model
import de.nilsdruyen.portfolio.model.Project
import de.nilsdruyen.portfolio.ui.Style
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun contributions() {
  gridRow {
    Div({ classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto) }) {
      Div({
        classes(
          Style.borderGray,
          Style.paddingMedium,
          Style.Grid.borderX,
          Style.Grid.span12,
          Style.Section.dotted,
          Style.Section.lime,
        )
      }) {
        P({ classes(Style.Section.title) }) { Text("Contributions") }
        Div({ classes(Style.Projects.grid) }) {
          Model.contributions.forEach {
            project(it)
          }
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
      }
    }
  }
}