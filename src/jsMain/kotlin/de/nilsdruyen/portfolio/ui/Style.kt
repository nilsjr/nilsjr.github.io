/*
 * Created by Nils Druyen on 10-13-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.ui

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.AnimationFillMode
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StylePropertyNumber
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.animation
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.backgroundPosition
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.delay
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.duration
import org.jetbrains.compose.web.css.fillMode
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
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.media
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.order
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.timingFunction
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.css.variable
import org.jetbrains.compose.web.css.width

@OptIn(ExperimentalComposeWebApi::class)
fun StyleScope.fadeIn(delay: Int = 0) {
  this.apply {
    opacity(0)
    transform { translateY((-Constants.DISTANCE_SM).px) }
    animation(Style.containerFadeIn) {
      delay(delay.ms)
      duration(400.ms)
      timingFunction(AnimationTimingFunction.EaseInOut)
      iterationCount = listOf(1)
      fillMode(AnimationFillMode.Forwards)
    }
  }
}

object Style : StyleSheet() {

  val titleColor by variable<CSSColorValue>()
  val sectionTitleColor by variable<CSSColorValue>()
  val textColor by variable<CSSColorValue>()
  val iconColor by variable<CSSColorValue>()

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

  val light by style {
    backgroundColor(Color.white)

    property("--tw-border-opacity", "1")
    property("transition", "background-color 1.0s ease-in")

    titleColor(Color.black)
    sectionTitleColor(Colors.DarkGrey)
    textColor(Colors.TextGray)
    iconColor(Colors.DarkGrey)
  }

  val dark by style {
    backgroundColor(Color.black)

    property("--tw-border-opacity", ".3")
    property("transition", "background-color 1.0s ease-in")

    titleColor(Color.white)
    sectionTitleColor(Color.white)
    textColor(Color.white)
    iconColor(Color.white)
  }

  val borderGray by style {
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
    position(Position.Absolute)
    maxWidth(280.px)
    borderRadius(1.cssRem)
    border {
      width = 1.px
      style = LineStyle.Solid
      color = Color("#d7dde4")
    }
    property("transition", "opacity 1.5s ease-in")
  }

  val hidden by style { opacity(0) }
  val visible by style { opacity(1) }

  val title by style {
    fontFamily("Rubik Dirt")
    fontSize(4.cssRem)
    marginLeft(.4.cssRem)
    color(titleColor.value())
    property("transition", "color 1.5s ease-in")
  }

  val paddingMedium by style { padding(2.cssRem) }
  val smallMargin by style { marginTop(1.cssRem) }
  val smallMarginBottom by style { marginBottom(1.cssRem) }
//  val mediumMargin by style { marginTop(2.cssRem) }
//  val largeMargin by style { marginTop(4.cssRem) }

  val noPaddingTop by style {
    media(mediaOnlyScreenMaxWidth(600.px)) {
      self style {
        paddingTop(0.px)
      }
    }
  }

  @OptIn(ExperimentalComposeWebApi::class)
  val containerFadeIn by keyframes {
    0.percent {
      opacity(0)
      transform {
        translateY((-Constants.DISTANCE_SM).px)
      }
    }
    100.percent {
      opacity(1)
      transform {
        translateY(0.px)
      }
    }
  }

  object Flex : StyleSheet(Style) {

    val container by style {
      display(DisplayStyle.Flex)
      justifyContent(JustifyContent.Center)
      alignItems(AlignItems.Center)
      position(Position.Relative)
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

    val row by style {
      display(DisplayStyle.Flex)
      flexDirection(FlexDirection.Row)
    }

    val center by style { alignItems(AlignItems.Center) }

    val gapSmall by style { gap(1.cssRem) }
    val gapMedium by style { gap(2.cssRem) }

    val stretch by style {
      flex(1)
      display(DisplayStyle.Flex)
      justifyContent(JustifyContent.Center)
    }
  }

  object Grid : StyleSheet(Style) {

    val col12 by style {
      gridTemplateColumns("repeat(12,minmax(0,1fr))")
      display(DisplayStyle.Grid)
      paddingLeft(2.5.cssRem)
      paddingRight(2.5.cssRem)

      media(mediaOnlyScreenMaxWidth(600.px)) {
        self style {
          paddingLeft(1.5.cssRem)
          paddingRight(1.5.cssRem)
        }
      }
    }

    val span12 by style { gridColumn("span 12/span 12") }
    val span11 by style { gridColumn("span 11/span 11") }
    val span10 by style { gridColumn("span 10/span 10") }
    val span9 by style { gridColumn("span 9/span 9") }
    val span8 by style { gridColumn("span 8/span 8") }
    val span7 by style { gridColumn("span 7/span 7") }
    val span6 by style { gridColumn("span 6/span 6") }
    val span5 by style { gridColumn("span 5/span 5") }
    val span4 by style { gridColumn("span 4/span 4") }
    val span2 by style { gridColumn("span 2/span 2") }
    val span1 by style { gridColumn("span 1/span 1") }

    val borderB by style {
      property("border-bottom-width", "1px")
    }

    val borderR by style {
      property("border-right-width", "1px")
    }

    val borderL by style {
      property("border-left-width", "1px")
      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { property("border-left-width", "0") }
      }
    }

    val borderSmallL by style {
      property("border-left-width", "1px")
      media(mediaOnlyScreenMinWidth(768.px)) {
        self style { property("border-left-width", "0") }
      }
    }

    val borderMediumL by style {
      property("border-left-width", "1px")
      media(mediaOnlyScreenMinWidth(896.px)) {
        self style { property("border-left-width", "0") }
      }
    }

    val borderT by style {
      property("border-top-width", "1px")
      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { property("border-top-width", "0") }
      }
    }

    val borderX by style {
      property("border-left-width", "1px")
      property("border-right-width", "1px")
    }

    val placeholder by style {
      height(2.5.cssRem)
      media(mediaOnlyScreenMaxWidth(600.px)) {
        self style { height(1.5.cssRem) }
      }
    }

    val title by style {
      gridColumn("span 11/span 11")
      media(mediaOnlyScreenMaxWidth(600.px)) {
        self style {
          gridColumn("span 12/span 12")
        }
      }
      media(mediaOnlyScreenMinWidth(768.px)) {
        self style {
          gridColumn("span 6/span 6")
        }
      }
    }

    val titleHidden by style {
      gridColumn("span 5/span 5")
      display(DisplayStyle.None)
      media(mediaOnlyScreenMinWidth(768.px)) {
        self style {
          display(DisplayStyle.Flex)
          justifyContent(JustifyContent.Center)
          alignItems(AlignItems.Center)
        }
      }
    }

    val hidden by style {
      display(DisplayStyle.Flex)
      media(mediaOnlyScreenMaxWidth(600.px)) {
        self style {
          display(DisplayStyle.None)
        }
      }
    }

    val description by style {
      gridColumn("span 12/span 12")
      order(2)
      media(mediaOnlyScreenMinWidth(768.px)) {
        self style {
          gridColumn("span 6/span 6")
          order(1)
        }
      }
      media(mediaOnlyScreenMinWidth(896.px)) {
        self style { gridColumn("span 8/span 8") }
      }
      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { gridColumn("span 4/span 4") }
      }
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val imageFadeIn by keyframes {
      0.percent {
        opacity(0)
        transform {
          rotate(10.deg)
          translateY((-30).px)
        }
      }
      60.percent {
        opacity(1)
      }
      100.percent {
        opacity(1)
        transform {
          rotate(0.deg)
          translateY(0.px)
        }
      }
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val imageContainer by style {
      width(100.percent)
      height(100.percent)
      display(DisplayStyle.Flex)
      justifyContent(JustifyContent.Center)
      alignItems(AlignItems.Center)
      opacity(0)
      transform {
        rotate(10.deg)
        translateY((-30).px)
      }
      animation(imageFadeIn) {
        duration(1000.ms)
        delay(350.ms)
        timingFunction(AnimationTimingFunction.EaseInOut)
        iterationCount = listOf(1)
        fillMode(AnimationFillMode.Forwards)
      }
    }

    val image by style {
      gridColumn("span 12/span 12")
      order(1)
      minHeight(320.px)

      media(mediaOnlyScreenMinWidth(768.px)) {
        self style {
          gridColumn("span 6/span 6")
        }
      }
      media(mediaOnlyScreenMinWidth(896.px)) {
        self style { gridColumn("span 4/span 4") }
      }
      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { gridColumn("span 4/span 4") }
      }
    }

    val experiments by style {
      gridColumn("span 12/span 12")
      order(3)
      display(DisplayStyle.Flex)
      flexDirection(FlexDirection.Column)
      gap(1.cssRem)
      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { gridColumn("span 4/span 4") }
      }
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val containerFadeIn by keyframes {
      0.percent {
        opacity(0)
        transform {
          translateY((-20).px)
        }
      }
      100.percent {
        opacity(1)
        transform {
          translateY(0.px)
        }
      }
    }

    val experimentsContainer by style {
      display(DisplayStyle.Flex)
      flexDirection(FlexDirection.Row)
      gap(1.cssRem)
      media(mediaOnlyScreenMaxWidth(768.px)) {
        self style { flexDirection(FlexDirection.Column) }
      }
      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { flexDirection(FlexDirection.Column) }
      }
    }

    fun experimentsFadeIn(scope: StyleScope, delay: Int) {
      scope.apply {
        opacity(0)
        animation(containerFadeIn) {
          delay(delay.ms)
          duration(400.ms)
          timingFunction(AnimationTimingFunction.EaseInOut)
          iterationCount = listOf(1)
          fillMode(AnimationFillMode.Forwards)
        }
      }
    }

    val work by style {
      gridColumn("span 12/span 12")
      media(mediaOnlyScreenMinWidth(896.px)) {
        self style { gridColumn("span 7/span 7") }
      }
      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { gridColumn("span 5/span 5") }
      }
    }

    val interests by style {
      gridColumn("span 12/span 12")
      property("border-top-width", "1px")
      media(mediaOnlyScreenMinWidth(896.px)) {
        self style {
          gridColumn("span 5/span 5")
          property("border-top-width", "0")
        }
      }
      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { gridColumn("span 7/span 7") }
      }
    }

    val interestsContainer by style {
      width(80.percent)
      display(DisplayStyle.Flex)
      flexDirection(FlexDirection.Column)

      media(mediaOnlyScreenMinWidth(1280.px)) {
        self style { flexDirection(FlexDirection.Row) }
      }
    }
  }

  object Section : StyleSheet(Style) {

    val title by style {
      fontSize(30.px)
      fontWeight(500)
      color(sectionTitleColor.value())
    }
    val title2 by style {
      fontSize(20.px)
      color(titleColor.value())
    }
    val subtitle by style {
      fontSize(14.px)
      color(textColor.value())
    }

    val gradient by style {
      background("linear-gradient(147deg, rgba(241,241,241,1) 0%, rgba(255,255,255,1) 70%, rgba(255,255,255,1) 100%)")
    }

    val flexItem by style {
      flex(1, 1, 0.percent)
    }

    val blue by style { backgroundColor(rgba(242, 246, 250, .4)) }
    val lime by style { backgroundColor(rgba(132, 250, 207, .1)) }
    val orange by style { backgroundColor(rgba(252, 229, 184, .1)) }
    val red by style { backgroundColor(rgba(242, 246, 250, .5)) }

    @Suppress("MaxLineLength")
    val dotted by style {
      backgroundPosition("50%")
      property(
        "background-image",
        "url(\"data:image/svg+xml,%3Csvg width='16' height='16' viewBox='0 0 16 16' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M1.5 0.75C1.5 1.16421 1.16421 1.5 0.75 1.5C0.335786 1.5 0 1.16421 0 0.75C0 0.335786 0.335786 0 0.75 0C1.16421 0 1.5 0.335786 1.5 0.75Z' fill='%23D7DDE4'/%3E%3C/svg%3E%0A\")"
      )
    }
  }

  object AboutMe : StyleSheet(Style) {

    @OptIn(ExperimentalComposeWebApi::class)
    private val iconFadeIn by keyframes {
      0.percent {
        opacity(0)
        transform { translateY((-20).px) }
      }
      100.percent {
        opacity(1)
        transform { translateY(0.px) }
      }
    }

    val description by style {
      fontSize(18.px)
      marginTop(1.cssRem)
      color(textColor.value())
    }

    val social by style {
      height(100.percent)
      width(100.percent)
      display(DisplayStyle.Flex)
      alignItems(AlignItems.FlexEnd)
      justifyContent(JustifyContent.FlexEnd)
      gap(20.px)
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val profileLink by style {
      opacity(.6)

      property("transition", "all .2s ease")
      self + hover style {
        opacity(1)
        transform {
          scale(1.2)
        }
      }
    }

    fun socialFadeIn(scope: StyleScope, delay: Int) {
      scope.apply {
        opacity(0)
        animation(iconFadeIn) {
          duration(600.ms)
          delay(delay.ms)
          timingFunction(AnimationTimingFunction.EaseInOut)
          iterationCount = listOf(1)
          fillMode(AnimationFillMode.Forwards)
        }
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

  object Work : StyleSheet(Style) {

    @OptIn(ExperimentalComposeWebApi::class)
    val interestLink by style {
      property("width", "min-content")
      height(80.px)
      display(DisplayStyle.Flex)
      alignItems(AlignItems.Center)
      property("transition", "transform .2s ease")
      self + hover style {
        transform {
          scale(1.1)
        }
      }
    }
  }

  object Projects : StyleSheet(Style) {

    val bgOpacity by variable<StylePropertyNumber>()

    val grid by style {
      display(DisplayStyle.Grid)
      gridTemplateColumns("repeat(4,minmax(0,1fr))")
      gap(2.cssRem)
      position(Position.Relative)
      marginTop(1.cssRem)
      media(mediaOnlyScreenMaxWidth(600.px)) {
        self style {
          gridTemplateColumns("repeat(2,minmax(0,1fr))")
        }
      }
      media(mediaOnlyScreenMinWidth(768.px)) {
        self style {
          gridTemplateColumns("repeat(6,minmax(0,1fr))")
        }
      }
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