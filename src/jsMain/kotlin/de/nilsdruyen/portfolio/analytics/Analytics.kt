/*
 * Created by Nils Druyen on 10-08-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.analytics

private const val SWETRIX_PROJECT_ID = "nU9VrRLm1S9R"
private const val SWETRIX_API_URL = "https://analytics2.nilsjr.dev/backend/v1/log"

/**
 * Starts cookieless page-view tracking against the self-hosted Swetrix instance.
 * The client is bundled from npm, so no third-party script is loaded at runtime.
 */
fun startAnalytics() {
  init(
    SWETRIX_PROJECT_ID,
    js("{}").unsafeCast<SwetrixOptions>().apply { apiURL = SWETRIX_API_URL },
  )
  trackViews().catch { } // blocked or offline: analytics must never surface as a page error
}