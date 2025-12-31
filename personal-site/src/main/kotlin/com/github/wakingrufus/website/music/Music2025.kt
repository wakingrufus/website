package com.github.wakingrufus.website.music

val bestMusic2025 = bestMusicOf(2025) {
    albumOfTheYear("In Ways", "Slung") {
        link("https://slungband.bandcamp.com/album/in-ways")
        review(
            """The debut from Slung is an alt-rock album that goes hard at times but balances that with some deep grooves.
Overall, it is not quite as in-your-face as you might expect after the first track, as there are a lot of slow burns. 
But those tracks hit hard once the time is right. The album is solid all the way through.
"""
        )
    }
    // albums
    album("V", "Naxatras") {
        link("https://sprintsmusic.bandcamp.com/album/letter-to-self")
        review("This was a close runner-up for AOTY for me. Really good spaced-out stoner prog-rock.")
    }
    album("Are We All Angels", "Scowl") {
        link("https://scowl831.bandcamp.com/album/are-we-all-angels")
        review("Another runner-up: This album is from a hardcore band with great pop sensibilities.")
    }
    album("Le Point de Non-Retour", "Point Mort") {
        link("https://pointmortband.bandcamp.com/album/le-point-de-non-retour")
        tag("metal")
    }
    album("LUX", "ROSALÍA") {
        link("https://www.amazon.com/dp/B0FXHD7CKY/")
    }
    album("Distimia", "Los Membrillos") {
        link("https://losmembrillos.bandcamp.com/album/distimia")
        tag("shoegaze")
    }
    album("Pirouette", "Model/Actriz") {
        link("https://modelactriz.bandcamp.com/album/pirouette")
    }
    album("The Age Of Ephemerality", "BRUIT ≤") {
        link("https://bruitofficial.bandcamp.com/album/the-age-of-ephemerality-2")
    }
    album("The Exodus of Gravity", "Arcadea") {
        link("https://arcadea.bandcamp.com/album/the-exodus-of-gravity")
        review("Ever wondered what Mastodon would sound like with synths instead of guitars? Check it out here.")
    }
    album("My First Album", "Jessica Winter") {
        link("https://jessicawinter.bandcamp.com/album/my-first-album")
    }
    album("We Were Just Here", "Just Mustard"){
        link("https://justmustard.bandcamp.com/album/we-were-just-here")
    }
    album("The Spin", "Messa"){
        link("https://messa.bandcamp.com/album/the-spin")
    }
    // tallah?

    // tracks
    track("I Feel Numb", "Rosa Bordallo") {
        link("https://rosabordallo.bandcamp.com/track/i-feel-numb")
    }
    track("Junior", "Great Grandpa") {
        link("https://greatgrandpa.bandcamp.com/track/junior")
    }
    track("Hard Times", "The Devil Makes Three") {
        link("https://thedevilmakesthree.bandcamp.com/track/hard-times")
    }
    track("Don't Be Seen with Me", "Avalon Emerson") {
        link("https://avalonemerson.bandcamp.com/track/don-t-be-seen-with-me")
    }
    track("Elegantly Wasted", "Hermanos Gutiérrez Ft. Leon Bridges") {
        link("https://hermanosgutierrez.bandcamp.com/track/elegantly-wasted")
    }
    track("Cityscape", "Hibou") {
        link("https://hibouband.bandcamp.com/track/cityscape-2")
    }
    track("Beneath a Lightless Star", "HOLY FAWN") {
        link("https://holyfawn.bandcamp.com/track/beneath-a-lightless-star")
    }
    track("Blastbeat Falafal", "Igorrr") {
        link("https://igorrr.bandcamp.com/track/blastbeat-falafel-2")
    }
    track("Expressionless", "la lune") {
        link("https://lalunelalunelalunelalune.bandcamp.com/track/expressionless")
    }
    track("Reincarnation of a Witch", "Lamp of Murmuur") {
        link("https://lampofmurmuur.bandcamp.com/track/reincarnation-of-a-witch")
        tag("metal")
    }
    track("HelloWorld.java", "Nanowar of Steel") {
        link("https://nanowarofsteel.bandcamp.com/track/helloworld-java-fix-metal-true-version-feat-intellij")
        tag("metal")
    }
    track("Afterlife", "Sharon Van Etten & The Attachment Theory") {
        link("https://sharonvanetten.bandcamp.com/track/afterlife")
    }
    track("Elderberry Wine", "Wednesday") {
        link("https://wednesdayband.bandcamp.com/track/elderberry-wine-2")
    }
    track("My Type", "Sudan Archives") {
        link("https://sudanarchives.bandcamp.com/track/my-type")
    }
    track("Jealousy is Lethal", "Pixel Grip") {
        link("https://pixelgrip.bandcamp.com/track/jealousy-is-lethal")
    }
    track("Split", "Pixel Grip") {
        link("https://pixelgrip.bandcamp.com/track/split-2")
    }
    track("Behind the Green Door", "Night Beats"){

    }
    track("Indelible", "Pelican"){
tag("metal")
    }
    track("Tanana", "Portugal. The Man"){

    }
    track("Descartes", "SPRINTS"){

    }
    track("Adagio", "Σtella"){

    }
}

val music2025 = bestMusic2025.toHtmlPage()
