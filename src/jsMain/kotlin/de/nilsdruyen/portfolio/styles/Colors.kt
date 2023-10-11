/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.styles

import org.jetbrains.compose.web.css.Color

object Colors {

  val Blue = "#0583f2".toColor()
  val DarkGrey = "#050608".toColor()
}

fun String.toColor() = Color(this)