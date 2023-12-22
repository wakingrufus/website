package com.github.wakingrufus.website.music

val bestMusic2020 = bestMusicOf(2020) {
    albumOfTheYear("Just Look At That Sky", "Ganser") {
        link("https://ganser.bandcamp.com/album/just-look-at-that-sky")
    }
    album("Black Aura My Sun", "Deserta")
    album("Women in Music Pt. III", "HAIM")
    album("Spider Tales", "Jake Blount")
    album("Ghosts of West Virginia", "Steve Earle & The Dukes")

    track("Fight Like Ida B & Marsha P", "Ric Wilson")
    track("落ちた事のある空", "DIR EN GREY")
    track("Aspects", "STR4TA")
    track("Unglitched", "Дeva")
    track("Sad Music", "Jessica Winter")
    track("Zepyuri nman", "Ladaniva")
}

val music2020 = bestMusic2020.toHtmlPage()