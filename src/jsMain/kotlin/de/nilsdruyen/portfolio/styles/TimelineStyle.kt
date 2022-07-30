/*
 * Created by Nils Druyen on 01-09-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.styles

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.selectors.Nth
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.width

object TimelineStyle : StyleSheet(WebPageStyle) {

  val container by style {
    display(DisplayStyle.Block)
    width(80.percent)
    property("margin", "150px auto")

    desc(self, WebPageStyle.heading) style {
      marginBottom(150.px)
    }
  }

  val cardBody by style {}

  val cardDetail by style {}

  @OptIn(ExperimentalComposeWebApi::class)
  val card by style {
    width(45.percent)
    padding(30.px)
    borderRadius(10.px)
    color(Color("#fff"))
    display(DisplayStyle.Block)
    property("margin", "-80px 0")
    position(Position.Relative)
    background("#f00")

    self + nthChild(Nth.Even) style {
      property("margin-left", "auto")
    }
    // even
    self + nthChild(Nth.Even) + before style {
      property("content", "''")
      position(Position.Absolute)
      left((-15).percent)
      top(50.percent)
      transform {
        translateY((-50).percent)
      }
      width(20.px)
      height(20.px)
      borderRadius(50.percent)
      border {
        width = 5.px
        style = LineStyle.Solid
        color = Color("#191919")
      }
    }
    self + nthChild(Nth.Even) + after style {
      property("content", "''")
      position(Position.Absolute)
      left((-8.5).percent)
      top(50.percent)
      transform {
        translateY((-50).percent)
      }
      width(7.percent)
      height(2.px)
      background("#fff")
      property("z-index", "-1")
    }
    // odd
    self + nthChild(Nth.Odd) + before style {
      property("content", "''")
      position(Position.Absolute)
      right((-13).percent)
      top(50.percent)
      transform {
        translateY((-50).percent)
      }
      width(20.px)
      height(20.px)
      borderRadius(50.percent)
      border {
        width = 5.px
        style = LineStyle.Solid
        color = Color("#191919")
      }
    }
    self + nthChild(Nth.Odd) + after style {
      property("content", "''")
      position(Position.Absolute)
      right((-8.5).percent)
      top(50.percent)
      transform {
        translateY((-50).percent)
      }
      width(7.percent)
      height(2.px)
      background("#fff")
      property("z-index", "-1")
    }

    group(
      self + nthChild(Nth.Functional(b = 2)),
      self + nthChild(Nth.Functional(b = 2)) + before,
    ).style {
      background("#ff4f4f")
    }
    group(
      self + nthChild(Nth.Functional(b = 3)),
      self + nthChild(Nth.Functional(b = 3)) + before,
    ).style {
      background("#ffb84f")
    }
    group(
      self + nthChild(Nth.Functional(b = 4)),
      self + nthChild(Nth.Functional(b = 4)) + before,
    ).style {
      background("#3dca5c")
    }
    group(
      self + nthChild(Nth.Functional(b = 5)),
      self + nthChild(Nth.Functional(b = 5)) + before,
    ).style {
      background("#565252")
    }
    group(
      self + nthChild(Nth.Functional(b = 6)),
      self + nthChild(Nth.Functional(b = 6)) + before,
    ).style {
      background("#4fa0ff")
    }

    desc(self + nthChild(Nth.Even), className(cardBody) + before) style {
      property("content", "''")
      position(Position.Absolute)
      left((-12).percent)
      top(0.px)
      width(0.px)
      height(100.percent)
      border(1.px, LineStyle.Dashed, Color("#fff"))
      property("z-index", "-1")
    }
  }

  val cardTitle by style {
    fontSize(30.px)
    fontWeight(300)
    marginBottom(20.px)
  }
}