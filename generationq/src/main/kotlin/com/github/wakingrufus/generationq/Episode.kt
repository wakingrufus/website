package com.github.wakingrufus.generationq

import kotlinx.css.Color
import kotlinx.css.em
import kotlinx.css.px
import kotlinx.html.*

class Episode(val title: String, val url: String) {
    var discussionUrl: String? = null
    private var description: DIV.() -> Unit = {}
    fun description(builder: DIV.() -> Unit) {
        this.description = builder
    }

    operator fun invoke(div: DIV){
        div.apply {
            div {
                style = css {
                    backgroundColor = MyStyles.LIGHT_BACKGROUND_COLOR
                    paddingBottom = 1.em
                    borderColor = Color.white
                    borderWidth = 2.px

                }
                h2 { +this@Episode.title }
                description()
                p {
                    a(href = url) { +"MP3" }
                }
                p {
                    a(href = discussionUrl) { +"Discuss this episode" }
                }
                p {
                    span {
                        a(href = "https://www.deviantart.com/benttibisson/art/Star-Trek-the-Next-Generation-painting-617241159") {
                            +"Artwork: Bentti Bisson"
                        }
                    }
                    span {
                        +" - "
                    }
                    span {
                        a(href = "https://soundcloud.com/nes-house") {
                            +"Intro Music: NES House - Star Trek the next generation House"
                        }
                    }
                    span {
                        +" - "
                    }
                    span {
                        a(href = "https://soundcloud.com/aeop") {
                            +"Outro Music: Aeop - A308 - What Do You Want Will Riker"
                        }
                    }
                }
            }
        }
    }
}