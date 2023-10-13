/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.styles

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StylePropertyNumber
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.backgroundPosition
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.gridColumn
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.letterSpacing
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.css.variable
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
      fontSize(2.cssRem)
      fontWeight(500)
    }
  }

  val borderB by style {
    property("border-bottom-width", "1px")
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

  val maxWidth by style {
    maxWidth(90.cssRem)
  }

  val mxAuto by style {
    property("margin-left", "auto")
    property("margin-right", "auto")
  }

  val profileImage by style {
    maxWidth(300.px)
    width(100.percent)
    borderRadius(1.cssRem)
    border {
      width = 1.px
      style = LineStyle.Solid
      color = Color("#d7dde4")
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
    backgroundColor(rgba(242, 246, 250, .4))
    property(
      "background-image",
      "url(\"data:image/svg+xml,%3Csvg width='16' height='16' viewBox='0 0 16 16' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M1.5 0.75C1.5 1.16421 1.16421 1.5 0.75 1.5C0.335786 1.5 0 1.16421 0 0.75C0 0.335786 0.335786 0 0.75 0C1.16421 0 1.5 0.335786 1.5 0.75Z' fill='%23D7DDE4'/%3E%3C/svg%3E%0A\")"
    )
  }

  val pad1 by style { padding(1.cssRem) }
  val pad2 by style { padding(2.cssRem) }

  val smallMargin by style { marginTop(1.cssRem) }
  val mediumMargin by style { marginTop(2.cssRem) }
  val largeMargin by style { marginTop(4.cssRem) }

  object Flex : StyleSheet(Style) {

    val container by style {
      display(DisplayStyle.Flex)
      justifyContent(JustifyContent.Center)
      alignItems(AlignItems.Center)
    }

    val alignLeft by style {
      justifyContent(JustifyContent.Left)
    }

    val alignCenter by style {
      justifyContent(JustifyContent.Center)
    }

    val column by style {
      display(DisplayStyle.Flex)
      flexDirection(FlexDirection.Column)
    }
  }

  object Grid : StyleSheet(Style) {

    val col12 by style {
      gridTemplateColumns("repeat(12,minmax(0,1fr))")
      display(DisplayStyle.Grid)
      paddingLeft(2.5.cssRem)
      paddingRight(2.5.cssRem)
    }

    val col6 by style {
      display(DisplayStyle.Grid)
      gridTemplateColumns("repeat(6,minmax(0,1fr))")
      gap(1.cssRem)
    }

    val span12 by style { gridColumn("span 12/span 12") }
    val span8 by style { gridColumn("span 8/span 8") }
    val span6 by style { gridColumn("span 6/span 6") }
    val span5 by style { gridColumn("span 5/span 5") }
    val span4 by style { gridColumn("span 4/span 4") }
    val span2 by style { gridColumn("span 2/span 2") }
    val span1 by style { gridColumn("span 1/span 1") }
  }

  object Section : StyleSheet(Style) {

    val title by style {
      fontSize(24.px)
      fontWeight(600)
      color(Colors.DarkGrey)
    }
    val title2 by style {
      fontSize(20.px)
      color(Colors.DarkGrey)
    }
    val subtitle by style {
      color(Colors.DarkGrey)
      fontSize(14.px)
      opacity(80.percent)
    }

    val gradient by style {
      background("linear-gradient(147deg, rgba(241,241,241,1) 0%, rgba(255,255,255,1) 70%, rgba(255,255,255,1) 100%)")
    }

    val flexItem by style {
      flex(1, 1, 0.percent)
    }
  }

  object AboutMe : StyleSheet(Style) {

    val description by style {
      color(Colors.DarkGrey)
      fontSize(18.px)
      opacity(80.percent)
      marginTop(16.px)
    }

    val social by style {
      height(100.percent)
      width(100.percent)
      display(DisplayStyle.Flex)
      alignItems(AlignItems.FlexEnd)
      justifyContent(JustifyContent.FlexEnd)
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val profileLink by style {
      marginLeft(16.px)
      property("transition", "all .2s ease")
      opacity(.6)
      self + hover style {
        transform {
          scale(1.2)
        }
        opacity(1)
      }
    }
  }

  object Experiment : StyleSheet(Style) {

    val container by style {
      background("linear-gradient(30deg, rgba(241,241,241,1) 0%, rgba(255,255,255,1) 50%, rgba(241,241,241,1) 100%)")
      borderRadius(8.px)
    }
    val containerOverlay by style {
      height(100.percent)
      padding(1.cssRem)
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

    val bgOpacity by variable<StylePropertyNumber>()

    val grid by style {
      display(DisplayStyle.Grid)
      gridTemplateColumns("repeat(6,minmax(0,1fr))")
      gap(1.cssRem)
      position(Position.Relative)
      marginTop(1.cssRem)
    }

    val container by style {
      background("linear-gradient(30deg, rgba(241,241,241,1) 0%, rgba(255,255,255,1) 50%, rgba(241,241,241,1) 100%)")
      borderRadius(1.cssRem)
    }

    val containerInner by style {
      height(100.percent)
      display(DisplayStyle.Flex)
      alignItems(AlignItems.FlexStart)
      flexDirection(FlexDirection.Column)
    }

    val containerOverlay by style {
      height(100.percent)
      borderRadius(1.cssRem)
      border {
        width = 1.px
        color = Color.lightgray
        style = LineStyle.Solid
      }
      display(DisplayStyle.Flex)
      flexDirection(FlexDirection.Column)
      padding(1.cssRem)
      bgOpacity(1)
      backgroundColor(rgba(255, 255, 255, bgOpacity.value().unsafeCast<Number>()))
      property("transition", "all .5s ease")
      self + hover style {
        bgOpacity(0)
      }
    }

    object Button : StyleSheet(Style) {

      val container by style {
        padding(.5.cssRem)
        borderRadius(.5.cssRem)
        border {
          width = 1.px
          style = LineStyle.Solid
          color = Color.lightgray
        }
        display(DisplayStyle.LegacyInlineFlex)
        alignItems("center")

        property("transition", "border .5s ease")
        desc(className(containerOverlay) + hover, self) style {
          border {
            width = 1.px
            style = LineStyle.Solid
            color = Colors.Blue
          }
        }
      }

      val text by style {
        color(Colors.DarkGrey)
        fontSize(.875.cssRem)
        fontWeight(700)
        lineHeight("1")
        property("transition", "color .3s linear")
        desc(className(containerOverlay) + hover, self) style {
          color(Colors.Blue)
        }
      }

      val icon by style {
        width(1.cssRem)
        height(1.cssRem)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        marginLeft(.5.cssRem)
      }
    }
  }

  object Footer : StyleSheet(Style) {

    val text by style {
      fontSize(14.px)
      fontWeight(500)
      textAlign("center")
      opacity(.5)
    }
  }
}