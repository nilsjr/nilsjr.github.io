/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundPosition
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.width

object HeaderStyle : StyleSheet(AppStylesheet) {

  val section by style {
    width(100.percent)
    height(600.px)
    top(0.px)
    padding(100.px, 0.px)
    display(DisplayStyle.Table)
    alignItems(AlignItems.Center)
  }

  val background by style {
    width(100.percent)
    height(400.px)
    position(Position.Relative)
//    padding(0.px, 150.px)
//    display(DisplayStyle.Flex)
//    alignItems(AlignItems.Center)
//    position(Position.Fixed)
//    top(0.px)
    background(Colors.Grey)
  }

  val profileImage by style {
    width(400.px)
    height(400.px)
    property("margin", "-200px auto 0")
    position(Position.Relative)
    backgroundPosition("50% 50%")
    borderRadius(50.percent)
    border {
      width = 8.px
      style = LineStyle.Solid
      color = Color("#fff")
    }
  }
}