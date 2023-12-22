package com.github.wakingrufus.website.music

val bestMusic2023 = bestMusicOf(2023) {
    albumOfTheYear("Chrysalis", "Zanias") {
        link("https://zanias.bandcamp.com/album/chrysalis")
    }
    // albums
    album("The Weight of the Mask", "Svalbard") {
        link("https://svalbard.bandcamp.com/album/the-weight-of-the-mask")
        tag("metal")
    }
    album("Zango", "WITCH") {
        link("https://w-i-t-c-h.bandcamp.com/album/zango")
    }
    album("Black Classical Music", "Yussef Dayes") {
        link("https://yussefdayes.bandcamp.com/album/black-classical-music")
        tag("jazz")
    }
    album("Arc (EP)", "Hibou") {
        link("https://hibouband.bandcamp.com/album/arc")
    }
    album("PoiL Ueda", "PoiL Ueda") {
        link("https://poil.bandcamp.com/album/poil-ueda")
    }
    album("Aşk", "Altin Gün") {
        link("https://altingun.bandcamp.com/album/a-k")
    }
    album("Dogsbody", "Model/Actriz") {
        link("https://modelactriz.bandcamp.com/album/dogsbody")
    }
    album("33", "Ranges") {
        link("https://ranges.bandcamp.com/album/33")
    }
    album("EGRESS", "The Color of Cyan") {
        link("https://thecolorofcyan.bandcamp.com/album/egress")
    }
    album("Fantasy", "M83") {
        link("https://ilovem83.bandcamp.com/album/fantasy")
    }
    album("Rat Saw God", "Wednesday") {
        link("https://wednesdayband.bandcamp.com/album/rat-saw-god")
    }
    album("Sea of Sorrow", "Tombstones In Their Eyes") {
        link("https://tombstonesintheireyes.bandcamp.com/album/sea-of-sorrow-2")
    }
    album("Systemic", "Divide and Dissolve") {
        link("https://divideanddissolve.bandcamp.com/album/systemic")
    }

    // tracks
    track("Insomnia", "Parannoul") {
        link("https://parannoul.bandcamp.com/track/insomnia")
    }
    track("Deep Space (Remix)", "Soft Faith / Love & Pain") {
        link("https://loveandpainmusic.bandcamp.com/track/deep-space-a-remix-by-soft-faith")
    }
    track("Let Me in", "Jessica Winter") {
        link("https://jessicawinter.bandcamp.com/track/let-me-in")
    }
    track("Oral", "Björk ft. Rosalía") {
        link("https://bjork.bandcamp.com/track/oral")
    }
    track("Brass Bell", "Screaming Females") {
        link("https://screamingfemales.bandcamp.com/track/brass-bell")
    }
    track("The Clutch", "Palehound") {
        link("https://palehound.bandcamp.com/track/the-clutch")
    }
    track("Phaethon", "Djunah") {
        link("https://djunah.bandcamp.com/track/phaethon")
    }
    track("Make Believe", "Tanukichan") {
        link("https://tanukichan.bandcamp.com/track/make-believe")
    }
    track("Wild Animals", "Liv.e") {
        link("https://o-liv.bandcamp.com/track/wild-animals")
    }
    track("Waaba Gwa\u200Ḇ\u200Bsoo ", "Saltwater Hank") {
        link("https://saltwaterhank.bandcamp.com/track/waaba-gwa-soo")
    }
    track("Hot Evening", "Avalon Emerson") {
        link("https://avalonemerson.bandcamp.com/track/hot-evening")
    }
    track("City", "Drinking Boys and Girls Choir") {
        link("https://band-dbgc.bandcamp.com/track/city")
    }
    track("My Lovely Cat!", "Deerhoof") {
        link("https://deerhoof.bandcamp.com/track/my-lovely-cat-2")
    }
    track("Blades", "Arlo Parks") {
        link("https://arloparks.bandcamp.com/track/blades")
    }
    track("Cautionary Tale", "Night Beats") {
        link("https://nightbeats.bandcamp.com/track/cautionary-tale")
    }
    track("The Worm", "HMLTD") {
        link("https://hmltd.bandcamp.com/track/the-worm")
    }
    track("Tiny Garden", "Jamila Woods feat. duendita") {
        link("https://jamilawoodsmusic.bandcamp.com/track/tiny-garden-feat-duendita")
    }
    track("rat", "Kara Jackson") {
        link("https://karajackson.bandcamp.com/track/rat")
    }
    track("Talk to Me", "Hallows") {
        link("https://hallows.bandcamp.com/track/talk-to-me")
    }
}

val music2023 = bestMusic2023.toHtmlPage()
