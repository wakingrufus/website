package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

val music2021 = htmlPage("music-2021.html") {
    article("Best Music of 2021") {
        nav {
            item("#albums", "Albums")
            item("#tracks", "Tracks")
        }
        htmlSection {
            h1 { +"Albums" }
            a { id = "Albums" }
            p{+"These are all the great albums, front-to-back, released this year, that I found."}
            h2 { +"To See the Next Part of the Dream - Parannoul" }
            p { a(href = "https://parannoul.bandcamp.com/album/to-see-the-next-part-of-the-dream") { +"Bandcamp" } }
            p { +"This DIY shoegaze album from South Korea channels the ennui of being stuck in the same day-to-day drudgery, never fulfilling your dreams." }
            h2 { +"Afrique Victime - Mdou Moctar" }
            h2 { +"Isles - Bicep" }
        }
        htmlSection {
            h1 { +"Tracks" }
            a { id = "Tracks" }
            p { +"These are great songs, released this year, excluding songs from top albums." }
            ol {
                li { +"That's What I Want - Lil Nas X" }
                li { +"Into the Endless Night - Parannoul" }
                li { +"Rainforest - Noname" }
                li { +"New Day - Night Beats" }
                li { +"Sad Cowboy - Goat Girl" }
                li { +"Stay in the Car - Bachelor" }
                li { +"Psalm 23 - Cold Cave" }
                li { +"Colors -  Parannoul" }
                li { +"All That is True Dies - Hallows" }
                li { +"Pursuit - Pixel Grip" }
                li { +"Insomnia - Parannoul" }
                li { +"Not Fit For This - GHLOW" }
                li { +"The Creeps - Garbage" }
                li { +"Oboro - DIR EN GREY" }
                li { +"I Am You - Just Mustard" }
                li { +"Wolves - Garbage" }
            }
        }
        footer(myFooter)
    }
}