/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import de.nilsdruyen.portfolio.components.Navigation
import de.nilsdruyen.portfolio.pages.About
import de.nilsdruyen.portfolio.pages.Home
import de.nilsdruyen.portfolio.pages.Maintainance
import de.nilsdruyen.portfolio.pages.Projects

@Composable
fun WebPage() {
  Routing()
}

@Composable
fun Routing() {
  var showError by mutableStateOf(false)

  println("recompose")

  Navigation {
    showError = it
  }

  Home()
  Projects()
  About()

  if (showError) {
    Maintainance()
  }

//  Crossfade(currentPath.value) { path ->
//    when (path) {
//      "/" -> Home()
//      "/projects" -> Projects()
//      "/about" -> About()
//    }
//  }
}