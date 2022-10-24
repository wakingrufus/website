package com.github.wakingrufus.website.lib.dashboard

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.lib.css
import kotlinx.css.backgroundColor
import kotlinx.css.em
import kotlinx.css.paddingBottom
import kotlinx.html.*

class TopicPanel(val name: String) {
    private val entries = mutableListOf<TopicEntry>()
    fun entry(name: String, url: String) {
        entries.add(TopicEntry(name, url))
    }

    operator fun invoke(div: DIV) {
        div.apply {
            div {
                style = css {
                    backgroundColor = MyStyles.SUB_SUB_BACKGROUND_COLOR
                    paddingBottom = 1.em
                }
                h3 {
                    +name
                }
                span {
                    entries.forEachIndexed { idx, it ->
                        a(href = it.link) { +it.name }
                        if (idx < entries.size - 1) {
                            +" - "
                        }
                    }
                }
            }
        }
    }
}