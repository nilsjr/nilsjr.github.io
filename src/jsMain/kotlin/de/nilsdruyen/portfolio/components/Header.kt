/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.Description
import de.nilsdruyen.portfolio.styles.WebPageStyle
import org.jetbrains.compose.web.attributes.href
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
fun Header() {
  Section({ classes(WebPageStyle.Header.section) }) {
    Div({ classes(WebPageStyle.Header.background) }) { }
    Img(
      src = "assets/nils.jpg",
      attrs = {
        classes(WebPageStyle.Header.profileImage)
      }
    )
    H2({
      classes(WebPageStyle.Header.title)
    }) {
      Text("Hi I'm Nils")
    }
    P({
      classes(WebPageStyle.Header.description)
    }) {
      Text(Description)
    }
    P({
      style {
        marginTop(20.px)
      }
    }) {
      A(attrs = {
        href("mailto:info@nilsdruyen.de")
      }) {
        Text("info@nilsdruyen.de")
      }
    }
  }
}