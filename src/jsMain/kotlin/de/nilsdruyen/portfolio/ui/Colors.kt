/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.ui

import org.jetbrains.compose.web.css.Color

object Colors {

  val Background = "#0E0D12".toColor()
  val Text = "#D9D6E3".toColor()
  val Bright = "#F4F2FA".toColor()
  val Body = "#A9A3BC".toColor()
  val Muted = "#6B6580".toColor()
  val Purple = "#7F52FF".toColor()
  val Green = "#57D9A3".toColor()
  val Border = "#26232F".toColor()
  val Dot = "#3A3646".toColor()
}

fun String.toColor() = Color(this)