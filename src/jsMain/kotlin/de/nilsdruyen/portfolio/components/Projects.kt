/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.model.Model
import de.nilsdruyen.portfolio.ui.Style
import de.nilsdruyen.portfolio.ui.fadeIn
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun projects() {
  gridRow {
    Div({ classes(Style.Grid.col12, Style.maxWidth, Style.mxAuto) }) {
      Div({
        classes(
          Style.Grid.borderX,
          Style.borderGray,
          Style.Grid.span12,
          Style.Section.dotted,
          Style.Section.orange,
        )
      }) {
        Div({ classes(Style.paddingMedium) }) {
          P({
            classes(Style.Section.title)
            style { fadeIn(200) }
          }) { Text("Projects") }
          Div({ classes(Style.Projects.grid) }) {
            Model.projects.forEachIndexed { index, project ->
              project(project, true) {
                fadeIn(200 + 200 * index)
              }
            }
          }
        }
      }
    }
  }
}