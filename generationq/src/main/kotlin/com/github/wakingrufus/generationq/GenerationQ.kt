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
        val episode2 = episode("002 - S1E01 - Encounter at Farpoint",
                "https://archive.org/details/001-s1e01-encounter-at-farpoint") {
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

        val episode3 = episode("003 - S1E03 - The Naked Now",
                "https://archive.org/details/002-s1e03-the-naked-now") {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104633016860860724"
            description {
                p {
                    +"We watch “The Naked Now”, quite possibly the worst episode of the series, but hey, sex sells."
                }
            }
        }

        val episode4 = episode("004 - S1E04 - Code of Honor",
                "https://archive.org/details/GenerationQ-004-S1E04CodeOfHonor") {
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

        val episode5 = episode("005 - S1E05 - The Last Outpost",
                "https://archive.org/details/GenerationQ-005-S1E5-TheLastOutpost") {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104689478681842924"
            description {
                p {
                    +"We review The Last Outpost, our first look at the Ferengi."
                }
            }
        }

        val episode6 = episode("006 - S1E06 - Where No One Has Gone Before",
                "https://archive.org/details/generationq-s1e6") {
            discussionUrl = "https://tenforward.social/@TanagraTooter/104830242366600858"
            description {
                p { +"We review Where No One Has Gone Before and take a trip." }
            }
        }
    }
}