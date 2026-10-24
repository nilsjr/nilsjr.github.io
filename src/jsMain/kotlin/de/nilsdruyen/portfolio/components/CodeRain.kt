/*
 * Created by Nils Druyen on 07-05-2026
 * Copyright © 2026 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.ui.TerminalStyle
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.ElementBuilder
import org.jetbrains.compose.web.dom.TagElement
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event
import org.w3c.dom.pointerevents.PointerEvent
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

private const val FONT_SIZE = 14.0
private const val COLUMN_WIDTH = FONT_SIZE * 1.6
private const val FRAME_INTERVAL_MS = 40.0
private const val RESIZE_DEBOUNCE_MS = 150
private const val RAIN_DENSITY = 0.2
private const val SMALL_SCREEN_BREAKPOINT = 640.0
private const val BACKGROUND = "#0E0D12"
private const val TRAIL = "rgba(14,13,18,0.14)"

// Umbrella: drops inside this radius around the cursor are pushed sideways to its edge. They get out of the way
// quickly and drift back to their column slowly, which leaves a dry, sheltered gap below the cursor.
private const val SHIELD_RADIUS = 90.0
private const val SHIELD_PUSH = 0.5
private const val SHIELD_RELEASE = 0.08

// Drops slide over the umbrella at this fraction of their speed, so they trace a visible arc around it.
private const val SHIELD_SLOWDOWN = 0.4

// Drops that pass close to the cursor are tinted mint and keep that tint as they fall, fading per frame.
private const val GLOW_RADIUS = 130.0
private const val GLOW_DECAY = 0.93
private const val GLOW_MIN = 0.02

// Click splash: glyphs burst out of the cursor and fall back down with gravity.
private const val SPARK_COUNT = 14
private const val SPARK_LIFE = 34
private const val SPARK_GRAVITY = 0.45
private const val SPARK_MAX = 140

private val HEAD = Rgba(185, 161, 255, 1.0)
private val GREEN = Rgba(87, 217, 163, 0.55)
private val PURPLE = Rgba(127, 82, 255, 0.45)
private val LIT = Rgba(170, 255, 214, 1.0)

private val KEYWORDS = listOf(
  "val", "fun", "when", "data", "class", "suspend", "object", "null", "if", "else", "return", "{}", "->", "::", "?:",
)
private val GLYPHS = "01{}<>=+-*/;:val funktolin".map { it.toString() }

@Composable
fun codeRain() {
  TagElement<HTMLCanvasElement>(
    elementBuilder = ElementBuilder.createBuilder("canvas"),
    applyAttrs = {
      classes(TerminalStyle.rainCanvas)
      attr("aria-hidden", "true")
      ref { canvas ->
        val rain = CodeRain(canvas)
        rain.start()
        onDispose { rain.stop() }
      }
    },
    content = null,
  )
}

private class Drop(
  var y: Double,
  var speed: Double,
  var word: String? = null,
  var wordLeft: Int = 0,
  var offset: Double = 0.0,
  var glow: Double = 0.0,
) {

  /**
   * Eases the sideways [offset] towards the edge of the cursor's umbrella, or back to the drop's column.
   * Returns whether the drop is currently sliding over the umbrella.
   */
  fun shield(columnX: Double, pointer: Pointer): Boolean {
    var target = 0.0
    val dy = y - pointer.y
    if (pointer.active && abs(dy) < SHIELD_RADIUS) {
      val dx = columnX - pointer.x
      val edge = sqrt(SHIELD_RADIUS * SHIELD_RADIUS - dy * dy)
      if (abs(dx) < edge) target = (if (dx < 0) -1 else 1) * (edge - abs(dx))
    }
    val easing = if (abs(target) > abs(offset)) SHIELD_PUSH else SHIELD_RELEASE
    offset += (target - offset) * easing
    return target != 0.0
  }

  /** Tints the drop by its distance to the cursor; the tint then fades frame by frame as the drop falls on. */
  fun tint(x: Double, pointer: Pointer) {
    val proximity = if (pointer.active) {
      val dx = x - pointer.x
      val dy = y - pointer.y
      1 - sqrt(dx * dx + dy * dy) / GLOW_RADIUS
    } else {
      0.0
    }
    glow = max(proximity, glow * GLOW_DECAY).coerceIn(0.0, 1.0)
  }
}

