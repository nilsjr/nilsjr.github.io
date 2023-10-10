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
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.backgroundPosition
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
import org.jetbrains.compose.web.css.gridColumn
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.letterSpacing
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.width

object Style : StyleSheet() {

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


  val gridCol12 by style {
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

  val maxWidth by style {
    maxWidth(90.cssRem)
  }

  val mxAuto by style {
    property("margin-left", "auto")
    property("margin-right", "auto")
  }

  @OptIn(ExperimentalComposeWebApi::class)
  val profileImage by style {
    width(100.percent)
    borderRadius(1.cssRem)
    border {
      width = 1.px
      style = LineStyle.Solid
      color = Color("#d7dde4")
    }
    opacity(80.percent)
    filter {
//      grayscale(1)
    }
    property("transition", "all .5s ease")
    self + hover style {
      filter {
        grayscale(0)
      }
    }
  }

  val title by style {
    fontSize(6.cssRem)
    letterSpacing(6.px)
    fontWeight(600)
    marginLeft(32.px)
  }

  val dotted by style {
    backgroundPosition("50%")
    backgroundColor(rgba(242, 246, 250, .2))
    property(
      "background-image",
      "url(\"data:image/svg+xml,%3Csvg width='16' height='16' viewBox='0 0 16 16' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M1.5 0.75C1.5 1.16421 1.16421 1.5 0.75 1.5C0.335786 1.5 0 1.16421 0 0.75C0 0.335786 0.335786 0 0.75 0C1.16421 0 1.5 0.335786 1.5 0.75Z' fill='%23D7DDE4'/%3E%3C/svg%3E%0A\")"
    )
  }

  object Grid : StyleSheet(Style) {

    val col12 by style {
      gridColumn("span 12/span 12")
    }
    val col6 by style {
      gridColumn("span 6/span 6")
    }
    val col4 by style {
      gridColumn("span 4/span 4")
    }
    val col2 by style {
      gridColumn("span 2/span 2")
    }
  }

  object Section : StyleSheet(Style) {

    val title by style {
      fontSize(24.px)
      color("#050608")
    }
    val title2 by style {
      fontSize(20.px)
      color("#050608")
      padding(16.px)
    }
    val subtitle by style {
      color("#050608")
      fontSize(14.px)
      paddingLeft(16.px)
      paddingRight(16.px)
      paddingBottom(16.px)
      opacity(80.percent)
    }
  }

  object Experiment : StyleSheet(Style) {

    val container by style {
      background("linear-gradient(30deg, rgba(241,241,241,1) 0%, rgba(255,255,255,1) 50%, rgba(241,241,241,1) 100%)")
      borderRadius(8.px)
    }
    val containerOverlay by style {
      height(100.percent)
      borderRadius(8.px)
      border {
        width = 1.px
        color = Color.lightgray
        style = LineStyle.Dashed
      }
      variable("exp-bg-opacity", 1)
      property("background-color", "rgb(255 255 255/var(--exp-bg-opacity))")
      property("transition", "all .5s ease")
      self + hover style {
        variable("exp-bg-opacity", 0)
      }
    }
  }

  object Projects : StyleSheet(Style) {

    val container by style {
      variable("project-bg-opacity", 1)
      property("background-color", "rgb(255 255 255/var(--project-bg-opacity))")
      property("transition", "all .5s ease")
      self + hover style {
        variable("project-bg-opacity", 0)
      }
    }
  }
}