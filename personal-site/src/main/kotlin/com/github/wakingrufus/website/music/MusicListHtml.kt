package com.github.wakingrufus.website.music

import com.github.wakingrufus.website.lib.HtmlPage
import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

fun YearEndList.toHtmlPage(): HtmlPage {
    return yearEndPage(this)
}

fun yearEndPage(yearEndList: YearEndList): HtmlPage {
    return htmlPage("music-${yearEndList.year}.html") {
        article("Best Music of ${yearEndList.year}") {
            htmlSection {
                a { id = "Albums" }
                yearEndList.albumOfTheYear?.also {
                    h1 { +"Album of the Year" }
                    h3 { +"${it.title} - ${it.artist}" }
                    p { a(href = it.link) { +"Bandcamp" } }
                    it.review?.let {
                        p { +it }
                    }
                }
                h1 { +"Great Albums" }
                p { +"These albums are good from start to finish, have multiple stand out great songs, or both." }
                ul {
                    yearEndList.albums.forEach { album ->
                        li {
                            a(href = album.link) { +"${album.title} - ${album.artist}" }
                        }
                    }
                }
            }
            htmlSection {
                h1 { +"Tracks" }
                a { id = "Tracks" }
                p { +"These are great songs, released this year, excluding songs from top albums." }
                ol {
                    yearEndList.tracks.forEach { track ->
                        li { a(href = track.link) { +"${track.title} - ${track.artist}" } }
                    }
                }
            }
            footer(myFooter)
        }
    }
}