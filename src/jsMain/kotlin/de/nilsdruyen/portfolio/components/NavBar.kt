/*
 * Created by Nils Druyen on 01-06-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.models.Page
import de.nilsdruyen.portfolio.style.AppStyle
import de.nilsdruyen.portfolio.style.ButtonStyle
import localUrl
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.w3c.dom.url.URL

val pages = listOf(
  "/" to "Home",
  "/about" to "About Me",
  "/projects" to "Projects",
  "/skills" to "Skills",
  "/imprint" to "Imprint",
)

@Composable
fun NavBar(path: String, navigateToScreen: (URL) -> Unit) {
  val pages = pages.map { (pagePath, name) ->
    Page(URL("${localUrl}$pagePath"), name, pagePath == path)
  }

  Nav({
    classes(AppStyle.navBar)
  }) {
    Ul({
      classes(AppStyle.linkGroup)
    }) {
      pages.forEach { page ->
        Li {
          Button({
            classes(ButtonStyle.navButton)
            if (page.isActive) classes(ButtonStyle.navButtonActive)
            onClick {
              navigateToScreen(page.url)
            }
          }) { Text(page.name) }
        }
      }
    }
  }
}