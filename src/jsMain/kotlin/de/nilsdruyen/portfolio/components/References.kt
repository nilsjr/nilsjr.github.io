/*
 * Created by Nils Druyen on 07-29-2022
 * Copyright © 2022 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.styles.WebPageStyle
import org.jetbrains.compose.web.dom.Section

@Composable
fun References() {
  Section({ classes(WebPageStyle.References.section) }) {
//    Div({ classes(WebPageStyle.References.background) }){}
    Experience()
    Skills()
  }
}