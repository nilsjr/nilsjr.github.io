/*
 * Created by Nils Druyen on 10-08-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

@file:JsModule("swetrix")
@file:Suppress("MatchingDeclarationName") // bindings for the whole swetrix module, not one type

package de.nilsdruyen.portfolio.analytics

import kotlin.js.Promise

/** Subset of Swetrix's `LibOptions` used by this site. */
external interface SwetrixOptions {
  var apiURL: String?
}

external fun init(pid: String, options: SwetrixOptions = definedExternally): dynamic

external fun trackViews(): Promise<dynamic>