package com.github.wakingrufus.website.music

val bestMusic2019 = bestMusicOf(2019) {
//    albumOfTheYear("Just Look At That Sky", "Ganser") {
//        link("https://ganser.bandcamp.com/album/just-look-at-that-sky")
//    }
    album("LEGACY! LEGACY!", "Jamila Woods")
    album("Nest", "Brutus")
    album("Farseer", "Farseer")
    album("Futha", "Heilung")
    album("Babel", "RANGES")
    album("Blood Year", "Russian Circles")

    track("The World of Mercy", "DIR EN GREY"){
        tag("metal")
    }
    track("Wheels", "The Devil Makes Three")
    track("Seventeen", "Sharon Van Etten ft. Norah Jones")
    track("Final Form", "Sampa the Great")
}

val music2019 = bestMusic2019.toHtmlPage()