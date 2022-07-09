/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.position

object SectionStyle : StyleSheet(AppStylesheet) {

//  val section by style {
//    width(100.percent)
//    minHeight(100.percent)
//    padding(150.px, 100.px, 100.px)
//    position(Position.Fixed)
//    top(0.px)
//
//    opacity(0)
//    property("transition", "1s")
//  }

  val sectionActive by style {
    position(Position.Relative)
    opacity(1)
    property("z-index", "8")
  }
}