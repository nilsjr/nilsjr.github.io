/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.ElementBuilder
import org.jetbrains.compose.web.dom.TagElement
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event
import kotlin.random.Random

private const val FONT_SIZE = 14.0
private const val COLUMN_WIDTH = FONT_SIZE * 1.6
private const val FRAME_INTERVAL_MS = 40.0
private const val RAIN_DENSITY = 0.2
private const val BACKGROUND = "#0E0D12"
private const val TRAIL = "rgba(14,13,18,0.14)"
private const val HEAD = "#B9A1FF"
private const val GREEN = "rgba(87,217,163,0.55)"
private const val PURPLE = "rgba(127,82,255,0.45)"

private val KEYWORDS = listOf(
  "val", "fun", "when", "data", "class", "suspend", "object", "null", "if", "else", "return", "{}", "->", "::", "?:",
)
private val GLYPHS = "01{}<>=+-*/;:val funktolin".toList()

@Composable
fun codeRain() {
  TagElement<HTMLCanvasElement>(
    elementBuilder = ElementBuilder.createBuilder("canvas"),
    applyAttrs = {
      classes(TerminalStyle.rainCanvas)
      ref { canvas ->
        val rain = CodeRain(canvas)
        rain.start()
        onDispose { rain.stop() }
      }
    },
    content = null,
  )
}

private class Drop(var y: Double, var speed: Double, var word: String? = null, var wordLeft: Int = 0)

private class CodeRain(private val canvas: HTMLCanvasElement) {

  private val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
  private val reducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)")
  private val onResize: (Event) -> Unit = { setSize() }
  private val onMotionChange: (Event) -> Unit = {
    window.cancelAnimationFrame(rafId)
    if (!reducedMotion.matches) rafId = window.requestAnimationFrame(::tick)
  }
  private var drops: Array<Drop> = emptyArray()
  private var rafId = 0
  private var lastFrame = 0.0
  private var viewWidth = 0.0
  private var viewHeight = 0.0

  fun start() {
    setSize()
    window.addEventListener("resize", onResize)
    reducedMotion.addEventListener("change", onMotionChange)
    if (!reducedMotion.matches) rafId = window.requestAnimationFrame(::tick)
  }

  fun stop() {
    window.cancelAnimationFrame(rafId)
    window.removeEventListener("resize", onResize)
    reducedMotion.removeEventListener("change", onMotionChange)
  }

  private fun setSize() {
    val dpr = window.devicePixelRatio
    viewWidth = window.innerWidth.toDouble()
    viewHeight = window.innerHeight.toDouble()
    canvas.width = (viewWidth * dpr).toInt()
    canvas.height = (viewHeight * dpr).toInt()
    ctx.scale(dpr, dpr)
    val columns = (viewWidth / COLUMN_WIDTH).toInt()
    drops = Array(columns) { Drop(y = Random.nextDouble() * -viewHeight, speed = randomSpeed()) }
    ctx.fillStyle = BACKGROUND
    ctx.fillRect(0.0, 0.0, viewWidth, viewHeight)
  }

  private fun tick(timestamp: Double) {
    rafId = window.requestAnimationFrame(::tick)
    if (timestamp - lastFrame < FRAME_INTERVAL_MS) return
    lastFrame = timestamp

    ctx.fillStyle = TRAIL
    ctx.fillRect(0.0, 0.0, viewWidth, viewHeight)
    ctx.font = "${FONT_SIZE.toInt()}px 'JetBrains Mono', monospace"

    drops.forEachIndexed { index, drop ->
      if (Random.nextDouble() > RAIN_DENSITY * 0.9 + 0.1 && drop.y < 0) return@forEachIndexed
      ctx.fillStyle = nextColor()
      ctx.fillText(nextChar(drop), index * COLUMN_WIDTH, drop.y)
      drop.y += FONT_SIZE * drop.speed
      if (drop.y > viewHeight + 100) recycle(drop)
    }
  }

  private fun nextChar(drop: Drop): String {
    val word = drop.word
    return when {
      drop.wordLeft > 0 && word != null -> {
        val char = word[word.length - drop.wordLeft]
        drop.wordLeft--
        char.toString()
      }

      Random.nextDouble() < 0.02 -> {
        val newWord = KEYWORDS.random()
        drop.word = newWord
        drop.wordLeft = newWord.length - 1
        newWord.first().toString()
      }

      else -> GLYPHS.random().toString()
    }
  }

  private fun nextColor(): String = when {
    Random.nextDouble() < 0.12 -> HEAD
    Random.nextDouble() < 0.15 -> GREEN
    else -> PURPLE
  }

  private fun recycle(drop: Drop) {
    drop.y = Random.nextDouble() * -300
    drop.speed = randomSpeed()
    drop.word = null
    drop.wordLeft = 0
  }

  private fun randomSpeed() = 0.6 + Random.nextDouble() * 1.6
}