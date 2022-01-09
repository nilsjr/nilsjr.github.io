/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textAlign

object TextStyle : StyleSheet(AppStylesheet) {

  val heading by style {
    textAlign("center")
    fontSize(60.px)
    color(Color.white)
    property("text-transform", "capitalize")
    fontWeight(300)
    marginBottom(100.px)
  }
}