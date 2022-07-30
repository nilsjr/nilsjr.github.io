/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import de.nilsdruyen.portfolio.components.AboutMe
import de.nilsdruyen.portfolio.components.Footer
import de.nilsdruyen.portfolio.components.Header
import de.nilsdruyen.portfolio.components.Maintainance
import de.nilsdruyen.portfolio.components.Navigation
import de.nilsdruyen.portfolio.components.Projects
import de.nilsdruyen.portfolio.components.References

@Composable
fun WebPage() {
  var showError by mutableStateOf(false)

  println("recompose")

  if (showError) {
    Maintainance()
  } else {
    Navigation {
      showError = it
    }
    Header()
    AboutMe()
    References()
    Projects()
    Footer()
  }
}