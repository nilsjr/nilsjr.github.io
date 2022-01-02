/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import de.nilsdruyen.portfolio.pages.AboutMe
import de.nilsdruyen.portfolio.pages.Intro
import de.nilsdruyen.portfolio.pages.Projects
import de.nilsdruyen.portfolio.pages.Skills
import de.nilsdruyen.portfolio.style.AppStylesheet
import de.nilsdruyen.portfolio.style.Component
import kotlinx.browser.window
import localUrl
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Composable
fun WebPage() {
  Routing()
}

@Composable
fun Routing() {
  val currentPath = mutableStateOf(window.location.pathname)

  LaunchedEffect(Unit) {
    window.onpopstate = {
      val newPath = window.location.pathname
      newPath.let {
        currentPath.value = it
      }
    }
  }

  Navigation()

  when (currentPath.value) {
    "/about" -> AboutMe()
    "/projects" -> Projects()
    "/skills" -> Skills()
    "/imprint" -> Intro()
    else -> Intro()
  }

  Footer()
}

@Composable
fun Navigation() {
  Nav({
    classes(Component.navBar)
  }) {
    Ul({
      classes(Component.linkGroup)
    }) {
      Li({
        classes(Component.linkItem, Component.activeLinkItem)
      }) {
        A(href = "$localUrl/") {
          Text("Home")
        }
      }
      Li({
        classes(Component.linkItem)
      }) {
        A(href = "$localUrl/about") {
          Text("About Me")
        }
      }
      Li({
        classes(Component.linkItem)
      }) {
        A(href = "$localUrl/projects") {
          Text("Projects")
        }
      }
      Li({
        classes(Component.linkItem)
      }) {
        A(href = "$localUrl/skills") {
          Text("Skills")
        }
      }
    }
  }
}

@Composable
fun Header() {
  org.jetbrains.compose.web.dom.Header({
    classes(AppStylesheet.pageHeader)
  }) {
    Text("Nils Druyen Portfolio")
  }
}

@Composable
fun Footer() {
  org.jetbrains.compose.web.dom.Footer({
    classes(AppStylesheet.pageFooter)
  }) {
    Text("Footer")
  }
}