/*
 * Created by Nils Druyen on 07-31-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.data.models

data class NavItem(
  val name: String,
  val hash: String = "",
  val isActive: Boolean = false,
)