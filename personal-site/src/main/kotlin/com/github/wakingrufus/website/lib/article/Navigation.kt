package com.github.wakingrufus.website.lib.article

import com.github.wakingrufus.website.lib.sideNavBar
import kotlinx.html.BODY
import kotlinx.html.a
import kotlinx.html.li

class Navigation {
    private val items: MutableList<Pair<String, String>> = mutableListOf()
    fun item(href: String, name: String) {
        items.add(href to name)
    }

    operator fun invoke(body: BODY) {
        body.sideNavBar {
            items.forEach {
                li { a(href = it.first) { +it.second } }
            }
        }
    }
}