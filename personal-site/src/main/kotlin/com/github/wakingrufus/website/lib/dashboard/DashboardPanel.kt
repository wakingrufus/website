package com.github.wakingrufus.website.lib.dashboard

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.css
import kotlinx.css.BorderStyle
import kotlinx.css.Display
import kotlinx.css.VerticalAlign
import kotlinx.css.em
import kotlinx.html.*

@WebsiteDsl
class DashboardPanel {
    private var title: String? = null
    private var content: DIV.() -> Unit = {}
    fun title(title: String) {
        this.title = title
    }

    fun content(c: DIV.() -> Unit) {
        content = c
    }

    operator fun invoke(div: DIV) {
        div.apply {
            div {
                style = css {
                    backgroundColor = MyStyles.LIGHT_BACKGROUND_COLOR
                    borderStyle = BorderStyle.solid
                    borderColor = MyStyles.FONT_COLOR
                    paddingLeft = 1.em
                    paddingRight = 1.em
                    display = Display.inlineBlock
                    verticalAlign = VerticalAlign.top
                }
                this@DashboardPanel.title?.let { t -> div.h2 { +t } }
                div.content()
            }
        }
    }
}