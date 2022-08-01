/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.nilsdruyen.portfolio.data.pages
import de.nilsdruyen.portfolio.styles.AppStyle
import de.nilsdruyen.portfolio.styles.ButtonStyle
import kotlinx.browser.window
import localUrl
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Composable
fun Navigation() {
  val pages = remember { mutableStateOf(pages) }

  val selectPage: (String) -> Unit = { hash ->
    pages.value = pages.value.map {
      it.copy(isActive = it.hash == hash)
    }
    if (hash.isEmpty()) {
      window.history.pushState("", "", localUrl)
      window.scrollTo(0.0, 0.0)
    } else {
      window.document.getElementById(hash)?.scrollIntoView()
      window.location.hash = hash
    }
  }

  Nav({ classes(AppStyle.navBar) }) {
    Ul({ classes(AppStyle.linkGroup) }) {
      pages.value.forEach { page ->
        val style = if (page.isActive) {
          ButtonStyle.navButtonActive
        } else {
          ButtonStyle.navButton
        }

        Li {
          Button({
            classes(style)
            onClick { selectPage(page.hash) }
          }) {
            Text(page.name)
          }
        }
      }
    }
  }
}