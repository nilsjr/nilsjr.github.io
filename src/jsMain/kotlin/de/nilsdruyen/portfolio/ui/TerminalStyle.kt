/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.ui

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.AnimationFillMode
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.animation
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.delay
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.duration
import org.jetbrains.compose.web.css.fillMode
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.flexWrap
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.media
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.timingFunction
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.css.width

@OptIn(ExperimentalComposeWebApi::class)
fun StyleScope.rise(delayMs: Int = 0) {
  opacity(0)
  transform { translateY(18.px) }
  animation(TerminalStyle.riseIn) {
    delay(delayMs.ms)
    duration(600.ms)
    timingFunction(AnimationTimingFunction.Ease)
    iterationCount = listOf(1)
    fillMode(AnimationFillMode.Both)
  }
}

object TerminalStyle : StyleSheet() {

  init {
    universal style {
      margin(0.px)
      padding(0.px)
      boxSizing("border-box")
    }

    "html" style {
      backgroundColor(Colors.Background)
    }

    "body" style {
      backgroundColor(Colors.Background)
    }

    media(CSSMediaQuery.Raw("(prefers-reduced-motion: reduce)")) {
      universal style {
        property("animation-duration", "0.01ms !important")
        property("animation-iteration-count", "1 !important")
      }
    }
  }

  @OptIn(ExperimentalComposeWebApi::class)
  val riseIn by keyframes {
    0.percent {
      opacity(0)
      transform { translateY(18.px) }
    }
    100.percent {
      opacity(1)
      transform { translateY(0.px) }
    }
  }

  val blink by keyframes {
    0.percent { opacity(1) }
    49.percent { opacity(1) }
    50.percent { opacity(0) }
    100.percent { opacity(0) }
  }

  val page by style {
    position(Position.Relative)
    minHeight(100.vh)
    backgroundColor(Colors.Background)
    color(Colors.Text)
    fontFamily("JetBrains Mono", "monospace")
    fontSize(15.px)
    lineHeight("1.7")
    property("overflow", "hidden")
  }

  val rainCanvas by style {
    position(Position.Fixed)
    property("inset", "0")
    width(100.percent)
    height(100.percent)
    property("pointer-events", "none")
    opacity(0.55)
  }

  val content by style {
    position(Position.Relative)
    property("z-index", "1")
    maxWidth(1280.px)
    property("margin", "0 auto")
    padding(52.px, 48.px, 64.px)

    media(mediaOnlyScreenMaxWidth(640.px)) {
      self style {
        padding(32.px, 20.px, 48.px)
      }
    }
  }

  val muted by style {
    color(Colors.Muted)
  }

  val label by style {
    color(Colors.Muted)
    fontSize(13.px)
  }

  val header by style {
    display(DisplayStyle.Flex)
    justifyContent(JustifyContent.SpaceBetween)
    alignItems(AlignItems.Center)
  }

  val dots by style {
    display(DisplayStyle.Flex)
    gap(8.px)
  }

  val dot by style {
    width(11.px)
    height(11.px)
    borderRadius(50.percent)
    backgroundColor(Colors.Dot)
    display(DisplayStyle.Block)
  }

  val dotAccent by style {
    backgroundColor(Colors.Purple)
  }

  val hero by style {
    marginTop(76.px)
  }

  val heroTitle by style {
    property("margin", "10px 0 0")
    fontSize(46.px)
    fontWeight(600)
    property("letter-spacing", "-0.02em")
    color(Colors.Bright)
    lineHeight("1.15")
    property("font-family", "inherit")

    media(mediaOnlyScreenMaxWidth(640.px)) {
      self style {
        fontSize(32.px)
      }
    }
  }

  val heroText by style {
    property("margin", "26px 0 0")
    maxWidth(640.px)
    color(Colors.Body)
  }

  val keyword by style {
    color(Colors.Purple)
  }

  val string by style {
    color(Colors.Green)
  }

  val bright by style {
    color(Colors.Bright)
  }

  val card by style {
    border {
      width = 1.px
      style = LineStyle.Solid
      color = Colors.Border
    }
    borderRadius(10.px)
    padding(28.px, 32.px)
    backgroundColor(rgba(19, 17, 24, 0.88))
    property("backdrop-filter", "blur(2px)")
  }

  val cardLabel by style {
    color(Colors.Muted)
    fontSize(13.px)
    marginBottom(16.px)
  }

  val heroGap by style {
    marginTop(72.px)
  }

  val sectionGap by style {
    marginTop(32.px)
  }

  val twoCol by style {
    display(DisplayStyle.Grid)
    gridTemplateColumns("1fr 1fr")
    gap(28.px)

    media(mediaOnlyScreenMaxWidth(640.px)) {
      self style {
        gridTemplateColumns("1fr")
      }
    }
  }

  val repoLink by style {
    display(DisplayStyle.Flex)
    gap(20.px)
    alignItems(AlignItems.Baseline)
    textDecoration("none")
    color(Colors.Text)
  }

  val repoName by style {
    color(Colors.Purple)
    fontWeight(600)
  }

  val repoDesc by style {
    color(Colors.Body)
    fontSize(14.px)
  }

  val repoList by style {
    display(DisplayStyle.Flex)
    flexDirection(FlexDirection.Column)
    gap(10.px)
  }

  val timeline by style {
    fontSize(14.px)
    display(DisplayStyle.Flex)
    flexDirection(FlexDirection.Column)
    gap(6.px)
  }

  val timelinePeriod by style {
    display(DisplayStyle.InlineBlock)
    width(150.px)
    color(Colors.Green)
  }

  val arrowLink by style {
    display(DisplayStyle.Block)
    marginTop(12.px)
    color(Colors.Green)
    textDecoration("none")
    fontSize(14.px)
  }

  val contactList by style {
    fontSize(14.px)
    display(DisplayStyle.Flex)
    flexDirection(FlexDirection.Column)
    gap(6.px)
  }

  val contactLink by style {
    color(Colors.Text)
    textDecoration("none")

    self + hover style {
      color(Colors.Green)
    }
  }

  val toolIcon by style {
    height(40.px)
    property("width", "auto")
  }

  val toolIconLarge by style {
    height(56.px)
    property("width", "auto")
  }

  val glowGreen by style {
    property("filter", "drop-shadow(0 0 12px rgba(61,220,132,0.35))")
  }

  val glowPurple by style {
    property("filter", "drop-shadow(0 0 12px rgba(127,82,255,0.4))")
  }

  val glowBlue by style {
    property("filter", "drop-shadow(0 0 12px rgba(83,131,236,0.4))")
  }

  val stackTools by style {
    marginTop(28.px)
    display(DisplayStyle.Flex)
    flexWrap(FlexWrap.Wrap)
    gap(20.px)
    justifyContent(JustifyContent.SpaceAround)
    alignItems(AlignItems.FlexEnd)
  }

  val tool by style {
    display(DisplayStyle.Flex)
    flexDirection(FlexDirection.Column)
    alignItems(AlignItems.Center)
    gap(12.px)
  }

  val toolName by style {
    fontSize(12.px)
    color(Colors.Muted)
  }

  val prompt by style {
    marginTop(64.px)
    color(Colors.Muted)
    fontSize(13.px)
  }

  val cursor by style {
    display(DisplayStyle.InlineBlock)
    width(9.px)
    height(17.px)
    backgroundColor(Colors.Purple)
    property("vertical-align", "text-bottom")
    animation(blink) {
      duration(1100.ms)
      timingFunction(AnimationTimingFunction.StepEnd)
      iterationCount = listOf(null)
    }
  }
}