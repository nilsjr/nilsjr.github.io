/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

@file:Suppress("FunctionName")

package de.nilsdruyen.portfolio.styles

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.backgroundImage
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.letterSpacing
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.width

object WebPageStyle : StyleSheet() {

  init {
    universal style {
      margin(0.px)
      padding(0.px)
      boxSizing("border-box")

      border {
        width = 0.px
        style = LineStyle.Solid
        color = Color("#48515e")
      }
    }

    "html" style {
      property("scroll-behavior", "smooth")
    }

    "body" style {
      width(100.percent)
      display(DisplayStyle.Block)
      fontFamily("Roboto", "sans-serif")
      property("margin", "auto")
    }

    "a" style {
      textDecoration("none")
      color(Colors.Blue.toColor())
      fontSize(2.cssRem)
      fontWeight(500)
    }
  }

  val borderB by style {
    property("border-bottom-width", "1px")
  }

  val borderL by style {
    property("border-left-width", "1px")
  }

  val borderR by style {
    property("border-right-width", "1px")
  }

  val borderX by style {
    property("border-left-width", "1px")
    property("border-right-width", "1px")
  }

  val borderGray by style {
    property("--tw-border-opacity", "1")
    property("border-color", "rgb(215 221 228/var(--tw-border-opacity))")
  }

  val grid by style {
    gridTemplateColumns("repeat(12,minmax(0,1fr))")
    display(DisplayStyle.Grid)
    paddingLeft(2.5.cssRem)
    paddingRight(2.5.cssRem)
  }

  val gridTop by style {
    gridTemplateColumns("repeat(12,minmax(0,1fr))")
    display(DisplayStyle.Grid)
    height(3.cssRem)
    paddingLeft(2.5.cssRem)
    paddingRight(2.5.cssRem)
  }

  val title2 by style {
    fontSize(2.cssRem)
    padding(16.px)
    color("#050608")
  }

  val subtitle by style {
    paddingLeft(16.px)
    paddingRight(16.px)
    paddingBottom(16.px)
    color("#050608")
    opacity(80.percent)
  }

  @OptIn(ExperimentalComposeWebApi::class)
  val profileImage by style {
    width(100.percent)
    borderRadius(2.cssRem)
    border {
      width = 1.px
      style = LineStyle.Solid
      color = Color("#eee")
    }
    backgroundImage("assets/nils.jpg")
    opacity(80.percent)
    filter {
      grayscale(1)
    }
    property("transition", "all .5s ease")

    self + hover style {
      filter {
        grayscale(0)
      }
    }
  }

  val title by style {
    fontSize(5.cssRem)
    letterSpacing(4.px)
    fontWeight(500)
    marginLeft(20.px)
  }
}