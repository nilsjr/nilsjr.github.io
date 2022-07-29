/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

@file:Suppress("FunctionName")

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.models.Skill
import de.nilsdruyen.portfolio.style.CircleProgressStyle
import de.nilsdruyen.portfolio.style.SkillStyle
import de.nilsdruyen.portfolio.style.TextStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

val skillList = listOf(
  Skill("Kotlin", "assets/skills/kotlin.png", 95, "Test test", SkillStyle.kotlinLevel),
  Skill("Java", "assets/skills/java.png", 90, "", SkillStyle.javaLevel),
  Skill("Android SDK", "assets/skills/android.png", 90, "", SkillStyle.androidLevel),
  Skill("Kotlin Mobile Mulitplatform", "assets/skills/kmm.png", 70, "", SkillStyle.kmmLevel),
  Skill("Koltin Mulitplatform", "assets/skills/kotlin.png", 45, "", SkillStyle.kotlinLevel),
)

@Composable
fun Skills() {
  Div({ classes(SkillStyle.section) }) {
    H1({ classes(TextStyle.heading) }) {
      Text("Skills")
    }
    Div({ classes(SkillStyle.container) }) {
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
    Circle(skill.level)
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

@Composable
fun Circle(level: Int) {
  Div({ classes(CircleProgressStyle.wrap) }) {
    Div({ classes(CircleProgressStyle.container) }) {
      Div({ classes(CircleProgressStyle.maskFull) }) {
        Div({ classes(CircleProgressStyle.fill) }) {}
      }
      Div({ classes(CircleProgressStyle.maskHalf) }) {
        Div({ classes(CircleProgressStyle.fill) }) {}
      }
      Div({ classes(CircleProgressStyle.inside) }) {
        Text("$level%")
      }
    }
  }
}