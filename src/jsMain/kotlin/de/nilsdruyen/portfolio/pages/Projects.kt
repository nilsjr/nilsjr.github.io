/*
 * Created by Nils Druyen on 01-02-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.pages

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.models.Project
import de.nilsdruyen.portfolio.style.ProjectStyle
import de.nilsdruyen.portfolio.utils.Constants
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

val projects = listOf(
  Project(
    name = "WoW Mythic+ Dashboard",
    description = """
    This is a small webpage for summarizing specific wow character statistics from Raider.IO
     """.trimIndent(),
//    linkGithubText = "github repo",
    linkGithub = "",
//    linkProjectText = "",
    linkProject = "",
  ),
  Project(
    name = "My board games",
    description = Constants.LOREM,
//    linkGithubText = "github repo",
    linkGithub = "https://github.com/nilsjr/MyBoardGames",
//    linkProjectText = "see live",
    linkProject = "https://github.com/nilsjr/MyBoardGames",
  ),
  Project(
    name = "Gradle FTP upload plugin",
    description = Constants.LOREM,
//    linkGithubText = "github repo",
    linkGithub = "https://github.com/nilsjr/gradle-ftp-upload-plugin",
//    linkProjectText = "",
    linkProject = "https://plugins.gradle.org/plugin/de.nilsdruyen.gradle-ftp-upload-plugin",
  ),
)

@Composable
fun Projects(openLink: (url: String) -> Unit = {}) {
  Section({
    classes(ProjectStyle.projectSection)
  }) {
    H1({
      classes(ProjectStyle.projectHeading)
    }) {
      Text("some of my projects")
    }
    Div({
      classes(ProjectStyle.projectContainer)
    }) {
      projects.forEach {
        ProjectCard(it, openLink)
      }
    }
  }
}

@Composable
fun ProjectCard(project: Project, openLink: (url: String) -> Unit = {}) {
  Div({
    classes(ProjectStyle.projectCard)
  }) {
    Img("", attrs = {
      classes(ProjectStyle.projectImg)
    })
    Div({
      classes(ProjectStyle.projectContent)
    }) {
      H1({
        classes(ProjectStyle.projectTitle)
      }) {
        Text(project.name)
      }
      P({
        classes(ProjectStyle.projectInfo)
      }) {
        Text(project.description)
      }
      Div({
        classes(ProjectStyle.projectBtnGrp)
      }) {
        Button({
          classes(ProjectStyle.projectBtn)
          onClick {
            openLink(project.linkGithub)
          }
        }) {
          Text("Link")
        }
        Button({
          classes(ProjectStyle.projectBtnLive)
          onClick {
            openLink(project.linkProject)
          }
        }) {
          Text("Link")
        }
      }
    }
  }
}