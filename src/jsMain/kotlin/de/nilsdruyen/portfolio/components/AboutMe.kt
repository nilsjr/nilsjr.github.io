/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.styles.WebPageStyle
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Suppress("MaxLineLength", "MaximumLineLength")
@Composable
fun AboutMe() {
  Section({ classes(WebPageStyle.AboutMe.section) }) {
    H2({
      classes(WebPageStyle.AboutMe.title)
    }) {
      Text("i am ")
      B {
        Text("software engineer")
      }
    }
    H3({
      classes(WebPageStyle.AboutMe.subtitle)
    }) {
      Text("at Fressnapf")
    }
    Img("https://camo.githubusercontent.com/38b93a2e20d5e6eee244a674ada49dbb7aa284e80afbde3cc82061f662fe1dce/68747470733a2f2f7777772e77616b6575702d636f6d6d756e69636174696f6e732e64652f77702d636f6e74656e742f75706c6f6164732f323031382f30342f6c6f676f2d66726573736e6170662e706e67")
  }
}