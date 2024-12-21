package com.github.wakingrufus.website.music

val bestMusic2024 = bestMusicOf(2024) {
    albumOfTheYear("The Past is Still Alive", "Hurray for the Riff Raff") {
        link("https://hftrr.bandcamp.com/album/the-past-is-still-alive")
        review(
            """Come for "Alibi", a catchy tune about supporting someone through their addiction, 
while struggling with your own tendencies to become an enabler. 
Stay for "Ogallala", which hit me hard with: 
    "I used to think I was born into the wrong generation. Now I know I was right on time to watch the world burn."
"""
        )
    }
    // albums
    album("Letter to Self", "SPRINTS") {
        link("https://sprintsmusic.bandcamp.com/album/letter-to-self")
        review("This we a close runner up for AOTY for me. I highly recommend this album from an Irish post-punk band")
    }
    album("Below the Waste", "Goat Girl") {
        link("https://goatgirl.bandcamp.com/album/below-the-waste")
    }
    album("Absolute Elsewhere", "Blood Incantation") {
        link("https://bloodincantation.bandcamp.com/album/absolute-elsewhere")
        tag("Metal")
    }
    album("RATKING", "Brimheim") {
        link("https://brimheim.bandcamp.com/album/ratking")
    }
    album("XII: A gyönyörű álmok ezután jönnek", "Thy Catafalque") {
        link("https://thycatafalqueuk.bandcamp.com/album/xii-a-gy-ny-r-lmok-ezut-n-j-nnek")
        tag("Metal")
    }
    album("Sonido Cósmico", "Hermanos Gutiérrez") {
        link("https://hermanosgutierrez.bandcamp.com/album/sonido-c-smico")
    }
    album("Funeral for Justice", "Mdou Moctar") {
        link("https://mdoumoctar.bandcamp.com/album/funeral-for-justice")
    }
    album("The Promised Land?", "Black Sites") {
        link("https://blacksites.bandcamp.com/album/the-promised-land")
        tag("Metal")
        review("The Chicago metal scene is alive and kicking.")
    }
    album("Championne (EP)", "Championne") {
        link("https://championne.bandcamp.com/album/championne")
        review("This is just an EP, but every song is fantastic. I can't wait to hear more from them.")
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
    album("A La Sala", "Khruangbin") {
        link("https://khruangbin.bandcamp.com/album/a-la-sala")
    }
    album(
        "Synthesizing the Silk Roads: Uzbek Disco, Tajik Folktronica, Uyghur Rock & Tatar Jazz from 1980s Soviet Central Asia",
        "Various Artists"
    ) {
        link(
            "https://ostinatorecords.bandcamp.com/album/synthesizing-the-silk-roads-uzbek-disco-tajik-folktronica-uyghur-rock-tatar-jazz-from-1980s-soviet-central-asia"
        )
        review("This is technically old music, but these songs have been collected and released worldwide for the first time. " +
                "It is full of buried treasures from a time and place that is often overlooked.")
    }

    // tracks
    track("Gasoline", "I AM THE INTIMIDATOR") {
        link("https://iamtheintimidator.bandcamp.com/track/gasoline")
        tag("Metal")
    }
    track("No Hiding Place", "Jake Blount & Mali Obomsawin") {
        link("https://jakeblountmusic.bandcamp.com/track/no-hiding-place")
    }
    track("The Devil in Me", "DIR EN GREY") {
        link("https://www.amazon.com/dp/B0D1G6VXT2")
        tag("Metal")
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
    track("Seeds", "Melt-Banana") { link("https://www.amazon.com/dp/B0D51QCLQJ") }
    track("황금빛 강 (Gold River)", "Parannoul") { link("https://parannoul.bandcamp.com/track/gold-river") }
    track("No Se Siente Normal", "Sol y Santi") { link("https://casateca.bandcamp.com/track/no-se-siente-normal-2") }
    track("Sinner", "Elsy Wameyo") { link("https://elsywameyo.bandcamp.com/track/sinner-2") }
    track("Wipeout", "COALTAR OF THE DEEPERS") {
        link("https://boris.bandcamp.com/track/wipeout")
        tag("Metal")
    }
    track("Motherfather", "Machine Girl") { link("https://machinegirl.bandcamp.com/track/motherfather") }
}

val music2024 = bestMusic2024.toHtmlPage()
