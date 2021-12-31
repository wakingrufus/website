package com.github.wakingrufus.website.music

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

val music2021 = htmlPage("music-2021.html") {
    article("Best Music of 2021") {
        htmlSection {
            a { id = "Albums" }
            h1 { +"Album of the Year" }
            h3 { +"To See the Next Part of the Dream - Parannoul" }
            p { a(href = "https://parannoul.bandcamp.com/album/to-see-the-next-part-of-the-dream") { +"Bandcamp" } }
            p { +"This DIY shoegaze album from South Korea channels the ennui of being stuck in the same day-to-day drudgery, never fulfilling your dreams." }
            h1 { +"Great Albums" }
            p { +"These albums are good from start to finish, have multiple stand out great songs, or both." }
            ul {
                li {
                    a(href = "https://mdoumoctar.bandcamp.com/album/afrique-victime") { +"Afrique Victime - Mdou Moctar" }
                }
                li {
                    a(href = "https://bicep.bandcamp.com/album/isles") { +"Isles - Bicep" }
                }
                li {
                    a(href = "https://lautluise.bandcamp.com/album/humans-in-a-pool-2") { +"Humans in a Pool - Takeshi's Cashew" }
                }
                li {
                    a(href = "https://arloparks.bandcamp.com/album/collapsed-in-sunbeams") { +"Collapsed In Sunbeams - Arlo Parks" }
                }
            }
        }
        htmlSection {
            h1 { +"Tracks" }
            a { id = "Tracks" }
            p { +"These are great songs, released this year, excluding songs from top albums." }
            ol {
                li { a(href = "https://www.youtube.com/watch?v=QDYDRA5JPLE") { +"That's What I Want - Lil Nas X" } }
                li {
                    a(
                        href = "https://parannoul.bandcamp.com/track/into-the-endless-night"
                    ) { +"Into the Endless Night - Parannoul" }
                }
                li { a(href = "https://nightbeats.bandcamp.com/track/new-day") { +"New Day - Night Beats" } }
                li { a(href = "https://goatgirl.bandcamp.com/track/sad-cowboy-1") { +"Sad Cowboy - Goat Girl" } }
                li { a(href = "https://nonameraps.bandcamp.com/track/rainforest") { +"Rainforest - Noname" } }
                li {
                    a(
                        href = "https://bachelortheband.bandcamp.com/track/stay-in-the-car"
                    ) { +"Stay in the Car - Bachelor" }
                }
                li { a(href = "https://coldcave.bandcamp.com/track/psalm-23") { +"Psalm 23 - Cold Cave" } }
                li { a(href = "https://longinusrecordings.bandcamp.com/track/colors") { +"Colors - Parannoul" } }
                li {
                    a(
                        href = "https://hallows.bandcamp.com/track/all-that-is-true-dies-3"
                    ) { +"All That is True Dies - Hallows" }
                }
                li { a(href = "https://pixelgrip.bandcamp.com/track/pursuit-2") { +"Pursuit - Pixel Grip" } }
                li { a(href = "https://www.youtube.com/watch?v=IGdFAQz35Vc") { +"朧 - DIR EN GREY" } }
                li { a(href = "https://longinusrecordings.bandcamp.com/track/insomnia") { +"Insomnia - Parannoul" } }
                li { a(href = "https://ghlow.bandcamp.com/track/not-fit-for-this") { +"Not Fit For This - GHLOW" } }
                li { a(href = "https://www.youtube.com/watch?v=O5YFSps8wRU") { +"The Creeps - Garbage" } }
                li { a(href = "https://justmustard.bandcamp.com/track/i-am-you") { +"I Am You - Just Mustard" } }
                li { a(href = "https://www.youtube.com/watch?v=lT4CXVSU18k") { +"Wolves - Garbage" } }
                li { a(href = "https://movegentlyrecords.bandcamp.com/track/eva-witchcraft") { +"Witchcraft - Дeva" } }
            }
        }
        footer(myFooter)
    }
}