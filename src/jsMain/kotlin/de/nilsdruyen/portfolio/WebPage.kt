/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.components.aboutMe
import de.nilsdruyen.portfolio.components.contributions
import de.nilsdruyen.portfolio.components.footer
import de.nilsdruyen.portfolio.components.placeholder
import de.nilsdruyen.portfolio.components.projects
import de.nilsdruyen.portfolio.components.title
import de.nilsdruyen.portfolio.components.work

@Composable
fun page(isDark: Boolean, toggleDarkMode: () -> Unit) {
  placeholder()
  title(isDark, toggleDarkMode)
  aboutMe()
  work()
  projects()
  contributions()
  footer()
  placeholder()
}