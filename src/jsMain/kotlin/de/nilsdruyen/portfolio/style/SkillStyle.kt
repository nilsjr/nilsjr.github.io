/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.style

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
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.width

object SkillStyle : StyleSheet(AppStylesheet) {

  val section by style {
    position(Position.Relative)
    margin(100.px, 0.px)
//    opacity(0)
//    property("transition", "1s")
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
    background("#664ACC28")
    border {
      width = 10.px
      style = LineStyle.Solid
      color = Color("#664ACC99")
    }
    color(Color("#664ACC"))
  }

  val border by style {
    position(Position.Absolute)
    top(80.px)
    right(0.px)
    width(150.px)
    height(150.px)
    borderRadius(100.percent)
    background("linear-gradient(270deg, black 50%, transparent 50%), linear-gradient(0deg, black 50%, lightgray 50%)")
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

  val androidLevel by style {
    baseLevel()
    background("#4fdfff28")
    border {
      width = 10.px
      style = LineStyle.Solid
      color = Color("#4fdfff")
    }
    color(Color("#4fdfff"))
  }

  val kmmLevel by style {
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
    borderRadius(50.percent)
  }
}