/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.ui

import org.jetbrains.compose.web.css.StyleScope

fun StyleScope.color(value: String) {
  // color hasn't Typed OM yet
  property("color", value.toColor())
}