/*
 * Created by Nils Druyen on 01-06-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.models.NavItem
import de.nilsdruyen.portfolio.style.AppStyle
import de.nilsdruyen.portfolio.style.ButtonStyle
import kotlinx.browser.window
import localUrl
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.url.URL

data class PageSection(
  val id: String,
  val name: String,
)

val pages = listOf(
  "/" to "Home",
  "/projects" to "Projects",
  "/about" to "About",
//  "/skills" to "Skills",
//  "/experience" to "Experience",
)

@Composable
fun NavBar(navigateToScreen: (URL) -> Unit) {
  val pages = pages.map { (pagePath, name) ->
    NavItem(
      URL("${localUrl}$pagePath"),
      name,
//      pagePath == path
    )
  }

  Nav({ classes(AppStyle.navBar) }) {
    Ul({ classes(AppStyle.linkGroup) }) {
      pages.forEach { page ->
        Li {
          Button({
            classes(ButtonStyle.navButton)
//            if (page.isActive) classes(ButtonStyle.navButtonActive)
            onClick {
              window.document.getElementById("test1")?.scrollIntoView(ScrollBehavior.SMOOTH)
              navigateToScreen(page.url)
            }
          }) { Text(page.name) }
        }
      }
    }
  }
}