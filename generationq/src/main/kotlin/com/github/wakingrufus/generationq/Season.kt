package com.github.wakingrufus.generationq

import kotlinx.html.DIV
import kotlinx.html.a
import kotlinx.html.h2
import kotlinx.html.id

@PodcastDsl
open class Season(val name: String) {
    val episodes = mutableListOf<Episode>()
    fun episode(title: String, url: String, builder: Episode.() -> Unit = {}): Episode {
        return Episode(title, url).apply(builder).also {
            episodes.add(it)
        }
    }

    fun id(): String {
        return name.replace("\\s".toRegex(), "")
    }

    operator fun invoke(div: DIV) {
        div.apply {
            a { id = this@Season.id() }
            h2 { +name }
            episodes.forEach {
                it(this)
            }
        }
    }
}