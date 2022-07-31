/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.Constants
import de.nilsdruyen.portfolio.models.Project
import de.nilsdruyen.portfolio.styles.WebPageStyle
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

val projects = listOf(
  Project(
    name = "WoW Mythic+ Dashboard",
    description = """
    This is a small webpage for summarizing specific wow character statistics from Raider.IO
     """.trimIndent(),
    imageUrl = "assets/projects/helm.jpg",
//    linkGithubText = "github repo",
    linkGithub = "",
//    linkProjectText = "",
    linkProject = "",
  ),
  Project(
    name = "My board games",
    description = Constants.LOREM,
    imageUrl = "assets/projects/helm.jpg",
//    linkGithubText = "github repo",
    linkGithub = "https://github.com/nilsjr/MyBoardGames",
//    linkProjectText = "see live",
    linkProject = "https://github.com/nilsjr/MyBoardGames",
  ),
  Project(
    name = "Gradle FTP upload plugin",
    description = Constants.LOREM,
    imageUrl = "assets/projects/helm.jpg",
//    linkGithubText = "github repo",
    linkGithub = "https://github.com/nilsjr/gradle-ftp-upload-plugin",
//    linkProjectText = "",
    linkProject = "https://plugins.gradle.org/plugin/de.nilsdruyen.gradle-ftp-upload-plugin",
  ),
)

@Composable
fun Projects() {
  val openLink: (String) -> Unit = {
    window.open(url = it, target = "_blank")
  }

  Section({
    classes(WebPageStyle.Projects.section)
    id("projects")
  }) {
    H1({ classes(WebPageStyle.Projects.projectHeading) }) {
      Text("some of my projects")
    }
    Div({ classes(WebPageStyle.Projects.projectContainer) }) {
      projects.forEach {
        ProjectCard(it, openLink)
      }
    }
  }
}

@Composable
fun ProjectCard(project: Project, openLink: (url: String) -> Unit = {}) {
  Div({ classes(WebPageStyle.Projects.projectCard) }) {
    Img(project.imageUrl, attrs = { classes(WebPageStyle.Projects.projectImg) })
    Div({ classes(WebPageStyle.Projects.projectContent) }) {
      H1({ classes(WebPageStyle.Projects.projectTitle) }) {
        Text(project.name)
      }
//      P({ classes(WebPageStyle.Projects.projectInfo) }) {
//        Text(project.description)
//      }
      Button({
        classes(WebPageStyle.Projects.projectBtn)
        onClick {
          openLink(project.linkProject)
        }
      }) {
        Text("Link")
      }
//      Div({ classes(WebPageStyle.Projects.projectBtnGrp) }) {
////        Button({
////          classes(WebPageStyle.Projects.projectBtn)
////          onClick {
////            openLink(project.linkGithub)
////          }
////        }) {
////          Text("Link")
////        }
//        Button({
//          classes(WebPageStyle.Projects.projectBtnLive)
//          onClick {
//            openLink(project.linkProject)
//          }
//        }) {
//          Text("Link")
//        }
//      }
    }
  }
}