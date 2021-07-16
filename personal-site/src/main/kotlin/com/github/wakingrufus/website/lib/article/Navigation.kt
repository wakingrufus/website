package com.github.wakingrufus.website.lib.article

import com.github.wakingrufus.website.lib.css
import kotlinx.css.Display
import kotlinx.css.VerticalAlign
import kotlinx.css.display
import kotlinx.css.verticalAlign
import kotlinx.html.NAV
import kotlinx.html.a
import kotlinx.html.classes
import kotlinx.html.style

class Navigation {
    private val items: MutableList<Pair<String, String>> = mutableListOf()
    fun item(href: String, name: String) {
        items.add(href to name)
    }

    operator fun invoke(body: NAV) {
        body.apply {
            classes += "navBar"
            style = css {
                verticalAlign = VerticalAlign.top
            }
            items.forEach {
                a(href = it.first) {
                    style = css {
                        display = Display.block
                    }
                    +it.second
                }
            }
        }
    }
}
