/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.styles

import org.jetbrains.compose.web.css.Color

object Colors {

  const val Blue = "#002147"
  const val Grey = "#f2f2f2"
  const val LightBlue = "#213a57"
}

fun String.toColor() = Color(this)