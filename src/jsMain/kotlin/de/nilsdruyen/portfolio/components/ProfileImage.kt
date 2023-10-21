/*
 * Created by Nils Druyen on 10-14-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.nilsdruyen.portfolio.ui.Style
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

private val images = listOf(
  "nils",
  "nils02",
  "nils03",
  "nils04",
  "nils05",
)

@Composable
fun image() {
  var image by remember { mutableStateOf("nils") }

  LaunchedEffect(Unit) {
    while (true) {
      delay(5_000)
      val randomList = images.filter { it != image }
      image = randomList.random()
    }
  }

  Div({
    classes(
      Style.borderGray,
      Style.paddingMedium,
      Style.Grid.borderR,
      Style.Grid.borderSmallL,
      Style.Grid.image,
    )
  }) {
    Div({
      classes(Style.Grid.imageContainer)
    }) {
      images.forEach {
        Img(
          src = "assets/$it.jpg",
          attrs = { classes(Style.profileImage, addStyle(image == it)) }
        )
      }
    }
  }
}

fun addStyle(show: Boolean) = if (show) Style.visible else Style.hidden