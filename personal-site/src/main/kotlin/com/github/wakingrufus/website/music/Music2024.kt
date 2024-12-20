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
        link("https://bloodincantation.bandcamp.com/album/absolute-elsewhere")
    }
    album("RATKING", "Brimheim") {
        link("https://brimheim.bandcamp.com/album/ratking")
    }
    album("XII: A gyönyörű álmok ezután jönnek", "Thy Catafalque") {
        link("https://thycatafalqueuk.bandcamp.com/album/xii-a-gy-ny-r-lmok-ezut-n-j-nnek")
    }
    album("Sonido Cósmico", "Hermanos Gutiérrez") {
        link("https://hermanosgutierrez.bandcamp.com/album/sonido-c-smico")
    }
    album("Funeral for Justice", "Mdou Moctar") {
        link("https://mdoumoctar.bandcamp.com/album/funeral-for-justice")
    }
    album("The Promised Land?", "Black Sites") {
        link("https://blacksites.bandcamp.com/album/the-promised-land")
    }
    album("Championne (EP)", "Championne") {
        link("https://championne.bandcamp.com/album/championne")
    }
    album("Mahal", "Glass Beams") {
        link("https://glassbeams.bandcamp.com/album/mahal")
    }
    album("Ngélar", "LAIR") {
        link("https://lairggb.bandcamp.com/album/ng-lar")
    }
    album("Codes", "Anja Huwe") {
        link("https://xmaldeutschland.bandcamp.com/album/codes")
    }
    album(
        "Synthesizing the Silk Roads: Uzbek Disco, Tajik Folktronica, Uyghur Rock & Tatar Jazz from 1980s Soviet Central Asia",
        "Various Artists"
    ) {
        link(
            "https://ostinatorecords.bandcamp.com/album/synthesizing-the-silk-roads-uzbek-disco-tajik-folktronica-uyghur-rock-tatar-jazz-from-1980s-soviet-central-asia"
        )
    }


    // tracks
    track("Gasoline", "I AM THE INTIMIDATOR") {
        link("https://iamtheintimidator.bandcamp.com/track/gasoline")
    }
    track("No Hiding Place", "Jake Blount & Mali Obomsawin") {
        link("https://jakeblountmusic.bandcamp.com/track/no-hiding-place")
    }
    track("The Devil in Me", "DIR EN GREY") {
        link("")
    }
    track("Crooked Road", "Fantastic Negrito") {
        link("https://fantasticnegritomusic.bandcamp.com/track/crooked-road")
    }
    track("Daydream Repeat", "Four Tet") {
        link("https://fourtet.bandcamp.com/track/daydream-repeat-2")
    }
    track("Bul Bul Bul", "Kit Sebastian") {
        link("https://kitsebastian.bandcamp.com/track/bul-bul-bul")
    }
    track("Seeds", "Melt-Banana")
    track("황금빛 강 (Gold River)", "Parannoul") { link("https://parannoul.bandcamp.com/track/gold-river") }
    track("No Se Siente Normal", "Sol y Santi")
    track("Sinner", "Elsy Wameyo")
    track("A Love International", "Khruangbin")
    track("Wipeout", "COALTAR OF THE DEEPERS")

}

val music2024 = bestMusic2024.toHtmlPage()
