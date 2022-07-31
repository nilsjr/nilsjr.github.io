/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

@file:Suppress("FunctionName")

package de.nilsdruyen.portfolio.styles

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.CSSBuilder
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.animation
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.backgroundImage
import org.jetbrains.compose.web.css.backgroundPosition
import org.jetbrains.compose.web.css.backgroundSize
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.flexBasis
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.letterSpacing
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.listStyle
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.width

object WebPageStyle : StyleSheet() {

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
      fontFamily("Roboto", "sans-serif")
      property("margin", "auto")
    }
  }

  val heading by style {
    textAlign("center")
    fontSize(60.px)
    color(Color.white)
    property("text-transform", "capitalize")
    fontWeight(300)
    marginBottom(100.px)
  }

  val lineBreak by style {
    flexBasis("100%")
    height(0.px)
  }

  object Header : StyleSheet(WebPageStyle) {

    val section by style {
      width(100.percent)
      height(600.px)
      top(0.px)
      padding(0.px)
      display(DisplayStyle.Table)
      alignItems(AlignItems.Center)
      textAlign("center")
    }

    val background by style {
      width(100.percent)
      height(400.px)
      position(Position.Relative)
      background(Colors.Grey)
    }

    val profileImage by style {
      width(400.px)
      height(400.px)
      position(Position.Relative)
      backgroundPosition("50% 50%")
      borderRadius(50.percent)
      border {
        width = 8.px
        style = LineStyle.Solid
        color = Color("#fff")
      }
      property("margin", "-250px auto 0")
    }

    private val textWidth = 400.px

    val title by style {
      width(textWidth)
      fontSize(3.cssRem)
      position(Position.Relative)
      letterSpacing(4.px)
      fontWeight(300)
      property("margin", "20px auto 0")
      property("text-transform", "uppercase")
    }

    val description by style {
      width(textWidth)
      position(Position.Relative)
      property("margin", "20px auto 0")
    }
  }

  object AboutMe : StyleSheet(WebPageStyle) {

    val section by style {
      width(100.percent)
      paddingTop(80.px)
      display(DisplayStyle.Table)
      alignItems(AlignItems.Center)
      textAlign("center")
    }

    val title by style {
      fontSize(3.cssRem)
      position(Position.Relative)
      letterSpacing(4.px)
      fontWeight(300)
      property("margin", "20px auto 0")
      property("text-transform", "lowercase")
    }

    val subtitle by style {
      fontSize(3.cssRem)
      position(Position.Relative)
      letterSpacing(4.px)
      fontWeight(300)
      property("margin", "20px auto 0")
      property("text-transform", "lowercase")
    }
  }

  object References : StyleSheet(WebPageStyle) {

    val section by style {
      width(100.percent)
      padding(80.px, 0.px)
      display(DisplayStyle.Table)
      alignItems(AlignItems.Center)
      textAlign("center")
    }

    val background by style {
      width(100.percent)
      height(400.px)
      position(Position.Relative)
      background(Colors.Grey)
    }
  }

  object Footer : StyleSheet(WebPageStyle) {

    val section by style {
      width(100.percent)
      height(300.px)
      display(DisplayStyle.Block)
      alignItems(AlignItems.Center)
      justifyContent(JustifyContent.Center)
      textAlign("center")
      backgroundImage("linear-gradient(0deg, rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url(\"assets/footer-bg.jpg\")")
      backgroundPosition("50% 50%")
      backgroundSize("cover")

      self + before style {
        property("content", "''")
        display(DisplayStyle.InlineBlock)
        height(100.percent)
        property("vertical-align", "middle")
      }
    }

    val text by style {
      textAlign("center")
      display(DisplayStyle.TableRow)
      color(Color("#fff"))
      property("margin", "auto 0")
    }

    val background by style {
      width(100.percent)
      height(120.px)
      position(Position.Relative)
      background(Colors.Blue)
      display(DisplayStyle.Flex)
      alignItems(AlignItems.Center)
      justifyContent(JustifyContent.Center)
      textAlign("center")
    }

    val linksTitle by style {
      color(Color.white)
      textAlign("center")
      margin(30.px)
    }

    val linksContainer by style {
      margin(30.px)
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val linkImage by style {
      width(40.px)
      height(40.px)
      filter {
        invert(1)
      }
      property("transition", "transform .2s")

      self + hover style {
        transform {
          scale(1.2)
        }
      }
    }
  }
}

object AppStyle : StyleSheet(WebPageStyle) {

  val navBar by style {
    width(100.percent)
    position(Position.Fixed)
    top(0.px)
    left(0.px)
    display(DisplayStyle.Flex)
    justifyContent(JustifyContent.Center)
    alignItems(AlignItems.Center)
    background(Colors.Blue)
    property("z-index", "10")
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
}

object ButtonStyle : StyleSheet(WebPageStyle) {

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
    padding(10.px, 30.px)
    margin(0.px, 20.px)
    lineHeight(70.px)
    fontSize(20.px)
    cursor("pointer")
    property("text-transform", "uppercase")
    property("transition", ".3s")

    self + hover style {
      opacity(1)
    }
  }
}

@OptIn(ExperimentalComposeWebApi::class)
object CircleProgressStyle : StyleSheet(WebPageStyle) {

  private val fillAnim by keyframes {
    0.percent {
      transform {
        rotate(0.deg)
      }
    }
    100.percent {
      transform {
        rotate(126.deg)
      }
    }
  }

  val wrap by style {
    position(Position.Absolute)
    top(80.px)
    right(0.px)
    property("margin", "50px auto")
    width(150.px)
    height(150.px)
    background("#e6e2e7")
    borderRadius(50.percent)
  }

  val container by style { }

  val maskFull by style {
    mask()
    transform {
      rotate(126.deg)
    }
    animation(fillAnim) {
      duration = listOf(3.s)
      timingFunction = listOf(AnimationTimingFunction.EaseInOut)
    }
  }

  val maskHalf by style {
    mask()
  }

  private fun CSSBuilder.mask() {
    baseMaskFill()
    property("clip", "rect(0px, 150px, 150px, 75px)")
  }

  private fun CSSBuilder.baseMaskFill() {
    width(150.px)
    height(150.px)
    position(Position.Absolute)
    borderRadius(50.percent)
  }

  val fill by style {
    baseMaskFill()

    property("clip", "rect(0px, 75px, 150px, 0px)")
    backgroundColor(Color("#9e00b1"))
    transform {
      rotate(126.deg)
    }
    animation(fillAnim) {
      duration = listOf(3.s)
      timingFunction = listOf(AnimationTimingFunction.EaseInOut)
    }
  }

  val inside by style {
    width(130.px)
    height(130.px)
    borderRadius(50.percent)
    background("#191919")
    lineHeight(130.px)
    textAlign("center")
    marginTop(10.px)
    marginLeft(10.px)
    position(Position.Absolute)
    property("z-index", "100")
    fontWeight(700)
    fontSize(2.em)
  }
}