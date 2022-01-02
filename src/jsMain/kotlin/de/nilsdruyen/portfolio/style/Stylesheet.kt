/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.listStyle
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.width

object AppStylesheet : StyleSheet() {

  init {
    universal style {
      margin(0.px)
      padding(0.px)
      boxSizing("border-box")
    }

    "body" style {
      width(100.percent)
      maxWidth(1400.px)
      display(DisplayStyle.Block)
      property("margin", "auto")
      background("#191919")
      property("font-family", FontConst.DEFAULT)
    }
  }

  val pageHeader by style {
    padding(22.px)
    textAlign("center")
    fontSize(28.px)
  }

  val pageFooter by style {
    padding(4.px)
  }
}

object Component : StyleSheet(AppStylesheet) {

  val navBar by style {
    width(100.percent)
    position(Position.Fixed)
    top(0.px)
    left(0.px)
    display(DisplayStyle.Flex)
    justifyContent(JustifyContent.Center)
    alignItems(AlignItems.Center)
    property("z-index", "9")
    background("#1a1a1a")
  }

  val linkGroup by style {
    listStyle("none")
    display(DisplayStyle.Flex)
  }

  val linkItem by style {
    self + " a" style {
      color(Color("#fff"))
      opacity(0.5)
      textDecoration("none")
      property("text-transform", "capitalize")
      padding(10.px, 30.px)
      margin(0.px, 20.px)
      lineHeight(80.px)
      property("transition", ".5s")
      fontSize(20.px)

      self + hover style {
        opacity(1)
      }
    }
  }

  val activeLinkItem by style {
    self + " a" style {
      opacity(1)
    }
  }
  val centerImage by style {
    position(Position.Absolute)
    top(0.px)
    left(0.px)
    right(0.px)
    bottom(0.px)
    property("margin", auto)
    width(50.percent)
    maxWidth(400.px)
  }
}