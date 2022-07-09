/*
 * Created by Nils Druyen on 01-02-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.pages

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.style.AppStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun Maintainance() {
  Div({ classes(AppStyle.centerContainer) }) {
    Img(src = "assets/lego.png")
    P({ classes(AppStyle.maintainanceText) }) {
      Text("Whoops...")
    }
    P({ classes(AppStyle.maintainanceText) }) {
      Text("Kurze Wartung. Bis später")
    }
  }
}