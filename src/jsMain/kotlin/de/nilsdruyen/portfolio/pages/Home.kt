/*
 * Created by Nils Druyen on 01-02-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.pages

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.style.HomeStyle
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
fun Home() {
  Section({ classes(HomeStyle.section) }) {
    H1({ classes(HomeStyle.heroHeading) }) {
      Text("hello, i am")
      Br { }
      Text(" thea")
    }
    Img(src = "assets/thea.jpg", attrs = { classes(HomeStyle.image) })
  }
}