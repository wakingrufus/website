package com.github.wakingrufus.website.music

val bestMusic2024 = bestMusicOf(2024) {
    albumOfTheYear("The Past is Still Alive", "Hurray for the Riff Raff") {
        link("https://hftrr.bandcamp.com/album/the-past-is-still-alive")
    }
    // albums
    album("Below the Waste", "Goat Girl") {
        link("https://goatgirl.bandcamp.com/album/below-the-waste")
    }
    album("Letter to Self", "SPRINTS") {
        link("https://sprintsmusic.bandcamp.com/album/letter-to-self")
    }
    album("Absolute Elsewhere", "Blood Incantation") {
        link("")
    }
    album("RATKING", "Brimheim") {
        link("")
    }
    album("Sonido Cósmico", "Hermanos Gutiérrez") {
        link("")
    }
    album("Funeral for Justice", "Mdou Moctar"){
        link("")
    }
    album("The Promised Land?", "Black Sites"){
        link("")
    }
    album("Championne (EP)", "Championne"){
        link("")
    }
    album("Mahal", "Glass Beams")
    album("Ngélar", "LAIR"){
        link("")
    }
    album("Codes","Anja Huwe")
    album(
        "Synthesizing the Silk Roads: Uzbek Disco, Tajik Folktronica, Uyghur Rock & Tatar Jazz from 1980s Soviet Central Asia",
        "Various Artists"
    )


    // tracks
    track("Gasoline", "I AM THE INTIMIDATOR"){
        link("")
    }
    track("No Hiding Place", "Jake Blount & Mali Obomsawin") {
        link("")
    }
    track("The Devil in Me", "DIR EN GREY") {
        link("")
    }
    track("Crooked Road", "Fantastic Negrito") {
        link("")
    }
    track("Daydream Repeat","Four Tet"){
        link("")
    }
    track("Bul Bul Bul", "Kit Sebastian")
    track("Seeds", "Melt-Banana")
    track("황금빛 강 (Gold River)", "Parannoul")
    track("No Se Siente Normal","Sol y Santi")
    track("Sinner", "Elsy Wameyo")
    track("A Love International","Khruangbin")
    track("Wipeout","COALTAR OF THE DEEPERS")

}

val music2024 = bestMusic2024.toHtmlPage()
