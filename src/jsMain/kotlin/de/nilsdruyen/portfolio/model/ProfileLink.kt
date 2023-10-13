/*
 * Created by Nils Druyen on 10-12-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.model

import androidx.compose.runtime.Composable

data class ProfileLink(
  val link: String,
  val icon: @Composable () -> Unit,
)