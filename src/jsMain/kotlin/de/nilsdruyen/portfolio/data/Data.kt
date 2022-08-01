/*
 * Created by Nils Druyen on 07-31-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.data

import de.nilsdruyen.portfolio.Constants
import de.nilsdruyen.portfolio.data.models.NavItem
import de.nilsdruyen.portfolio.data.models.ProfileLink
import de.nilsdruyen.portfolio.data.models.Project
import de.nilsdruyen.portfolio.data.models.Skill
import de.nilsdruyen.portfolio.data.models.TimelineExp
import de.nilsdruyen.portfolio.styles.WebPageStyle

@Suppress("TopLevelPropertyNaming")
const val Description = """
  Passionate Android developer since 2012
  I'm currently working from my small farm on the beautiful Lower Rhine 🇩🇪
"""

val pages = listOf(
  NavItem("Home"),
  NavItem("About", "about"),
  NavItem("Projects", "projects"),
)

val skillList = listOf(
  Skill("Kotlin", "assets/skills/kotlin.png", 95, "Test test", WebPageStyle.Skills.kotlinLevel),
  Skill("Android SDK", "assets/skills/android.png", 75, "", WebPageStyle.Skills.androidLevel),
  Skill("Kotlin Mobile Mulitplatform", "assets/skills/kmm.png", 50, "", WebPageStyle.Skills.kmmLevel),
  Skill("Kotlin Mobile Mulitplatform", "assets/skills/kmm.png", 10, "", WebPageStyle.Skills.kmmLevel),
  Skill("Kotlin Mobile Mulitplatform", "assets/skills/kmm.png", 30, "", WebPageStyle.Skills.kmmLevel),
)

val timeline = listOf(
  TimelineExp("since 2022", "", "Software Engineer", "Fressnapf | Maxizoo"),
  TimelineExp("05.2016 - 12.2021", "", "Software Entwickler", "NanoGiants GmbH"),
  TimelineExp("2010 - 2016", "Angewandte Informatik", "", "Universität Duisburg-Essen"),
)

val projects = listOf(
  Project(
    name = "WoW Mythic+ Dashboard",
    description = """
    This is a small webpage for summarizing specific wow character statistics from Raider.IO
     """.trimIndent(),
    imageUrl = "assets/projects/helm.jpg",
//    linkGithubText = "github repo",
    linkGithub = "",
//    linkProjectText = "",
    linkProject = "",
  ),
  Project(
    name = "My board games",
    description = Constants.LOREM,
    imageUrl = "assets/projects/helm.jpg",
//    linkGithubText = "github repo",
    linkGithub = "https://github.com/nilsjr/MyBoardGames",
//    linkProjectText = "see live",
    linkProject = "https://github.com/nilsjr/MyBoardGames",
  ),
  Project(
    name = "Gradle FTP upload plugin",
    description = Constants.LOREM,
    imageUrl = "assets/projects/helm.jpg",
//    linkGithubText = "github repo",
    linkGithub = "https://github.com/nilsjr/gradle-ftp-upload-plugin",
//    linkProjectText = "",
    linkProject = "https://plugins.gradle.org/plugin/de.nilsdruyen.gradle-ftp-upload-plugin",
  ),
)

val links = listOf(
  ProfileLink("github.png", "https://github.com/nilsjr"),
  ProfileLink("twitter.png", "https://twitter.com/NilsJr"),
  ProfileLink("xing.png", "https://www.xing.com/profile/Nils_Druyen/cv"),
)