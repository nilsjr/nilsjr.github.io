/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.AboutMe
import de.nilsdruyen.portfolio.styles.WebPageStyle
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gridColumn
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun WebPage() {
  divider()
  Header({
    classes(
      WebPageStyle.borderB,
      WebPageStyle.borderGray,
    )
  }) {
    Div({
      style {
        height(200.px)
      }
      classes(WebPageStyle.grid)
    }) {
      Div({
        classes(WebPageStyle.borderX, WebPageStyle.borderGray)
        style {
          gridColumn("span 6/span 6")
          display(DisplayStyle.Flex)
          alignItems("center")
        }
      }) {
        P({ classes(WebPageStyle.title) }) {
          Text("nilsjr.")
        }
      }
      Div({
        style { gridColumn("span 1/span 1") }
        classes(WebPageStyle.borderR, WebPageStyle.borderGray)
      }) {}
      Div({
        style { gridColumn("span 5/span 5") }
        classes(WebPageStyle.borderR, WebPageStyle.borderGray)
      }) {}
    }
  }
//  Divider()
  Div({
    classes(
      WebPageStyle.borderB,
      WebPageStyle.borderGray,
    )
  }) {
    Div({
      style {
//        height(100.px)
      }
      classes(WebPageStyle.grid)
    }) {
      Div({
        classes(WebPageStyle.borderX, WebPageStyle.borderGray)
        style { gridColumn("span 6/span 6") }
      }) {
        // description
        P({ classes(WebPageStyle.title2) }) {
          Text("About me")
        }
        P({ classes(WebPageStyle.subtitle) }) {
          Text(AboutMe)
        }
      }
      Div({
        style {
          gridColumn("span 6/span 6")
          display(DisplayStyle.Flex)
          justifyContent(JustifyContent.Center)
          alignItems(AlignItems.Center)
          padding(32.px)
        }
        classes(
          WebPageStyle.borderR,
          WebPageStyle.borderGray,
        )
      }) {
        Img(
          src = "assets/nils.jpg",
          attrs = {
            classes(WebPageStyle.profileImage)
          }
        )
      }
    }
  }
  Div({
    classes(
      WebPageStyle.borderB,
      WebPageStyle.borderGray,
    )
  }) {
    Div({
      style {
        height(200.px)
      }
      classes(WebPageStyle.grid)
    }) {
      Div({
        classes(WebPageStyle.borderX, WebPageStyle.borderGray)
        style { gridColumn("span 4/span 4") }
      }) {

      }
      Div({
        style { gridColumn("span 8/span 8") }
        classes(WebPageStyle.borderR, WebPageStyle.borderGray)
      }) {

      }
    }
  }
  Div({
    classes(
      WebPageStyle.borderB,
      WebPageStyle.borderGray,
    )
  }) {
    Div({
      style {
        height(200.px)
      }
      classes(WebPageStyle.grid)
    }) {
      Div({
        classes(WebPageStyle.borderX, WebPageStyle.borderGray)
        style { gridColumn("span 12/span 12") }
      }) {

      }
    }
  }
}

@Composable
private fun divider() {
  Div({
    classes(
      WebPageStyle.borderB,
      WebPageStyle.borderGray,
    )
  }) {
    Div({ classes(WebPageStyle.gridTop) }) {
      Div({
        classes(WebPageStyle.borderX, WebPageStyle.borderGray)
        style { gridColumn("span 4/span 4") }
      }) {}
      Div({
        style { gridColumn("span 2/span 2") }
        classes(WebPageStyle.borderR, WebPageStyle.borderGray)
      }) {}
      Div({
        style { gridColumn("span 1/span 1") }
        classes(WebPageStyle.borderR, WebPageStyle.borderGray)
      }) {}
      Div({
        style { gridColumn("span 5/span 5") }
        classes(WebPageStyle.borderR, WebPageStyle.borderGray)
      }) {}
    }
  }
}