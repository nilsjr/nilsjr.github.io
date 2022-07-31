/*
 * Created by Nils Druyen on 12-27-2021
 * Copyright © 2021 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.components.AboutMe
import de.nilsdruyen.portfolio.components.Footer
import de.nilsdruyen.portfolio.components.Header
import de.nilsdruyen.portfolio.components.Navigation
import de.nilsdruyen.portfolio.components.Projects
import de.nilsdruyen.portfolio.components.Timeline

@Composable
fun WebPage() {
  println("recompose")

  Navigation()
  Header()
  AboutMe()
  Timeline()
  Projects()
  Footer()
}