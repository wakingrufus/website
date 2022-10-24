package com.github.wakingrufus.website.lib.dashboard

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.css
import kotlinx.css.*
import kotlinx.html.DIV
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.style

@WebsiteDsl
class DashboardPanel {
    private var title: String? = null
    private var content: DIV.() -> Unit = {}
    private val topics = mutableListOf<TopicPanel>()
    fun title(title: String) {
        this.title = title
    }

    fun content(c: DIV.() -> Unit) {
        content = c
    }

    fun topic(title: String, topic: TopicPanel.() -> Unit) {
        topics.add(
            TopicPanel(title).apply {
                this.topic()
            })
    }

    operator fun invoke(div: DIV) {
        div.apply {
            div {
                style = css {
                    backgroundColor = MyStyles.SUB_BACKGROUND_COLOR
                    borderStyle = BorderStyle.solid
                    borderColor = MyStyles.FONT_COLOR
                    paddingLeft = 1.em
                    paddingRight = 1.em
                    display = Display.inlineBlock
                    verticalAlign = VerticalAlign.top
                }
                this@DashboardPanel.title?.let { t -> div.h2 { +t } }
                this.content()
                topics.forEach {
                    it.invoke(this)
                }
            }
        }

    }
}