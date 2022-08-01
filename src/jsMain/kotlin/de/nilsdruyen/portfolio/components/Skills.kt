/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

@file:Suppress("FunctionName")

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.models.Skill
import de.nilsdruyen.portfolio.data.skillList
import de.nilsdruyen.portfolio.styles.CircleProgressStyle
import de.nilsdruyen.portfolio.styles.WebPageStyle
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
fun Skills() {
  Section({ classes(WebPageStyle.Skills.section) }) {
    H1({ classes(WebPageStyle.heading) }) {
      Text("Experience")
    }
    Div({ classes(WebPageStyle.Skills.container) }) {
      skillList.forEach { SkillItem(it) }
    }
  }
}

@Composable
fun SkillItem(skill: Skill) {
  Div({
    classes(WebPageStyle.Skills.card)
  }) {
    Img(skill.imageUrl, attrs = {
      classes(WebPageStyle.Skills.img)
    })
    Circle(skill.level)
    H1({
      classes(WebPageStyle.Skills.name)
    }) {
      Text(skill.name)
    }
    P({
      classes(WebPageStyle.Skills.info)
    }) {
      Text(skill.info)
    }
  }
}

fun calculateDegrees(percentage: Int): Int = percentage * 360 / 100

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun Circle(level: Int) {
  val degrees = calculateDegrees(level)
  println("degrees $level - $degrees")

  val halfRotation = degrees.coerceAtMost(180).deg
  val fullRotation = (degrees - 180).coerceAtLeast(0).deg

//  val styleState = derivedStateOf {
//    val degrees = calculateDegrees(level).deg
//
//    object : StyleSheet(WebPageStyle) {
//
//      val animationKeyframes by keyframes {
//        0.percent {
//          transform {
//            rotate(0.deg)
//          }
//        }
//        100.percent {
//          transform {
//            rotate(degrees)
//          }
//        }
//      }
//
//      val anim by style {
////      animation(animationKeyframes) {
////        duration = listOf(3.s)
////        timingFunction = listOf(AnimationTimingFunction.EaseInOut)
////      }
//        transform {
//          rotate(degrees)
//        }
//      }
//
//      val animFull by style {
////      animation(animationKeyframes) {
////        duration = listOf(3.s)
////        timingFunction = listOf(AnimationTimingFunction.EaseInOut)
////      }
//        transform {
//          rotate(degrees)
//        }
//      }
//    }
//  }

  val style = object : StyleSheet(WebPageStyle) {

    val animationKeyframes by keyframes {
      0.percent {
        transform {
          rotate(0.deg)
        }
      }
      100.percent {
        transform {
          rotate(degrees.deg)
        }
      }
    }
  }
//
//    val anim by style {
////      animation(animationKeyframes) {
////        duration = listOf(3.s)
////        timingFunction = listOf(AnimationTimingFunction.EaseInOut)
////      }
//      transform {
//        rotate(degrees)
//      }
//    }
//
//    val animFull by style {
////      animation(animationKeyframes) {
////        duration = listOf(3.s)
////        timingFunction = listOf(AnimationTimingFunction.EaseInOut)
////      }
//      transform {
//        rotate(degrees)
//      }
//    }
//  }

  Div({ classes(CircleProgressStyle.wrap) }) {
    Div({ classes(CircleProgressStyle.container) }) {
      Div({
        classes(CircleProgressStyle.maskFull)
        style {
          transform {
            rotate(fullRotation)
          }
//          animation(style.animationKeyframes) {
//            duration = listOf(3.s)
//            timingFunction = listOf(AnimationTimingFunction.EaseInOut)
//          }
        }
      }) {
        Div({
          classes(CircleProgressStyle.fill)
          style {
            transform {
              rotate(fullRotation)
            }
          }
        }) {}
      }
      Div({ classes(CircleProgressStyle.maskHalf) }) {
        Div({
          classes(CircleProgressStyle.fill)
          style {
            transform {
              rotate(halfRotation)
            }
//            animation(style.animationKeyframes) {
//              duration = listOf(3.s)
//              timingFunction = listOf(AnimationTimingFunction.EaseInOut)
//            }
          }
        }) {}
      }
      Div({ classes(CircleProgressStyle.inside) }) {
        Text("$level%")
      }
    }
  }
}