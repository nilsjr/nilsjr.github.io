/*
 * Created by Nils Druyen on 01-06-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.models

import org.w3c.dom.url.URL

data class NavItem(
  val url: URL,
  val name: String,
//  val isActive: Boolean,
)