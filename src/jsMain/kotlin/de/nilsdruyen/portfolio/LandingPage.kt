/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import de.nilsdruyen.portfolio.pages.Intro
import kotlinx.browser.window

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

  when (currentPath.value) {
    "/about" -> Intro()
    "/projects" -> Intro()
    "/skills" -> Intro()
    "/imprint" -> Intro()
    else -> Intro()
  }
}