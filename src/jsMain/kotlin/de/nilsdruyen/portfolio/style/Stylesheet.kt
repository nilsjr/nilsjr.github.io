/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

@file:Suppress("FunctionName")

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
import org.jetbrains.compose.web.css.borderRadius
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
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.transform
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
    alignItems(AlignItems.Center)
    position(Position.Fixed)
    top(0.px)
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
    cursor("pointer")

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

  val projectCard by style {
    height(400.px)
    position(Position.Relative)

    self + hover + " .ProjectStyle-projectImg" style {
      property("filter", "blur(20px)")
    }
    self + hover + " .ProjectStyle-projectContent" style {
      opacity(1)
    }
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
    basicButton()
  }

  val projectBtnLive by style {
    basicButton()
    background("none")
    border {
      width = 2.px
      style = LineStyle.Solid
      color = Color("#fff")
    }
  }

  private fun CSSBuilder.basicButton() {
    height(40.px)
    property("text-transform", "capitalize")
    fontSize(18.px)
    property("border", "none")
    backgroundColor(Color("#000"))
    color(Color("#fff"))
    cursor("pointer")
  }
}

object AboutStyle : StyleSheet(AppStylesheet) {

  val section by style {
    width(100.percent)
    minHeight(100.vh)
    padding(150.px, 100.px, 0.px)
    position(Position.Fixed)
    top(0.px)
//    opacity(0)
//    property("transition", "1s")
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

object SkillStyle : StyleSheet(AppStylesheet) {

  val section by style {
    position(Position.Relative)
    margin(100.px, 0.px)
  }

  val heading by style {
    textAlign("center")
    fontSize(60.px)
    color(Color.white)
    property("text-transform", "capitalize")
    fontWeight(300)
    marginBottom(100.px)
  }

  val container by style {
    width(95.percent)
    property("margin", "auto")
    display(DisplayStyle.Grid)
    gridTemplateColumns("repeat(3, 1fr)")
    property("grid-gap", "100px")
    color(Color("#fff"))
  }

  val card by style {
    position(Position.Relative)
  }

  val img by style {
    display(DisplayStyle.Block)
    property("margin", "auto")
    height(200.px)
  }

  val name by style {
    fontSize(30.px)
    fontWeight(300)
    textAlign("center")
    property("text-transform", "capitalize")
    margin(30.px, 0.px, 20.px)
  }

  val info by style {
    textAlign("center")
    opacity(0.5)
    fontSize(18.px)
    lineHeight(30.px)
  }

  val kotlinLevel by style {
    baseLevel()
    background("#ff4f4f28")
    border {
      color = Color("#ff4f4f")
    }
    color(Color("#ff4f4f"))
  }

  val javaLevel by style {
    baseLevel()
    background("#4fdfff28")
    border {
      width = 10.px
      style = LineStyle.Solid
      color = Color("#4fdfff")
    }
    color(Color("#4fdfff"))
  }

  private fun CSSBuilder.baseLevel() {
    position(Position.Absolute)
    top(80.px)
    right(0.px)
    width(150.px)
    height(150.px)
    display(DisplayStyle.Flex)
    justifyContent(JustifyContent.Center)
    alignItems(AlignItems.Center)
    fontSize(22.px)
    border {
      width = 10.px
      style = LineStyle.Solid
    }
    borderRadius(50.percent)
  }
}

object TimeLineStyle : StyleSheet(AppStylesheet)