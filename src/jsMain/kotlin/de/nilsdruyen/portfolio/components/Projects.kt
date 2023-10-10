/*
 * Created by Nils Druyen on 10-10-2023
 * Copyright © 2023 Nils Druyen. All rights reserved.
 */

package de.nilsdruyen.portfolio.components

import androidx.compose.runtime.Composable
import de.nilsdruyen.portfolio.data.Model
import de.nilsdruyen.portfolio.gridRow
import de.nilsdruyen.portfolio.styles.Style
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg
import org.jetbrains.compose.web.svg.fill
import org.jetbrains.compose.web.svg.height
import org.jetbrains.compose.web.svg.width

@Composable
fun projects() {
  gridRow {
    Div({
      classes(
        Style.gridCol12,
        Style.maxWidth,
        Style.mxAuto,
      )
    }) {
      Div({
        classes(
          Style.borderX,
          Style.borderGray,
          Style.dotted,
          Style.Grid.col12,
        )
        style {
          padding(16.px)
        }
      }) {
        P({ classes(Style.Section.title) }) {
          Text("Projects")
        }
        Div({
          style {
            display(DisplayStyle.Grid)
            gridTemplateColumns("repeat(6,minmax(0,1fr))")
            gap(1.cssRem)
            position(Position.Relative)
            marginTop(1.cssRem)
          }
        }) {
          Model.projects.forEach {
            Div({
              classes(
                Style.Grid.col2,
                Style.Projects.container,
              )
              style {
//                backgroundColor(Color.white)
                borderRadius(8.px)
                border {
                  width = 1.px
                  color = Color.lightgray
                  style = LineStyle.Solid
                }
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                padding(1.cssRem)
              }
            }) {
              A(
                href = it.link,
                attrs = {
                  target(ATarget.Blank)
                  style {
                    height(100.percent)
                  }
                }
              ) {
                Div({
                  style {
                    height(100.percent)
                    display(DisplayStyle.Flex)
                    alignItems(AlignItems.FlexStart)
                    flexDirection(FlexDirection.Column)
                  }
                }) {
                  P { Text(it.title) }
                  P({
                    classes(Style.Section.subtitle)
                    style {
                      marginTop(1.cssRem)
                      marginBottom(1.cssRem)
                      flex(1, 1, 0.percent)
                    }
                  }) { Text(it.subtitle) }
                  viewMore()
                }
              }
            }
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun viewMore() {
  Div({
    style {
      border {
        width = 1.px
        color = Color.lightgray
        style = LineStyle.Solid
      }
      borderRadius(.5.cssRem)
      display(DisplayStyle.LegacyInlineFlex)
      padding(.5.cssRem)
      alignItems("center")
      property("transition", "all .5s ease")
      property("background-color", "rgb(125 125 125/var(--project-bg-opacity))")
    }
  }) {
    Div({
      style {
        fontSize(.875.cssRem)
        fontWeight(700)
        lineHeight("1")
      }
    }) {
      Text("View more")
    }
    Div({
      style {
        width(1.cssRem)
        height(1.cssRem)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        marginLeft(.5.cssRem)
      }
    }) {
      Svg(
        viewBox = "0 0 8 8",
        attrs = {
          width(.5.cssRem)
          height(.5.cssRem)
          fill("none")
        }
      ) {
        Path(
          d = "M6.8291 6.82849L6.8291 1.17163M6.8291 1.17163L1.17225 1.17163M6.8291 1.17163L1.17188 6.82849",
          attrs = {
            style {
              property("stroke", "currentColor")
              property("stroke-width", "1.5")
              property("stroke-linecap", "round")
              property("stroke-linejoin", "round")
            }
          },
        )
      }
    }
  }
}