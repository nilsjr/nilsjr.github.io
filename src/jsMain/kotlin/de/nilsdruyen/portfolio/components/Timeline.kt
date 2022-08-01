/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.models.TimelineExp
import de.nilsdruyen.portfolio.data.timeline
import de.nilsdruyen.portfolio.styles.WebPageStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
fun Timeline() {
  Section({
    id("about")
    classes(WebPageStyle.Timeline.section)
  }) {
    H1({ classes(WebPageStyle.heading) }) {
      Text("Education and experience")
    }
    Div({ classes(WebPageStyle.Timeline.container) }) {
      timeline.forEach {
        TimelineItem(it)
      }
    }
  }
}

@Composable
fun TimelineItem(timeline: TimelineExp) {
  Div({ classes(WebPageStyle.Timeline.card) }) {
    Div({ classes(WebPageStyle.Timeline.cardBody) }) {
      H1({ classes(WebPageStyle.Timeline.cardTitle) }) {
        Text(timeline.name)
      }
      P({ classes(WebPageStyle.Timeline.cardDetail) }) {
        Text(timeline.info)
      }
      P({ classes(WebPageStyle.Timeline.cardDetail) }) {
        Text(timeline.company)
      }
      P({ classes(WebPageStyle.Timeline.cardDetail) }) {
        Text(timeline.role)
      }
    }
  }
}