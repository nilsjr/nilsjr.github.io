/*
 * Created by Nils Druyen on 07-09-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.nilsdruyen.portfolio.ui.TerminalStyle
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import kotlin.random.Random

private const val MAX_LINES = 11
private const val TYPE_DELAY_MS = 55L
private const val OUTPUT_DELAY_MS = 350L
private const val PAUSE_BETWEEN_SCRIPTS_MS = 2200L

private sealed interface Step {
  data class Type(val text: String) : Step
  data class Print(val text: String) : Step
  data class Pause(val ms: Long) : Step
}

private val SCRIPTS: List<List<Step>> = listOf(
  listOf(
    Step.Type("$ vim UserRepository.kt"),
    Step.Pause(600),
    Step.Type("suspend fun loadUser(id: String): User ="),
    Step.Type("  api.getUser(id).toDomain()"),
    Step.Pause(500),
    Step.Type(":wq"),
    Step.Print(""),
  ),
  listOf(
    Step.Type("$ ./gradlew assembleRelease"),
    Step.Print("> Task :app:compileReleaseKotlin"),
    Step.Print("> Task :app:assembleRelease"),
    Step.Pause(700),
    Step.Print("BUILD SUCCESSFUL in 24s"),
  ),
  listOf(
    Step.Type("$ ./gradlew test"),
    Step.Print("> Task :app:testDebugUnitTest"),
    Step.Pause(900),
    Step.Print("142 tests completed, 0 failed"),
    Step.Print("BUILD SUCCESSFUL in 18s"),
  ),
  listOf(
    Step.Type("$ git add ."),
    Step.Type("$ git commit -m \"fix: handle empty state\""),
    Step.Print("[main 3f2a1c9] fix: handle empty state"),
    Step.Type("$ git push"),
    Step.Pause(600),
    Step.Print("To github.com:nilsjr/Koncept.git"),
  ),
  listOf(
    Step.Type("$ kotlin"),
    Step.Print("Welcome to Kotlin (JVM)"),
    Step.Type(">>> listOf(1, 2, 3).sumOf { it * it }"),
    Step.Pause(400),
    Step.Print("res0: kotlin.Int = 14"),
    Step.Type(">>> \"nils\".replaceFirstChar { it.uppercase() }"),
    Step.Pause(400),
    Step.Print("res1: kotlin.String = Nils"),
  ),
  listOf(
    Step.Type("$ vim MainViewModel.kt"),
    Step.Pause(500),
    Step.Type("val state = flow {"),
    Step.Type("  emit(repository.load())"),
    Step.Type("}.stateIn(viewModelScope)"),
    Step.Pause(500),
    Step.Type(":wq"),
    Step.Print(""),
  ),
  listOf(
    Step.Type("$ adb devices"),
    Step.Print("List of devices attached"),
    Step.Print("Pixel_9_Pro     device"),
    Step.Type("$ adb install app-release.apk"),
    Step.Pause(800),
    Step.Print("Success"),
  ),
  listOf(
    Step.Type("$ ./gradlew detekt ktlintCheck"),
    Step.Print("> Task :detekt"),
    Step.Print("> Task :ktlintCheck"),
    Step.Pause(600),
    Step.Print("BUILD SUCCESSFUL in 9s"),
  ),
)

@Composable
fun miniTerminal() {
  var lines by remember { mutableStateOf(listOf<String>()) }
  var current by remember { mutableStateOf("") }

  LaunchedEffect(Unit) {
    if (window.matchMedia("(prefers-reduced-motion: reduce)").matches) {
      lines = listOf("$ ./gradlew assembleRelease", "BUILD SUCCESSFUL in 24s", "$ ")
      return@LaunchedEffect
    }
    fun commit(line: String) {
      lines = (lines + line).takeLast(MAX_LINES)
      current = ""
    }
    var last = -1
    while (true) {
      val index = generateSequence { Random.nextInt(SCRIPTS.size) }.first { it != last }
      last = index
      SCRIPTS[index].forEach { step ->
        when (step) {
          is Step.Type -> {
            step.text.forEach { char ->
              current += char
              delay(TYPE_DELAY_MS + Random.nextLong(60))
            }
            delay(250)
            commit(step.text)
          }

          is Step.Print -> {
            delay(OUTPUT_DELAY_MS)
            commit(step.text)
          }

          is Step.Pause -> delay(step.ms)
        }
      }
      delay(PAUSE_BETWEEN_SCRIPTS_MS)
    }
  }

  Div({ classes(TerminalStyle.miniTerminal) }) {
    Div({ classes(TerminalStyle.miniTerminalHeader) }) {
      Div({ classes(TerminalStyle.dots) }) {
        repeat(3) { Span({ classes(TerminalStyle.dot) }) }
      }
      Span({ classes(TerminalStyle.label) }) { Text("nils@krefeld: ~/work") }
    }
    Div({ classes(TerminalStyle.miniTerminalBody) }) {
      lines.forEach { line -> Div { Text(line.ifEmpty { " " }) } }
      Div {
        Text(current)
        Span({ classes(TerminalStyle.cursor) })
      }
    }
  }
}