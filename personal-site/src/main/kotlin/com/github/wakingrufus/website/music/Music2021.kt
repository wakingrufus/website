package com.github.wakingrufus.website.music

val bestMusic2021 = bestMusicOf(2021) {
    albumOfTheYear("To See the Next Part of the Dream", "Parannoul") {
        link("https://parannoul.bandcamp.com/album/to-see-the-next-part-of-the-dream")
        review(
            "This DIY shoegaze album from South Korea channels the ennui of being stuck in the same day-to-day drudgery, never fulfilling your dreams."
        )
        tag("shoegaze")
    }
    album("Afrique Victime", "Mdou Moctar") {
        link("https://mdoumoctar.bandcamp.com/album/afrique-victime")
    }
    album("Isles", "Bicep") {
        link("https://bicep.bandcamp.com/album/isles")
    }
    album("Collapsed In Sunbeams", "Arlo Parks") {
        link("https://arloparks.bandcamp.com/album/collapsed-in-sunbeams")
    }
    album("Humans in a Pool", "Takeshi's Cashew") {
        link("https://lautluise.bandcamp.com/album/humans-in-a-pool-2")
    }
    album("För Allting", "Makthaverskan") {
        link("https://makthaverskan.bandcamp.com/album/f-r-allting")
    }
    album("Outlaw R&B", "Night Beats") {
        link("https://nightbeats.bandcamp.com/album/outlaw-r-b")
    }

    track("That's What I Want", "Lil Nas X") {
        link("https://www.youtube.com/watch?v=QDYDRA5JPLE")
    }
    track("Into the Endless Night", "Parannoul") {
        link("https://parannoul.bandcamp.com/track/into-the-endless-night")
        tag("shoegaze")
    }
    track("Sad Cowboy", "Goat Girl") {
        link("https://goatgirl.bandcamp.com/track/sad-cowboy-1")
    }
    track("Rainforest", "Noname") {
        link("https://nonameraps.bandcamp.com/track/rainforest")
    }
    track("Stay in the Car", "Bachelor") {
        link("https://bachelortheband.bandcamp.com/track/stay-in-the-car")
    }
    track("Psalm 23", "Cold Cave") {
        link("https://coldcave.bandcamp.com/track/psalm-23")
    }
    track("Colors", "Parannoul") {
        link("https://longinusrecordings.bandcamp.com/track/colors")
        tag("shoegaze")
    }
    track("All That is True Dies", "Hallows") {
        link("https://hallows.bandcamp.com/track/all-that-is-true-dies-3")
    }
    track("Alibis", "Sneaker Pimps") {
        link("")
    }
    track("Pursuit", "Pixel Grip") {
        link("https://pixelgrip.bandcamp.com/track/pursuit-2")
    }
    track("Not Fit For This", "GHLOW") {
        link("https://ghlow.bandcamp.com/track/not-fit-for-this")
    }
    track("The Creeps", "Garbage") {
        link("https://www.youtube.com/watch?v=O5YFSps8wRU")
    }
    track("Wolves", "Garbage") {
        link("https://www.youtube.com/watch?v=lT4CXVSU18k")
    }
    track("Witchcraft", "Дeva") {
        link("https://movegentlyrecords.bandcamp.com/track/eva-witchcraft")
    }
}
val music2021 = bestMusic2021.toHtmlPage()