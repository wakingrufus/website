package com.github.wakingrufus.website.music

import kotlinx.html.ARTICLE
import kotlinx.html.a
import kotlinx.html.li
import kotlinx.html.span
import kotlinx.html.ul

fun ARTICLE.albumList(list: List<Album>) {
    ul {
        list.forEach { album ->
            li {
                a(href = album.link) { +"${album.title} - ${album.artist}" }
                album.review?.also {
                    span { +" - $it" }
                }
            }
        }
    }
}

fun ARTICLE.trackList(list: List<Track>) {
    ul {
        list.forEach { track ->
            li { a(href = track.link) { +"${track.title} - ${track.artist}" } }
        }
    }
}