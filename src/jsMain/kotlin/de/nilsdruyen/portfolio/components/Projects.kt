/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.models.Project
import de.nilsdruyen.portfolio.data.projects
import de.nilsdruyen.portfolio.styles.WebPageStyle
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
fun Projects() {
  val openLink: (String) -> Unit = {
    window.open(url = it, target = "_blank")
  }

  Section({
    id("projects")
    classes(WebPageStyle.Projects.section)
  }) {
    H1({ classes(WebPageStyle.Projects.projectHeading) }) {
      Text("some of my projects")
    }
    Div({ classes(WebPageStyle.Projects.projectContainer) }) {
      projects.forEach {
        ProjectItem(it, openLink)
      }
    }
  }
}

@Composable
fun ProjectItem(project: Project, openLink: (url: String) -> Unit) {
  Div({
    classes(WebPageStyle.Projects.projectItemContainer)
  }) {
    Div({
      classes(WebPageStyle.Projects.projectItemContent)
    }) {
      H1({ classes(WebPageStyle.Projects.projectTitle) }) {
        Text(project.name)
      }
      Button({
        classes(WebPageStyle.Projects.projectBtn)
        onClick {
          openLink(project.link)
        }
      }) {
        Text("Show me")
      }
    }
  }
}