/*
 * Created by Nils Druyen on 01-02-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.pages

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.style.AppStyle
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
fun Intro() {
  Section({
    classes(AppStyle.homeSection)
  }) {
    H1({
      classes(AppStyle.heroHeading)
    }) {
      Text("hello, i am")
      Br { }
      Text(" thea")
    }
    Img(src = "assets/thea.jpg", attrs = {
      classes(AppStyle.homeImage)
    })
  }
}