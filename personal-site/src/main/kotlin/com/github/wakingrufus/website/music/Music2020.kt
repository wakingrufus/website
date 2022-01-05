package com.github.wakingrufus.website.music

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

val music2020 = htmlPage("music-2020.html") {
    article("Best Music of 2020") {
        nav {
            item("#albums", "Albums")
            item("#tracks", "Tracks")
        }
        htmlSection {
            a { id = "Albums" }
            h1 { +"Albums" }
            h1 { +"Album of the Year" }
            h3 { +"Just Look At That Sky - Ganser" }
            p { a(href = "https://ganser.bandcamp.com/album/just-look-at-that-sky") { +"Bandcamp" } }
            h1 { +"Great Albums" }
            h3 { +"Women in Music Pt. III - HAIM" }
            h3 { +"Black Aura My Sun - Deserta" }
            h3 { +"Ghosts of West Virginia - Steve Earle & The Dukes" }
            h3 { +"Spider Tales - Jake Blount" }
        }
        htmlSection {
            h1 { +"Tracks" }
            a { id = "Tracks" }
            p { +"These are great songs, released this year, excluding songs from top albums." }
            ol {
                li { +"Fight Like Ida B & Marsha P - Ric Wilson" }
                li { +"Now I'm In It - HAIM" }
                li { +"ochita koto no aru sora - DIR EN GREY" }
                li { +"Aspects - STR4TA" }
                li { +"Unglitched - Дeva" }
            }
        }
        footer(myFooter)
    }
}