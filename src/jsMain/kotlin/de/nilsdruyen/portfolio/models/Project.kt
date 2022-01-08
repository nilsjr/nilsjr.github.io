/*
 * Created by Nils Druyen on 01-06-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.models

data class Project(
  val name: String,
  val description: String,
  val imageUrl: String,
//  val linkGithubText: String,
  val linkGithub: String,
//  val linkProjectText: String,
  val linkProject: String,
)