package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.css
import kotlinx.css.*

class MyStyles {
    companion object {
        val BACKGROUND_COLOR: Color = Color("#111111")
        val LIGHT_BACKGROUND_COLOR: Color = Color("#333333")
        val FONT_COLOR: Color = Color("#EEEEEE")
        val FONT_COLOR_WEAK: Color = Color("#999999")
        val TITLE_FONT_COLOR: Color = Color("#9999FF")
        val FOOTER_FONT_COLOR: Color = Color("#999999")
        val LINK_COLOR: Color = Color("#00FF77")
        val BORDER_COLOR: Color = Color("#FFFFFF")
    }

    fun globalStyles(): String {
        return css {
            body {
                fontFamily = "sans-serif"
                backgroundColor = BACKGROUND_COLOR
                color = FONT_COLOR
                margin(all = 0.px)
            }
            a {
                color = LINK_COLOR
            }
        }
    }

    fun styles(): String {
        return css {
            h1 {
                color = Color("#FFFFFF")
            }
            h2 {
                color = Color("#DDDDDD")
            }
            h3 {
                color = Color("#BBBBBB")
            }
            (".page-content") {
                display = Display.inlineBlock
         //       maxWidth = 79.pct
            }

            (".page-content blockquote") {
                paddingLeft = 10.px
                borderLeftStyle = BorderStyle.solid
                borderLeftWidth = 5.px
            }

            media("screen and (min-resolution: 120dpi)") {
                body {
                    fontSize = 1.5.em
                }
            }
            media("screen and (min-resolution: 192dpi)") {
                body {
                    fontSize = 1.75.em
                }
            }

            media("screen and (max-width: 1080px)") {
                (".navBar") {
                    display = Display.block
                    paddingBottom = 2.em
                    borderBottomStyle = BorderStyle.solid
                    borderBottomColor = BORDER_COLOR

                }
                (".navBar li") {
                    display = Display.inlineBlock
                }
                (".page-content") {
                    display = Display.block
                }
            }
            media("screen and (min-width: 1080px)") {
                (".navBar") {
                    display = Display.inlineBlock
                    paddingRight = 2.pct
                    borderRightStyle = BorderStyle.solid
                    borderRightColor = BORDER_COLOR
                    maxWidth = 18.pct
                }
            }
        }
    }

    fun slideShowStyles(): String {
        return css {
            media("screen and (max-width: 1200px)") {
                body {
                    fontSize = .5.em
                }
            }
            media("screen and (min-width: 1200px) and (max-width: 1900px)") {
                body {
                    fontSize = .75.em
                }
            }
            html {
                height = 100.pct
            }
            body {
                height = 100.pct
            }
        }
    }
}