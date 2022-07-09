/*
 * Created by Nils Druyen on 01-02-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.pages

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.style.AboutStyle
import de.nilsdruyen.portfolio.utils.Constants
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
fun About() {
  val openLink: (String) -> Unit = {
    window.open(url = it, target = "_blank")
  }

  Section({ classes(AboutStyle.section) }) {
    Div({ classes(AboutStyle.container) }) {
      Div({ classes(AboutStyle.imgContainer) }) {
        Img("assets/thea.jpg", attrs = { classes(AboutStyle.img) })
        Button({
          classes(AboutStyle.githubLink)
          onClick {
            openLink("https://github.com/nilsjr")
          }
        }) {
          Text("Github Profile")
        }
      }
      P({ classes(AboutStyle.info) }) {
        Text(Constants.LOREM)
      }
    }
    Skills()
    Experience()
  }
}