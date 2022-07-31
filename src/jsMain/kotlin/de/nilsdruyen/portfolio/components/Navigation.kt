/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.nilsdruyen.portfolio.models.NavItem
import de.nilsdruyen.portfolio.styles.AppStyle
import de.nilsdruyen.portfolio.styles.ButtonStyle
import kotlinx.browser.window
import kotlinx.dom.addClass
import kotlinx.dom.removeClass
import localUrl
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.w3c.dom.asList

val pages = listOf(
  NavItem("Home", ""),
  NavItem("About", "about"),
  NavItem("Projects", "projects"),
)

@Composable
fun Navigation() {
  val selectedHash = remember { mutableStateOf("") }
  val selectedIndex = derivedStateOf {
    pages.indexOfFirst { it.hash == selectedHash.value }
  }

  val scrollToHash: (String) -> Unit = {
    selectedHash.value = it
    if (it.isEmpty()) {
      window.history.pushState("", "", localUrl)
      window.scrollTo(0.0, 0.0)
    } else {
      val element = window.document.getElementById(it)
      element?.scrollIntoView()
      window.location.hash = it
    }
  }

  LaunchedEffect(selectedIndex.value) {
    val navButtons = window.document.getElementsByClassName(ButtonStyle.navButton)
      .asList()
    navButtons.forEach { it.removeClass("active") }
    navButtons[selectedIndex.value].addClass("active")
  }

  Nav({ classes(AppStyle.navBar) }) {
    Ul({ classes(AppStyle.linkGroup) }) {
      pages.forEach { page ->
        Li {
          Button({
            classes(ButtonStyle.navButton)
            onClick { scrollToHash(page.hash) }
          }) { Text(page.name) }
        }
      }
    }
  }
}