/*
 * Created by Nils Druyen on 01-08-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.models

data class Skill(
  val name: String,
  val imageUrl: String,
  val level: Int,
  val info: String,
  val levelStyle: String,
) {

  val formattedLevel = "$level%"
}