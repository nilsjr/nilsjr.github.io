/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import de.nilsdruyen.portfolio.ui.rise
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun contributions() {
  Div({
    classes(TerminalStyle.card, TerminalStyle.sectionGap)
    style { rise(480) }
  }) {
    Div({ classes(TerminalStyle.cardLabel) }) { Text("$ ls ~/contributions") }
    Div({ classes(TerminalStyle.repoList) }) {
      repo(
        "https://github.com/mobile-dev-inc/maestro",
        "Maestro/",
        "Painless Mobile UI Automation",
      )
      repo(
        "https://github.com/postmanlabs/postman-code-generators",
        "postman-code-generators/",
        "Common repository for all code generators shipped with Postman",
      )
      repo(
        "https://github.com/google/accompanist",
        "accompanist/",
        "A collection of extension libraries for Jetpack Compose",
      )
      repo(
        "https://github.com/microsoft/azure-gradle-plugins",
        "azure-gradle-plugins/",
        "About Azure Plugins for Gradle",
      )
    }
  }
}
