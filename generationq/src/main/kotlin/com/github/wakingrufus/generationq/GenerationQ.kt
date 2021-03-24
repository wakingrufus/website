package com.github.wakingrufus.generationq

import kotlinx.html.a
import kotlinx.html.p

object GenerationQ : Podcast() {
    val episode1 = episode("001 - Intro", "https://archive.org/details/GenerationQ-000-Intro") {
        discussionUrl = "https://tenforward.social/@TanagraTooter/104560793009745258"
        description {
            p { +"The hosts John and Andy introduce themselves, and what their goal is with this podcast." }
        }
    }

    val season1 = season("Season 1") {
        val episode2 = episode(
            "002 - S1E01 - Encounter at Farpoint",
            "https://archive.org/details/001-s1e01-encounter-at-farpoint"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104593651455494052"
            description {
                p {
                    +"""We look at the pilot episode "Encounter at Farpoint", 
                |a 2-part episode that introduces us to the Next Generation crew and establishes some of the main themes of this series.
            """.trimMargin()
                }
                p { +"This episode is longer due to covering both parts of the episode (technically episodes 1&2)" }
            }
        }

        val episode3 = episode(
            "003 - S1E03 - The Naked Now",
            "https://archive.org/details/002-s1e03-the-naked-now"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104633016860860724"
            imageName = "ep2.jpg"
            description {
                p {
                    +"We watch “The Naked Now”, quite possibly the worst episode of the series, but hey, sex sells."
                }
            }
        }

        val episode4 = episode(
            "004 - S1E04 - Code of Honor",
            "https://archive.org/details/GenerationQ-004-S1E04CodeOfHonor"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104689478681842924"
            description {
                p {
                    +"""We review “Code of Honor”, possibly THE most racist, terrible episode in all of Trek. 
                    |We also discuss the concept of Scifi/Fantasy species as stand ins for race. We referenced """
                        .trimMargin()
                    a(href = "https://www.denofgeek.com/tv/revisiting-star-trek-tng-code-of-honor/") { +"Den of Geek" }
                }
            }
        }

        val episode5 = episode(
            "005 - S1E05 - The Last Outpost",
            "https://archive.org/details/GenerationQ-005-S1E5-TheLastOutpost"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104689478681842924"
            imageName = "ep5.png"
            description {
                p {
                    +"We review The Last Outpost, our first look at the Ferengi."
                }
            }
        }

        val episode6 = episode(
            "006 - S1E06 - Where No One Has Gone Before",
            "https://archive.org/details/generationq-s1e6"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104830242366600858"
            imageName = "ep6.png"
            description {
                p { +"We review Where No One Has Gone Before and take a trip." }
            }
        }

        val episode7 = episode(
            "007 - S1E07 - Lonely Among Us",
            "https://archive.org/details/GenerationQ-S1E07-LonelyAmongUs"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104880119881367074"
            imageName = "ep7.png"
            description {
                p { +"We review Lonely Among Us, in which we upload ourselves to the cloud." }
            }
        }

        episode(
            "008 - S1E08 - Justice",
            "https://archive.org/details/generationq-008"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/105036382906419475"
            imageName = "ep8.png"
            description {
                p { +"You know, I came to this sex planet to get away from things like lectures about justice." }
            }
        }

        episode(
            "009 - S1E09 - The Battle",
            "https://archive.org/details/generation-q-009"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/105084689212363611"
            imageName = "ep9.png"
            description {
                p { +"I didn't expect to be explaining hashing algorithms on this podcast, but here we are." }
            }
        }

        episode(
            "010 - S1E10 - Hide and Q",
            "https://archive.org/details/generationq-010"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/105266789173514217"
            imageName = "ep10.png"
            description {
                p {
                    +"""I apologize for the inconsistency we have been getting these out, but editing can be time consuming,
                        |and life gets in the way. 
                        |In this episode, we review S1E10 "Hide and Q".  
                        |We talk about Q and Picard's ongoing bet, and how it relates to politics in our time."""
                        .trimMargin()
                }
            }
        }
        episode(
            "011 - S1E11 - Haven",
            "https://archive.org/details/generation-q-011"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/105471469922559613"
            imageName = "ep11.png"
            description {
                p { +"In this episode, we review S1E11 \"Haven\"." }
            }
        }
        episode(
            "012 - S1E12 - The Big Goodbye",
            "https://archive.org/details/generationq-012"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/105512678150722191"
            imageName = "ep12.png"
            description {
                p { +"In this episode, we review S1E12 \"The Big Goodbye\". We talk Ubik, Raymond Chandler, and DRM." }
            }
        }
        episode(
            "013 - S1E13 - Datalore",
            "https://archive.org/details/generationq-013"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/105583733375028262"
            imageName = "ep13.png"
            description {
                p {
                    +"""
                    In this episode, we review S1E13 "Datalore", and talk about why COVID is our Crystalline Entity.
                """.trimIndent()
                }
            }
        }
        episode(
            "014 - S1E14 - Angel One",
            "https://archive.org/details/generationq-014"
        ) {
            discussionUrl = "https://tenforward.social/@TanagraTooter/105675385094791069"
            imageName = "ep14.png"
            description {
                p {
                    +"In this episode, we discuss Angel One, discuss revolutions, anarchists, and try our new format as a shock jock morning show."
                }
                p { a(href = "https://vimeo.com/434141029") { +"The Murder of Fred Hampton (1971, The Film Group)" } }
            }
        }
        episode(
            "015 - S1E15 - 11001001",
            "https://archive.org/details/generationq-015"
        ){
            discussionUrl = "https://tenforward.social/@TanagraTooter/105720436485763423"
            imageName = "ep15.png"
            description {
                p {
                    +"In this episode, we discuss 11001001. Jack in to the mainframe and help us save the world."
                }
            }
        }
        episode(
            "016 - S1E16 - Too Short a Season",
                "https://archive.org/details/generationq-016"
        ){
            discussionUrl = "https://tenforward.social/@TanagraTooter/105942530656600418"
            imageName = "ep16.png"
            description {
                p {
                    +"You are never too old to do a contra."
                }
            }
        }
//        episode(){
//            description {
//                p {
//                    +"Don't scab for the kidnappers..."
//                }
//            }
//        }
    }
}
