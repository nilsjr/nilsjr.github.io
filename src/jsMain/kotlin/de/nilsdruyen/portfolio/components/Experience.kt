/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.Constants
import de.nilsdruyen.portfolio.models.TimelineExp
import de.nilsdruyen.portfolio.style.TextStyle
import de.nilsdruyen.portfolio.style.TimelineStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun Experience() {
  Timeline()
}

val timeline = listOf(
  TimelineExp("2020", Constants.LOREM),
  TimelineExp("2006-2010", Constants.LOREM),
  TimelineExp("2006-2010", Constants.LOREM),
  TimelineExp("2006-2010", Constants.LOREM),
  TimelineExp("2006-2010", Constants.LOREM),
)

@Composable
fun Timeline() {
  Div({ classes(TimelineStyle.container) }) {
    H1({ classes(TextStyle.heading) }) {
      Text("Education and experience")
    }
    timeline.forEach {
      TimelineItem(it)
    }
  }
}

@Composable
fun TimelineItem(timeline: TimelineExp) {
  Div({ classes(TimelineStyle.card) }) {
    Div({ classes(TimelineStyle.cardBody) }) {
      H1({ classes(TimelineStyle.cardTitle) }) {
        Text(timeline.name)
      }
      P({ classes(TimelineStyle.cardDetail) }) {
        Text(timeline.info)
      }
    }
  }
}