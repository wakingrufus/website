package com.github.wakingrufus.website.music

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import kotlinx.html.a
import kotlinx.html.h1
import kotlinx.html.id

val essentialMetal = htmlPage("essential-metal.html") {
    article("Best Metal Music") {
        htmlSection {
            h1 { +"Albums" }
            a { id = "Albums" }
            albumList(allAlbums.filter { it.tags.contains("metal") }.sortedByDescending { it.rating })
        }
        htmlSection {
            h1 { +"Tracks" }
            a { id = "Tracks" }
            trackList(allYearEndLists.flatMap { it.tracks }.filter { it.tags.contains("metal") })
        }
    }
}