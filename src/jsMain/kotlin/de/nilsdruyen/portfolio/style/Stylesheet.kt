/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.CSSBuilder
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundClip
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.listStyle
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.vh
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

//    "body.js-stop-transition *" style {
//      property("transition", "none !important")
//      property("-webkit-transition", "none !important")
//      property("-moz-transition", "none !important")
//      property("-ms-transitio", "none !important")
//      property("-o-transition", "none !important")
//    }
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

object AppStyle : StyleSheet(AppStylesheet) {

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

  val centerContainer by style {
    position(Position.Absolute)
    top(0.px)
    left(0.px)
    right(0.px)
    bottom(0.px)
    property("margin", auto)
    width(50.percent)
    height(50.percent)
    maxWidth(400.px)
  }

  val maintainanceText by style {
    marginTop(80.px)
    textAlign("center")
    color(Color("#fff"))
    fontSize(24.px)
  }

  val homeSection by style {
    homeSection()
  }

  private fun CSSBuilder.homeSection() {
    width(100.percent)
    height(100.vh)
    padding(0.px, 150.px)
    display(DisplayStyle.Flex)
    alignItems("center")
    position(Position.Fixed)
    top(0.px)
//    opacity(0)
//    property("transition", "1s")
  }

  val heroHeading by style {
    color(Color("#fff"))
    fontSize(120.px)
    property("text-transform", "capitalize")
    fontWeight(300)
  }

  val homeImage by style {
    position(Position.Absolute)
    top(0.px)
    right(0.px)
    height(100.vh)
    width(50.percent)
    property("object-fit", "cover")
    opacity(.2)
  }
}

object ButtonStyle : StyleSheet(AppStylesheet) {

  private fun CSSBuilder.basicButton() {
    // disable button style
    property("border", "none")
  }

  val navButton by style {
    basicButton()

    color(Color("#fff"))
    backgroundColor(Color.transparent)
    opacity(0.5)
    textDecoration("none")
    property("text-transform", "capitalize")
    padding(10.px, 30.px)
    margin(0.px, 20.px)
    lineHeight(80.px)
    property("transition", ".3s")
    fontSize(20.px)

    self + hover style {
      opacity(1)
    }
  }

  val navButtonActive by style {
    navButton
    opacity(1)
  }
}

object ProjectStyle : StyleSheet(AppStylesheet) {

  val projectSection by style {
    width(100.percent)
    minHeight(100.percent)
    padding(150.px, 100.px, 100.px)
    position(Position.Fixed)
    top(0.px)
//    property("transition", "1s")
//    opacity(0)
  }

  val projectHeading by style {
    fontSize(100.px)
    backgroundColor(Color("#252525"))
    property("text-transform", "capitalize")
    textAlign("center")
    marginBottom(50.px)
    color(Color("#1a1a1a"))
    backgroundClip("text")
    property("-webkit-background-clip", "text")
    property("-webkit-text-stroke", "8px transparent")
  }

  val projectContainer by style {
    display(DisplayStyle.Grid)
    gridTemplateColumns("repeat(2, 1fr)")
    property("grid-gap", "100px")
  }

  @OptIn(ExperimentalComposeWebApi::class)
  val projectCard by style {
    height(400.px)
    position(Position.Relative)

//    self + hover + " $projectContent" style {
//      opacity(1)
//    }
//
//    self + hover + " $projectImg" style {
//      property("filter", "blur(20px)")
//    }
  }

  val projectImg by style {
    width(100.percent)
    height(100.percent)
    position(Position.Absolute)
    top(0.px)
    left(0.px)
    property("object-fit", "cover")
    property("transition", ".5s")
  }

  val projectContent by style {
    position(Position.Relative)
    padding(40.px)
    color(Color("#fff"))
    property("transition", ".5s")
    opacity(0)
  }

  val projectTitle by style {
    fontSize(50.px)
    property("text-transform", "capitalize")
    textAlign("center")
    fontWeight(300)
  }

  val projectInfo by style {
    margin(40.px)
    fontSize(20.px)
    lineHeight(30.px)
    textAlign("center")
  }

  val projectBtnGrp by style {
    display(DisplayStyle.Grid)
    gridTemplateColumns("repeat(2, 1fr)")
    property("grid-gap", "20px")
  }

  val projectBtn by style {
    height(40.px)
    property("text-transform", "capitalize")
    fontSize(18.px)
    property("border", "none")
    backgroundColor(Color("#000"))
    color(Color("#fff"))
    cursor("pointer")
  }

  val projectBtnLive by style {
    projectBtn
    background("none")
    border {
      width = 2.px
      style = LineStyle.Solid
      color = Color("#fff")
    }
  }
}