private class Pointer(var x: Double = 0.0, var y: Double = 0.0, var active: Boolean = false)

private class Spark(var x: Double, var y: Double, var vx: Double, var vy: Double, val char: String, var life: Int)

/** Click splash: a ring of glyphs bursts out of the click point and falls back down with gravity. */
private class Splashes {

  private val sparks = ArrayDeque<Spark>()

  fun burst(x: Double, y: Double) {
    repeat(SPARK_COUNT) { index ->
      val angle = 2 * PI * index / SPARK_COUNT + Random.nextDouble() * 0.4
      val speed = 3 + Random.nextDouble() * 5
      sparks.addLast(Spark(x, y, cos(angle) * speed, sin(angle) * speed - 3, GLYPHS.random(), SPARK_LIFE))
    }
    while (sparks.size > SPARK_MAX) sparks.removeFirst()
  }

  fun draw(ctx: CanvasRenderingContext2D) {
    val iterator = sparks.iterator()
    while (iterator.hasNext()) {
      val spark = iterator.next()
      spark.x += spark.vx
      spark.y += spark.vy
      spark.vy += SPARK_GRAVITY
      spark.life--
      if (spark.life <= 0) {
        iterator.remove()
        continue
      }
      ctx.fillStyle = LIT.copy(alpha = spark.life.toDouble() / SPARK_LIFE).css
      ctx.fillText(spark.char, spark.x, spark.y)
    }
  }
}

private data class Rgba(val r: Int, val g: Int, val b: Int, val alpha: Double) {

  /** Built once per instance, so untinted glyphs reuse the same string every frame. */
  val css = "rgba($r,$g,$b,$alpha)"

  fun mix(other: Rgba, amount: Double): Rgba = Rgba(
    r = (r + (other.r - r) * amount).toInt(),
    g = (g + (other.g - g) * amount).toInt(),
    b = (b + (other.b - b) * amount).toInt(),
    alpha = alpha + (other.alpha - alpha) * amount,
  )
}

private class CodeRain(private val canvas: HTMLCanvasElement) {

