/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.styles.WebPageStyle
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

data class ProfileLink(
  val image: String,
  val link: String,
)

val links = listOf(
  ProfileLink("github.png", "https://github.com/nilsjr"),
  ProfileLink("twitter.png", "https://twitter.com/NilsJr"),
  ProfileLink("xing.png", "https://www.xing.com/profile/Nils_Druyen/cv"),
)

@Composable
fun Footer() {
  Section({ classes(WebPageStyle.Footer.section) }) {
    H3({ classes(WebPageStyle.Footer.linksTitle) }) {
      Text("stay in touch with me")
    }
    Div({ classes(WebPageStyle.Footer.linksContainer) }) {
      links.forEach {
        Link(it)
      }
    }
  }
  org.jetbrains.compose.web.dom.Footer({
    classes(WebPageStyle.Footer.background)
  }) {
    P({ classes(WebPageStyle.Footer.text) }) {
      Text("© 2022 Nils Druyen")
    }
  }
}

@Composable
fun Link(profileLink: ProfileLink) {
  A(href = profileLink.link) {
    Img(
      "assets/links/${profileLink.image}",
      attrs = {
        classes(WebPageStyle.Footer.linkImage)
      }
    )
  }
}