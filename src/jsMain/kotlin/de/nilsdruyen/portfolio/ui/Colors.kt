/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.ui

import de.nilsdruyen.portfolio.ui.Colors.TextGray
import org.jetbrains.compose.web.css.Color

object Colors {

  val Blue = "#0583f2".toColor()
  val DarkGrey = "#050608".toColor()
  val TextGray = "#545454".toColor()
}

object ColorTheme {

  object Light
  object Dark

  fun Light.title() = TextGray
  fun Dark.title() = Color.white
}

fun String.toColor() = Color(this)