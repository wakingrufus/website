package com.github.wakingrufus.website.music

val bestMusic2022 = bestMusicOf(2022) {
    albumOfTheYear("Squeeze", "Sasami") {
        link("https://sasami.bandcamp.com/album/squeeze")
        review(
            "Sasami has an eclectic style that risks being scatterbrained, but it works, and showcases her incredible range."
        )
    }
    // albums
    album("El Bueno y el Malo", "Hermanos Gutierrez") {
        link("https://hermanosgutierrez.bandcamp.com/album/el-bueno-y-el-malo")
    }
    album("Eye Escapes", "Kraus") {
        link("https://willkraus.bandcamp.com/album/eye-escapes")
    }
    album("Heart Under", "Just Mustard") {
        link("https://justmustard.bandcamp.com/album/heart-under")
    }
    album("Every Moment, Everything You Need", "Deserta") {
        link("https://deserta.bandcamp.com/album/every-moment-everything-you-need")
        tag("shoegaze")
    }
    album("Dimensional Bleed", "Holy Fawn") {
        link("https://holyfawn.bandcamp.com/album/dimensional-bleed")
    }
    album("As Above So Below", "Sampa the Great") {
        link("https://sampathegreat.bandcamp.com/album/as-above-so-below")
    }
    album("Up and Away", "Σtella") {
        link("https://stellawithasigma.bandcamp.com/album/up-and-away")
    }
    album("Nilotic (EP)", "Elsy Wameyo") {
        link("https://elsywameyo.bandcamp.com/album/nilotic")
    }
    album("Yesterday Is Heavy", "Lil Silva") {
        link("https://lilsilva.bandcamp.com/album/yesterday-is-heavy")
    }
    album("RLYR", "RLYR") {
        link("https://gileadmedia.bandcamp.com/album/rlyr")
    }
    album("The Last Thing Left", "Say Sue Me") {
        link("https://saysueme.bandcamp.com/album/the-last-thing-left")
    }
    album("Drif", "Heilung") {
        link("https://heilung.bandcamp.com/album/drif")
    }
    // tracks
    track("SLUGS", "Beautiful Freaks") {
        link("https://fr3aks.bandcamp.com/track/slugs-2")
    }
    track("どうしてもあなたをゆるせない -tears-", "Boris") {
        link("https://boris.bandcamp.com/track/tears")
    }
    track("Give Me a Reason", "Boy Harsher") {
        link("https://boyharsher.bandcamp.com/track/give-me-a-reason-2")
    }
    track("The Man Was Burning", "Jake Blount") {
        link("https://jakeblountmusic.bandcamp.com/track/the-man-was-burning")
        tag("folk")
    }
    track("Pedestal", "Yugen Blakrok") {
        link("https://yugenblakrok.bandcamp.com/track/pedestal")
    }
    track("It's Mutual", "James and the Cold Gun") {
        link("https://jamesandthecoldgun.bandcamp.com/track/its-mutual")
    }
    track("Cut", "Working Men's Club") {
        link("https://jamesandthecoldgun.bandcamp.com/track/its-mutual")
    }
    track("Mistakes", "Sharon Van Etten") {
        link("https://sharonvanetten.bandcamp.com/track/mistakes-2")
    }
    track("People Watching", "Ganser") {
        link("https://ganser.bandcamp.com/track/people-watching")
    }
    track("Paper Route", "Grynch") {
        link("https://grynch.bandcamp.com/track/paper-route-feat-sir-mix-a-lot-2")
    }
    track("The Same", "The Smile") {
        link("https://thesmile.bandcamp.com/track/the-same")
    }
    track("Szélben Szőtt", "Дeva (feat. Labek & Chrobák)") {
        link("https://movegentlyrecords.bandcamp.com/track/sz-lben-sz-tt-feat-labek-chrob-k")
    }
    track("The World is Dying, Hold My Hand", "Void River)") {
        link("https://voidriver.bandcamp.com/track/the-world-is-dying-hold-my-hand")
        tag("Noise")
    }
    track("Protection From Evil", "Ibibio Sound Machine") {
        link("https://ibibiosoundmachine.bandcamp.com/track/protection-from-evil")
    }
    track("Love Me More", "Mitski") {
        link("https://mitski.bandcamp.com/track/love-me-more")
    }
    track("Gammawake", "ELK") {
        link("https://elkberlin.bandcamp.com/track/gammawake")
        tag("post-rock")
    }
    track("Glitterati", "Panda Riot") {
        link("https://pandariot.bandcamp.com/track/glitterati")
    }
    track("Ooo La La", "Brijean") {
        link("https://brijean.bandcamp.com/track/ooo-la-la")
    }
}
val music2022 = bestMusic2022.toHtmlPage()
