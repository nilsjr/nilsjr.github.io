/*
 * Created by Nils Druyen on 01-02-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

@file:Suppress("FunctionName")

package de.nilsdruyen.portfolio.pages

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.models.Skill
import de.nilsdruyen.portfolio.style.SkillStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

val skillList = listOf(
  Skill("Kotlin", "assets/skills/kotlin.png", 95, "Test test", SkillStyle.kotlinLevel),
  Skill("Java", "assets/skills/java.png", 90, "", SkillStyle.javaLevel),
  Skill("Android SDK", "", 90, "", SkillStyle.kotlinLevel),
  Skill("Kotlin Mobile Mulitplatform", "", 80, "", SkillStyle.kotlinLevel),
  Skill("Koltin Mulitplatform", "", 65, "", SkillStyle.kotlinLevel),
)

@Composable
fun Skills() {
  Div({
    classes(SkillStyle.section)
  }) {
    H1({
      classes(SkillStyle.heading)
    }) {
      Text("")
    }
    Div({
      classes(SkillStyle.container)
    }) {
      skillList.forEach { SkillItem(it) }
    }
  }
}

@Composable
fun SkillItem(skill: Skill) {
  Div({
    classes(SkillStyle.card)
  }) {
    Img(skill.imageUrl, attrs = {
      classes(SkillStyle.img)
    })
    Div({
      classes(skill.levelStyle)
    }) {
      Text(skill.formattedLevel)
    }
    H1({
      classes(SkillStyle.name)
    }) {
      Text(skill.name)
    }
    P({
      classes(SkillStyle.info)
    }) {
      Text(skill.info)
    }
  }
}