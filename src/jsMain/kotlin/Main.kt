/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {
  var count: Int by mutableStateOf(0)

  renderComposable(rootElementId = "root") {
    Div({ style { padding(25.px) } }) {
      Button(attrs = {
        onClick { count -= 1 }
      }) {
        Text("-")
      }

      Span({ style { padding(15.px) } }) {
        Text("$count")
      }

      Button(attrs = {
        onClick { count += 1 }
      }) {
        Text("+")
      }

      Dashboard()
    }
  }
}

@Composable
fun Dashboard() {
  val currentPath = mutableStateOf(window.location.pathname)

  LaunchedEffect(Unit) {
    window.onpopstate = {
      val newPath = window.location.pathname
      newPath.let {
        currentPath.value = it
      }
    }
  }

  Br { }
  Text("path: ${currentPath.value}")
}