  private val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
  private val reducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)")
  private val onResize: (Event) -> Unit = {
    window.clearTimeout(resizeTimer)
    resizeTimer = window.setTimeout({ setSize() }, RESIZE_DEBOUNCE_MS)
  }
  private var resizeTimer = 0
  private val pointer = Pointer()
  private val splashes = Splashes()
  private val onPointerMove: (Event) -> Unit = { event ->
    val move = event as PointerEvent
    if (move.pointerType != "touch") {
      pointer.x = move.clientX.toDouble()
      pointer.y = move.clientY.toDouble()
      pointer.active = true
    }
  }
  private val onPointerOut: (Event) -> Unit = { event ->
    // A null relatedTarget means the pointer left the window, not just moved between elements.
    if ((event as PointerEvent).relatedTarget == null) pointer.active = false
  }
  private val onPointerDown: (Event) -> Unit = { event ->
    val down = event as PointerEvent
    // Only a primary click/tap splashes, and only while the rain is animating, so sparks never pile up unseen.
    val animating = !reducedMotion.matches && !isDocumentHidden()
    if (animating && down.isPrimary && down.button.toInt() == 0) {
      splashes.burst(down.clientX.toDouble(), down.clientY.toDouble())
    }
  }
  private val onMotionChange: (Event) -> Unit = {
    window.cancelAnimationFrame(rafId)
    if (!reducedMotion.matches) rafId = window.requestAnimationFrame(::tick)
  }
  private val onVisibilityChange: (Event) -> Unit = {
    window.cancelAnimationFrame(rafId)
    if (!isDocumentHidden() && !reducedMotion.matches) {
      rafId = window.requestAnimationFrame(::tick)
    }
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
    document.addEventListener("visibilitychange", onVisibilityChange)
    window.addEventListener("pointermove", onPointerMove)
    window.addEventListener("pointerdown", onPointerDown)
    document.addEventListener("pointerout", onPointerOut)
    if (!reducedMotion.matches && !isDocumentHidden()) rafId = window.requestAnimationFrame(::tick)
  }

  fun stop() {
    window.cancelAnimationFrame(rafId)
    window.clearTimeout(resizeTimer)
    window.removeEventListener("resize", onResize)
    reducedMotion.removeEventListener("change", onMotionChange)
    document.removeEventListener("visibilitychange", onVisibilityChange)
    window.removeEventListener("pointermove", onPointerMove)
    window.removeEventListener("pointerdown", onPointerDown)
    document.removeEventListener("pointerout", onPointerOut)
  }

  private fun setSize() {
    val dpr = minOf(window.devicePixelRatio, 2.0)
    viewWidth = window.innerWidth.toDouble()
    viewHeight = window.innerHeight.toDouble()
    canvas.width = (viewWidth * dpr).toInt()
    canvas.height = (viewHeight * dpr).toInt()
    ctx.scale(dpr, dpr)
    val effectiveColumnWidth = if (viewWidth < SMALL_SCREEN_BREAKPOINT) COLUMN_WIDTH * 1.6 else COLUMN_WIDTH
    val columns = (viewWidth / effectiveColumnWidth).toInt()
    drops = Array(columns) { index ->
      drops.getOrNull(index) ?: Drop(y = Random.nextDouble() * -viewHeight, speed = randomSpeed())
    }
    ctx.fillStyle = BACKGROUND
    ctx.fillRect(0.0, 0.0, viewWidth, viewHeight)
    ctx.font = "${FONT_SIZE.toInt()}px 'JetBrains Mono', monospace"
  }

  private fun tick(timestamp: Double) {
    rafId = window.requestAnimationFrame(::tick)
    val interval = if (viewWidth < SMALL_SCREEN_BREAKPOINT) FRAME_INTERVAL_MS * 1.5 else FRAME_INTERVAL_MS
    if (timestamp - lastFrame < interval) return
    lastFrame = timestamp

    ctx.fillStyle = TRAIL
    ctx.fillRect(0.0, 0.0, viewWidth, viewHeight)

    drops.forEachIndexed { index, drop ->
      val columnX = index * COLUMN_WIDTH
      val sheltered = drop.shield(columnX, pointer)
      if (drop.y >= 0) {
        val x = columnX + drop.offset
        drop.tint(x, pointer)
        val base = nextColor()
        ctx.fillStyle = if (drop.glow < GLOW_MIN) base.css else base.mix(LIT, drop.glow).css
        ctx.fillText(nextChar(drop), x, drop.y)
      }
      drop.y += FONT_SIZE * drop.speed * (if (sheltered) SHIELD_SLOWDOWN else 1.0)
      if (drop.y > viewHeight + 100) recycle(drop)
    }
    splashes.draw(ctx)
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

      else -> GLYPHS.random()
    }
  }

  private fun nextColor(): Rgba {
    val roll = Random.nextDouble()
    return when {
      roll < 0.12 -> HEAD
      roll < 0.25 -> GREEN
      else -> PURPLE
    }
  }

  private fun recycle(drop: Drop) {
    drop.y = Random.nextDouble() * -viewHeight / RAIN_DENSITY
    drop.speed = randomSpeed()
    drop.word = null
    drop.wordLeft = 0
    drop.offset = 0.0
    drop.glow = 0.0
  }

  private fun randomSpeed() = 0.6 + Random.nextDouble() * 1.6

  @Suppress("UnsafeCastFromDynamic")
  private fun isDocumentHidden(): Boolean = js("document.hidden") as Boolean
}