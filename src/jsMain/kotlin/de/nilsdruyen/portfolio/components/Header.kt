/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.style.HeaderStyle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
fun Header() {
  Section({ classes(HeaderStyle.section) }) {
    Div({ classes(HeaderStyle.background) }) { }
    Img(
      src = "assets/nils.jpg",
      attrs = {
        classes(HeaderStyle.profileImage)
      }
    )
    H2 {
      Text("Hi I'm Nils")
    }
    P {
      Text(Description)
    }
  }
}

const val Description = """
  Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
"""