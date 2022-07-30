/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

import de.nilsdruyen.portfolio.WebPage
import de.nilsdruyen.portfolio.styles.WebPageStyle
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

val localUrl = "${window.location.protocol}//${window.location.host}"

fun main() {
  renderComposable(rootElementId = "root") {
    Style(WebPageStyle)
    WebPage()
  }
}