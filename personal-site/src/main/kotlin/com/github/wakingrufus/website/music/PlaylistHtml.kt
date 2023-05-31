package com.github.wakingrufus.website.music

import com.github.wakingrufus.website.lib.HtmlPage
import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.h1
import kotlinx.html.id
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p

fun Playlist.toHtmlPage(): HtmlPage {
    return playlistPage(this)
}

fun playlistPage(playlist: Playlist): HtmlPage {
    return htmlPage("music-${playlist.name.replace(" ", "-").lowercase()}.html") {
        article(playlist.name) {
            htmlSection {
                if (playlist.description != null) {
                    p { +playlist.description }
                }
                h1 { +"Tracks" }
                a { id = "Tracks" }
                ol {
                    playlist.tracks.forEach { track ->
                        li { a(href = track.link) { +"${track.title} - ${track.artist}" } }
                    }
                }
            }
            footer(myFooter)
        }
    }
}