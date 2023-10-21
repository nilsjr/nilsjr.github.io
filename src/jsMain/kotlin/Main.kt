/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.nilsdruyen.portfolio.page
import de.nilsdruyen.portfolio.ui.Style
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.set

const val KEY = "nilsjr.darkmode"

fun main() {
  renderComposable(rootElementId = "root") {
    var darkMode by remember {
      mutableStateOf((localStorage.getItem(KEY) ?: "false") == "true")
    }

    LaunchedEffect(darkMode) {
      localStorage[KEY] = darkMode.toString()
      console.log("mode set $darkMode")
    }

    Style(Style)
    Div({
      classes(if (darkMode) Style.dark else Style.light)
    }) {
      page(darkMode) { darkMode = !darkMode }
    }
  }
}