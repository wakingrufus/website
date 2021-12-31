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
            h1 { +"Albums" }
            a { id = "Albums" }
            p{+"These are all the great albums, released this year, that I found."}
            h3 { +"Just Look At That Sky - Ganser" }
            p { a(href = "https://parannoul.bandcamp.com/album/to-see-the-next-part-of-the-dream") { +"Bandcamp" } }
            h3 { +"Women in Music Pt. III - HAIM" }
            h3 { +"Black Aura My Sun - Deserta" }
            h3 {+"Ghosts of West Virginia - Steve Earle & The Dukes"}
            h3 {+"Spider Tales - Jake Blount"}
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
            }
        }
        footer(myFooter)
    }
}