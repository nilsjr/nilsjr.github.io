/*
 * Created by Nils Druyen on 07-31-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.data

import de.nilsdruyen.portfolio.model.Experiment
import de.nilsdruyen.portfolio.model.Project

object AboutMe {
  const val TITLE = "About me"
  const val FIRST_LINE = "I'm a passionate Android developer since 2012 working on several B2B & B2C apps."
  const val SECOND_LINE =
    "I live on a small farm on the beautiful Lower Rhine \uD83C\uDDE9\uD83C\uDDEA and work from the home office."
  const val THIRD_LINE = "I like to explore and dig deep in new stuff like kotlin & compose multiplatform."
}

// val skillList = listOf(
//   Skill("Kotlin", "assets/skills/kotlin.png", 95, "Test test", WebPageStyle.Skills.kotlinLevel),
//   Skill("Android SDK", "assets/skills/android.png", 75, "", WebPageStyle.Skills.androidLevel),
//   Skill("Kotlin Mobile Mulitplatform", "assets/skills/kmm.png", 50, "", WebPageStyle.Skills.kmmLevel),
// )
//
// val timeline = listOf(
//   TimelineExp("since 2022", "", "Software Engineer", "Fressnapf | Maxizoo"),
//   TimelineExp("05.2016 - 12.2021", "", "Software Entwickler", "NanoGiants GmbH"),
//   TimelineExp("2010 - 2016", "Angewandte Informatik", "", "Universität Duisburg-Essen"),
// )
// val links = listOf(
//   ProfileLink("github.png", "https://github.com/nilsjr"),
//   ProfileLink("twitter.png", "https://twitter.com/NilsJr"),
//   ProfileLink("xing.png", "https://www.xing.com/profile/Nils_Druyen/cv"),
// )

object Model {

  val experiments = listOf(
    Experiment(
      "Koncept",
      "Android playground app for architecture, new frameworks & other stuff",
      "https://github.com/nilsjr/Koncept",
    ),
    Experiment(
      "ComposeParty",
      "Compose playground project on steriods",
      "https://github.com/nilsjr/ComposeParty",
    ),
    Experiment(
      "nilsjr.github.io",
      "My portfolio webpage",
      "https://github.com/nilsjr/nilsjr.github.io",
    ),
  )

  val projects = listOf(
    Project(
      "Snappy",
      "Android CameraX Library",
      "https://github.com/nilsjr/Snappy",
    ),
    Project(
      "Gradle FTP upload plugin",
      "Gradle plugin for uploading files via ftp",
      "https://github.com/nilsjr/gradle-ftp-upload-plugin"
    ),
  )
}