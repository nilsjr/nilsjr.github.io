/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.nilsdruyen.portfolio.data.models.Project
import de.nilsdruyen.portfolio.data.projects
import de.nilsdruyen.portfolio.styles.WebPageStyle
import kotlinx.browser.window
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun Projects() {
  val openLink: (String) -> Unit = {
    window.open(url = it, target = "_blank")
  }

  Section({
    id("projects")
    classes(WebPageStyle.Projects.section)
  }) {
    H1({ classes(WebPageStyle.Projects.projectHeading) }) {
      Text("some of my projects")
    }
    Div({ classes(WebPageStyle.Projects.projectContainer) }) {
      projects.forEach {
        ProjectItem(it, openLink)
      }
    }
  }
}

private val maxAngleRange = -22f..22f
private fun lerp(min: Float, max: Float, norm: Float): Float = (max - min) * norm + min

private fun Int.toDegrees(inverted: Boolean = false): Float {
  val fraction = this.toFloat() / 100
  return if (!inverted) {
    lerp(maxAngleRange.start, maxAngleRange.endInclusive, fraction)
  } else {
    lerp(maxAngleRange.endInclusive, maxAngleRange.start, fraction)
  }
}

@ExperimentalComposeWebApi
@Composable
fun ProjectItem(project: Project, openLink: (url: String) -> Unit) {
  val percent = remember { mutableStateOf(Pair(50, 50)) }

  Div({
    classes(WebPageStyle.Projects.projectItemContainer)
    style {
      val degX = percent.value.second.toDegrees().deg
      val degY = percent.value.first.toDegrees().deg

      transform {
        rotateX(degX)
        rotateY(degY)
      }
      property("-webkit-transform", "rotateX($degX) rotateY($degY)")
    }
  }) {
    DisposableEffect(Unit) {
      val div = scopeElement
//      div.onmouseenter = {
//        projectHovered = true
//        offset = Offset(it.offsetX.roundToInt(), it.offsetY.roundToInt())
//          .calculateTilt(div.clientWidth, div.clientHeight)
//        it.preventDefault()
//      }
      div.onpointermove = {
        val rect = it.target.unsafeCast<HTMLDivElement>().getBoundingClientRect()
        val absX = it.clientX - rect.left
        val absY = it.clientY - rect.top
        val perX = ((100 / rect.width) * absX).roundToInt()
        val perY = ((100 / rect.height) * absY).roundToInt()
        percent.value = Pair(perX, perY)
//        offset = Offset(it.offsetX.roundToInt(), it.offsetY.roundToInt())
//          .calculateTilt(div.clientWidth, div.clientHeight)
        true
      }
      div.onmouseleave = {
        percent.value = Pair(50, 50)
        true
      }
      onDispose {}
    }

    Div({
      classes(WebPageStyle.Projects.projectItemContent)
    }) {
      H1({ classes(WebPageStyle.Projects.projectTitle) }) {
        Text(project.name + "${percent.value}")
      }
      Button({
        classes(WebPageStyle.Projects.projectBtn)
        onClick {
          openLink(project.link)
        }
      }) {
        Text("Show me")
      }
    }
  }
}