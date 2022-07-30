/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.nilsdruyen.portfolio.models.NavItem
import de.nilsdruyen.portfolio.styles.AppStyle
import de.nilsdruyen.portfolio.styles.ButtonStyle
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.dom.addClass
import kotlinx.dom.removeClass
import localUrl
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.w3c.dom.Element
import org.w3c.dom.asList
import org.w3c.dom.url.URL

val pages = listOf(
  "/" to "Home",
  "/projects" to "Projects",
  "/about" to "About",
)

fun String.mapToIndex() = when (this) {
  "/" -> 0
  "/projects" -> 1
  "/about" -> 2
  else -> -1
}

@Composable
fun Navigation(showError: (Boolean) -> Unit = {}) {
  val currentPath = remember { mutableStateOf(window.location.pathname) }

  val navigateAction: (URL) -> Unit = {
    println("navigate: $it")
    window.history.pushState("", "", it.toString())
    currentPath.value = it.pathname
  }

  LaunchedEffect(currentPath.value) {
    val sections = window.document.querySelectorAll("section")
      .asList()
      .filterIsInstance<Element>()
    val navButtons = window.document.getElementsByClassName(ButtonStyle.navButton)
      .asList()

    println("Sections: ${sections.size} / Buttons: ${navButtons.size}")

    val currentPage = currentPath.value.mapToIndex()

    sections.forEach { it.removeClass("active") }
    navButtons.forEach { it.removeClass("active") }

    if (currentPage >= 0) {
      delay(1000)
      sections[currentPage].addClass("active")
      navButtons[currentPage].addClass("active")
    } else {
      showError(true)
    }
  }

  val pages = pages.map { (pagePath, name) ->
    NavItem(
      url = URL("${localUrl}$pagePath"),
      name = name,
    )
  }

  Nav({ classes(AppStyle.navBar) }) {
    Ul({ classes(AppStyle.linkGroup) }) {
      pages.forEach { page ->
        Li {
          Button({
            classes(ButtonStyle.navButton)
            onClick { navigateAction(page.url) }
          }) { Text(page.name) }
        }
      }
    }
  }
}