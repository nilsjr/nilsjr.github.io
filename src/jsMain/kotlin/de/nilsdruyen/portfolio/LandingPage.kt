/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.nilsdruyen.portfolio.components.NavBar
import de.nilsdruyen.portfolio.pages.AboutMe
import de.nilsdruyen.portfolio.pages.Intro
import de.nilsdruyen.portfolio.pages.Maintainance
import de.nilsdruyen.portfolio.pages.Projects
import de.nilsdruyen.portfolio.pages.Skills
import kotlinx.browser.window
import org.w3c.dom.url.URL

@Composable
fun WebPage() {
  Routing()
}

@Composable
fun Routing() {
  val currentPath = remember { mutableStateOf(window.location.pathname) }

  LaunchedEffect(Unit) {
    window.onpopstate = {
      val newPath = window.location.pathname
      newPath.let {
        println("new path: $newPath")
        currentPath.value = it
      }
    }
  }

  val navigateAction: (URL) -> Unit = {
    println("navigate: $it")
    window.history.pushState("", "", it.toString())
    currentPath.value = it.pathname
  }

  NavBar(currentPath.value, navigateAction)

  when (currentPath.value) {
    "/" -> Intro()
    "/about" -> AboutMe()
    "/projects" -> Projects()
    "/skills" -> Skills()
    "/imprint" -> Intro()
    else -> Maintainance()
  }
}