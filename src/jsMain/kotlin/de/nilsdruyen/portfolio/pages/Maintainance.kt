/*
 * Created by Nils Druyen on 01-02-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.pages

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.style.Component
import org.jetbrains.compose.web.dom.Img

@Composable
fun Maintainance() {
  Img(src = "assets/lego.png", attrs = {
    classes(Component.centerImage)
  })
}