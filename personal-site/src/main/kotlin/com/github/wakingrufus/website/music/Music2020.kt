package com.github.wakingrufus.website.music

val bestMusic2020 = bestMusicOf(2020) {
    albumOfTheYear("Just Look At That Sky", "Ganser") {
        link("https://ganser.bandcamp.com/album/just-look-at-that-sky")
    }
    album("Black Aura My Sun", "Deserta") {
        link("https://deserta.bandcamp.com/album/black-aura-my-sun")
        review("Runner up")
        tag("shoegaze")
    }
    album("Women in Music Pt. III", "HAIM")
    album("Spider Tales", "Jake Blount")
    album("Ghosts of West Virginia", "Steve Earle & The Dukes")
    album("Spirituality and Distortion", "Igorrr") {
        link("https://igorrr.bandcamp.com/album/spirituality-and-distortion")
    }

    track("Fight Like Ida B & Marsha P", "Ric Wilson")
    track("Aspects", "STR4TA")
    track("Unglitched", "Дeva")
    track("Sad Music", "Jessica Winter")
    track("Zepyuri nman", "Ladaniva")
    track("Kef Chilini", "Ladaniva")
    track("Bullshit Anthem", "Fantastic Negrito")
}

val music2020 = bestMusic2020.toHtmlPage()