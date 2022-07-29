/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.css.width

object HomeStyle : StyleSheet(AppStylesheet) {

  val section by style {
    width(100.percent)
    height(100.vh)
    padding(0.px, 150.px)
    display(DisplayStyle.Flex)
    alignItems(AlignItems.Center)
    position(Position.Fixed)
    top(0.px)
    background(Colors.Grey)

//    opacity(0)
//    property("transition", "1s")
//
//    desc(self, sectionActive) style {
//      position(Position.Relative)
//      opacity(1)
//      property("z-index", "8")
//    }
  }

  val heroHeading by style {
    color(Color("#fff"))
    fontSize(120.px)
    property("text-transform", "capitalize")
    fontWeight(300)
  }

  val image by style {
    position(Position.Absolute)
    top(0.px)
    right(0.px)
    height(100.vh)
    width(50.percent)
    property("object-fit", "cover")
    opacity(.2)
  }
}