/*
 * Created by Nils Druyen on 10-12-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

@file:Suppress("MaxLineLength")

package de.nilsdruyen.portfolio.ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg
import org.jetbrains.compose.web.svg.fill
import org.jetbrains.compose.web.svg.height
import org.jetbrains.compose.web.svg.width
import org.w3c.dom.svg.SVGElement

object Icons {

  private const val ICON_SIZE = 36

  @OptIn(ExperimentalComposeWebSvgApi::class)
  @Composable
  fun github(attrBlock: AttrsScope<SVGElement>.() -> Unit) {
    Svg(
      viewBox = "0 0 24 24",
      attrs = {
        width(ICON_SIZE)
        height(ICON_SIZE)
        attrBlock()
      }
    ) { Path(d = GITHUB) }
  }

  @OptIn(ExperimentalComposeWebSvgApi::class)
  @Composable
  fun twitterX(attrBlock: AttrsScope<SVGElement>.() -> Unit) {
    Svg(
      viewBox = "0 0 1200 1227",
      attrs = {
        width(ICON_SIZE)
        height(ICON_SIZE)
        attrBlock()
      }
    ) { Path(d = TWITTER_X) }
  }

  @OptIn(ExperimentalComposeWebSvgApi::class)
  @Composable
  fun instagram(attrBlock: AttrsScope<SVGElement>.() -> Unit) {
    Svg(
      viewBox = "0 0 24 24",
      attrs = {
        width(ICON_SIZE)
        height(ICON_SIZE)
        attrBlock()
      }
    ) { Path(d = INSTAGRAM) }
  }

  @OptIn(ExperimentalComposeWebSvgApi::class)
  @Composable
  fun xing(attrBlock: AttrsScope<SVGElement>.() -> Unit) {
    Svg(
      viewBox = "0 0 24 24",
      attrs = {
        width(ICON_SIZE)
        height(ICON_SIZE)
        attrBlock()
      }
    ) { Path(d = XING) }
  }

  @OptIn(ExperimentalComposeWebSvgApi::class)
  @Composable
  fun linkedin(attrBlock: AttrsScope<SVGElement>.() -> Unit) {
    Svg(
      viewBox = "0 0 24 24",
      attrs = {
        width(ICON_SIZE)
        height(ICON_SIZE)
        attrBlock()
      }
    ) { Path(d = LINKEDIN) }
  }

  @OptIn(ExperimentalComposeWebSvgApi::class)
  @Composable
  fun darkMode(isDark: Boolean) {
    Svg(
      viewBox = "0 -960 960 960",
      attrs = {
        width(24)
        height(24)
        fill(Style.textColor.value().toString())
      }
    ) {
      Path(d = if (isDark) DARK_MODE_FILLED else DARK_MODE)
    }
  }
}

private const val DARK_MODE =
  "M480-120q-150 0-255-105T120-480q0-150 105-255t255-105q14 0 27.5 1t26.5 3q-41 29-65.5 75.5T444-660q0 90 63 153t153 63q55 0 101-24.5t75-65.5q2 13 3 26.5t1 27.5q0 150-105 255T480-120Zm0-80q88 0 158-48.5T740-375q-20 5-40 8t-40 3q-123 0-209.5-86.5T364-660q0-20 3-40t8-40q-78 32-126.5 102T200-480q0 116 82 198t198 82Zm-10-270Z"
private const val DARK_MODE_FILLED =
  "M480-120q-150 0-255-105T120-480q0-150 105-255t255-105q14 0 27.5 1t26.5 3q-41 29-65.5 75.5T444-660q0 90 63 153t153 63q55 0 101-24.5t75-65.5q2 13 3 26.5t1 27.5q0 150-105 255T480-120Z"
private const val GITHUB =
  "M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z"
private const val INSTAGRAM =
  "M12 2.163c3.204 0 3.584.012 4.85.07 3.252.148 4.771 1.691 4.919 4.919.058 1.265.069 1.645.069 4.849 0 3.205-.012 3.584-.069 4.849-.149 3.225-1.664 4.771-4.919 4.919-1.266.058-1.644.07-4.85.07-3.204 0-3.584-.012-4.849-.07-3.26-.149-4.771-1.699-4.919-4.92-.058-1.265-.07-1.644-.07-4.849 0-3.204.013-3.583.07-4.849.149-3.227 1.664-4.771 4.919-4.919 1.266-.057 1.645-.069 4.849-.069zm0-2.163c-3.259 0-3.667.014-4.947.072-4.358.2-6.78 2.618-6.98 6.98-.059 1.281-.073 1.689-.073 4.948 0 3.259.014 3.668.072 4.948.2 4.358 2.618 6.78 6.98 6.98 1.281.058 1.689.072 4.948.072 3.259 0 3.668-.014 4.948-.072 4.354-.2 6.782-2.618 6.979-6.98.059-1.28.073-1.689.073-4.948 0-3.259-.014-3.667-.072-4.947-.196-4.354-2.617-6.78-6.979-6.98-1.281-.059-1.69-.073-4.949-.073zm0 5.838c-3.403 0-6.162 2.759-6.162 6.162s2.759 6.163 6.162 6.163 6.162-2.759 6.162-6.163c0-3.403-2.759-6.162-6.162-6.162zm0 10.162c-2.209 0-4-1.79-4-4 0-2.209 1.791-4 4-4s4 1.791 4 4c0 2.21-1.791 4-4 4zm6.406-11.845c-.796 0-1.441.645-1.441 1.44s.645 1.44 1.441 1.44c.795 0 1.439-.645 1.439-1.44s-.644-1.44-1.439-1.44z"
private const val XING =
  "M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-14.085 15l2.744-4.825-1.846-3.162h3.292l1.83 3.152-2.744 4.835h-3.276zm8.79-1.445l3.514 6.445h-3.252l-3.55-6.445 5.38-9.555h3.289l-5.381 9.555z"
private const val LINKEDIN =
  "M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-11 19h-3v-11h3v11zm-1.5-12.268c-.966 0-1.75-.79-1.75-1.764s.784-1.764 1.75-1.764 1.75.79 1.75 1.764-.783 1.764-1.75 1.764zm13.5 12.268h-3v-5.604c0-3.368-4-3.113-4 0v5.604h-3v-11h3v1.765c1.396-2.586 7-2.777 7 2.476v6.759z"
private const val TWITTER_X =
  "M714.163 519.284L1160.89 0H1055.03L667.137 450.887L357.328 0H0L468.492 681.821L0 1226.37H105.866L515.491 750.218L842.672 1226.37H1200L714.137 519.284H714.163ZM569.165 687.828L521.697 619.934L144.011 79.6944H306.615L611.412 515.685L658.88 583.579L1055.08 1150.3H892.476L569.165 687.854V687.828Z"