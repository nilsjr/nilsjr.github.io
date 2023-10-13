/*
 * Created by Nils Druyen on 07-31-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.data

import de.nilsdruyen.portfolio.model.Experiment
import de.nilsdruyen.portfolio.model.Interest
import de.nilsdruyen.portfolio.model.ProfileLink
import de.nilsdruyen.portfolio.model.Project
import de.nilsdruyen.portfolio.model.Work
import de.nilsdruyen.portfolio.ui.Icons

object AboutMe {
  const val TITLE = "About me"
  const val FIRST_LINE = "I'm a passionate Android developer since 2012 working on several B2B & B2C apps."
  const val SECOND_LINE =
    "I live on a small farm on the beautiful Lower Rhine \uD83C\uDDE9\uD83C\uDDEA and work from the home office."
  const val THIRD_LINE =
    "I like to explore and dig deep in new stuff like kotlin multiplatform, compose multiplatform and beyond."
}

object Model {

  val timeline = listOf(
    Work("2010 - 2016", "Angewandte Informatik", "", "Universität Duisburg-Essen"),
    Work("05.2016 - 12.2021", "", "Software Entwickler", "NanoGiants GmbH"),
    Work("since 2022", "", "Software Engineer", "Fressnapf | Maxizoo"),
  )

  val links = listOf(
    ProfileLink("https://github.com/nilsjr") { Icons.github() },
    ProfileLink("https://twitter.com/NilsJr") { Icons.twitterX() },
    ProfileLink("https://www.instagram.com/nils.j.r/?hl=de") { Icons.instagram() },
    ProfileLink("https://www.linkedin.com/in/nils-druyen-235b6024b") { Icons.linkedin() },
    ProfileLink("https://www.xing.com/profile/Nils_Druyen/cv") { Icons.xing() },
  )

  val experiments = listOf(
    Experiment(
      "Koncept",
      "\uD83D\uDD28 Android playground app for architecture, new frameworks & other stuff",
      "https://github.com/nilsjr/Koncept",
    ),
    Experiment(
      "ComposeParty",
      "\uD83C\uDF89 Compose playground project on steriods",
      "https://github.com/nilsjr/ComposeParty",
    ),
    Experiment(
      "nilsjr.github.io",
      "\uD83E\uDDCD My portfolio webpage",
      "https://github.com/nilsjr/nilsjr.github.io",
    ),
  )

  val projects = listOf(
    Project(
      "Snappy",
      "\uD83D\uDCF8 Android CameraX Library",
      "https://github.com/nilsjr/Snappy",
    ),
    Project(
      "Gradle FTP upload plugin",
      "Gradle plugin for uploading files via ftp",
      "https://github.com/nilsjr/gradle-ftp-upload-plugin"
    ),
    Project(
      "WoWMythicPlus",
      "\uD83D\uDD11 WoW M+ overview displaying data from raider.io api",
      "https://github.com/nilsjr/WoWMythicPlus"
    ),
  )

  val contributions = listOf(
    Project(
      "Maestro",
      "Painless Mobile UI Automation",
      "https://github.com/mobile-dev-inc/maestro",
    ),
    Project(
      "postman-code-generators",
      "Common repository for all code generators shipped with Postman",
      "https://github.com/postmanlabs/postman-code-generators",
    ),
    Project(
      "accompanist",
      "A collection of extension libraries for Jetpack Compose",
      "https://github.com/google/accompanist"
    ),
  )

  val interests = listOf(
    Interest("Kotlin"),
    Interest("Android"),
    Interest("Kotlin Mulitplatform"),
    Interest("Compose Mulitplatform"),
  )
}