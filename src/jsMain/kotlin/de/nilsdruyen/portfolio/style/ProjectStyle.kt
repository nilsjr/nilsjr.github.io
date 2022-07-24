/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

import org.jetbrains.compose.web.css.CSSBuilder
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundClip
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.width

object ProjectStyle : StyleSheet(AppStylesheet) {

  val projectSection by style {
    width(100.percent)
    minHeight(100.percent)
    padding(150.px, 100.px, 100.px)
//    position(Position.Fixed)
    position(Position.Relative)
    top(0.px)

//    opacity(0)
//    property("transition", "1s")

//    desc(self, active) style {
//      position(Position.Relative)
//      opacity(1)
//      property("z-index", "8")
//    }
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