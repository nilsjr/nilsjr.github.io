/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.css.width

object AboutStyle : StyleSheet(AppStylesheet) {

  val section by style {
    width(100.percent)
    minHeight(100.vh)
    padding(150.px, 100.px, 0.px)
    position(Position.Fixed)
    top(0.px)

    opacity(0)
    property("transition", "1s")

    desc(self, active) style {
      position(Position.Relative)
      opacity(1)
      property("z-index", "8")
    }
  }

  val container by style {
    width(100.percent)
    display(DisplayStyle.Grid)
    gridTemplateColumns("30% 65%")
    property("grid-gap", "40px")
  }

  val imgContainer by style {
    position(Position.Relative)
  }

  val info by style {
    color(Color("#fff"))
    opacity(0.6)
    fontSize(20.px)
    lineHeight(40.px)
  }

  val img by style {
    width(100.percent)
    height(100.percent)
    property("object-fit", "cover")
    borderRadius(20.px)
  }

  @OptIn(ExperimentalComposeWebApi::class)
  val githubLink by style {
    position(Position.Absolute)
    bottom(20.px)
    left(50.percent)
    transform {
      translateX((-50).percent)
    }
    padding(10.px, 20.px)
    color(Color("#fff"))
    property("border", "none")
    fontSize(16.px)
    property("text-transform", "capitalize")
    cursor("pointer")
    property("transition", ".5s")
    backgroundColor(rgba(0, 0, 0, 0.5))

    self + hover style {
      background("#000")
    }
  }
}