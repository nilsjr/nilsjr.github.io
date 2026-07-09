/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.ui

import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.CSSUnitValue
import org.jetbrains.compose.web.css.GenericStyleSheetBuilder
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.mediaMaxWidth
import org.jetbrains.compose.web.css.mediaMinWidth
import org.w3c.dom.HTMLAnchorElement

fun StyleScope.color(value: String) {
  // color hasn't Typed OM yet
  property("color", value.toColor())
}

fun AttrsScope<HTMLAnchorElement>.openInNewTab() {
  target(ATarget.Blank)
  attr("rel", "noopener noreferrer")
}

/**
 * Create the media query for min width.
 *
 * @param width — Linear dimension only allowing web safe sizing.
 */
fun <TBuilder> GenericStyleSheetBuilder<TBuilder>.mediaOnlyScreenMinWidth(width: CSSUnitValue) =
  CSSMediaQuery.Only(CSSMediaQuery.MediaType(CSSMediaQuery.MediaType.Enum.Screen), mediaMinWidth(width))

fun <TBuilder> GenericStyleSheetBuilder<TBuilder>.mediaOnlyScreenMaxWidth(width: CSSUnitValue) =
  CSSMediaQuery.Only(CSSMediaQuery.MediaType(CSSMediaQuery.MediaType.Enum.Screen), mediaMaxWidth(width))