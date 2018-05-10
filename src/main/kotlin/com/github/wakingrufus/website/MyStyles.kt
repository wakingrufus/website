package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.css
import kotlinx.css.*

class MyStyles {
    companion object {
        val BACKGROUND_COLOR: Color = Color("#111111")
        val FONT_COLOR: Color = Color("#EEEEEE")
        val TITLE_FONT_COLOR: Color = Color("#9999FF")
        val FOOTER_FONT_COLOR: Color = Color("#999999")
        val LINK_COLOR: Color = Color("#9999EE")
        val BORDER_COLOR: Color = Color("#FFFFFF")
    }

    fun styles(): String {
        return css {
            body {
                fontFamily = "sans-serif"
                backgroundColor = BACKGROUND_COLOR
                color = FONT_COLOR
            }
            a {
                color = LINK_COLOR
            }
            media("screen and (max-width: 800px or min-resolution: 96dpi)") {
                body {
                    fontSize = 2.em
                }
            }
            media("screen and (max-width: 800px)") {
                body {
                    fontSize = 2.em
                }
            }
            media("screen and (min-resolution: 96dpi)") {
                body {
                    fontSize = 2.em
                }
            }
        }
    }

    fun slideShowStyles(): String {
        return css {
            html {
                height = 100.pct
            }
            body {
                height = 100.pct
                fontFamily = "sans-serif"
                backgroundColor = BACKGROUND_COLOR
                color = FONT_COLOR
            }
            a {
                color = LINK_COLOR
            }
        }
    }
}