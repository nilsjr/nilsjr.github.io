/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

import de.nilsdruyen.portfolio.WebPage
import de.nilsdruyen.portfolio.style.AppStylesheet
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
  renderComposable(rootElementId = "root") {
    Style(AppStylesheet)
    WebPage()
  }
}