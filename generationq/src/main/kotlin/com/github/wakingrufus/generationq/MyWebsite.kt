package com.github.wakingrufus.generationq

import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.em
import kotlinx.css.px
import kotlinx.html.*
import java.io.File

object Paths {
    val CSS_PATH = "styles.css"
    val RSS_PATH = "rss.xml"
    val SITE_UPDATES_RSS_PATH = "site-updates.xml"
}

val myFooter: FOOTER.() -> Unit = {
    div {
        style = css {
            display = Display.block
        }
        a(href = mainPage.path) { +"Home" }
    }
}

fun HtmlPage.standardHead() {
    this.head {
        style(type = "text/css") { unsafe { raw(MyStyles().globalStyles()) } }
        link(href = Paths.CSS_PATH, rel = "stylesheet")
        link(href = "https://generationq.neocities.org/rss/" + Paths.RSS_PATH, type = "application/rss+xml", rel = "alternate") {
            title = "RSS"
        }
        title(getTitle())
        meta(name = "Description", content = getDescription())
        meta(name = "viewport", content = "width=device-width, initial-scale=1")
    }
}

class MyWebsite {

    fun build(baseDir: File): Website {
        return website(baseDir) {
            cssFile(path = Paths.CSS_PATH, cssString = MyStyles().styles())
            page(mainPage)

        }
    }
}

val mainPage = htmlPage("index.html") {
    standardHead()
    title("Generation Q")
    description("A Star Trek TNG Podcast")
    body {
        pageTitle("Generation Q")
        content {
            div {
                style = css {
                    paddingLeft = 1.em
                }
                p { +"A Star Trek: The Next Generation Podcast" }
                p { a(href = "http://generationq.libsyn.com/rss") { +"RSS" } }
                p {
                    +"Discuss at: "
                    a(href = "https://tenforward.social/@TanagraTooter") {
                        rel = "me"
                        +"tenforward.social"
                    }
                }
            }
            div {
                style = css {
                    paddingLeft = 1.em
                }
                h1 { +"Episodes" }

                div {
                    style = css {
                        backgroundColor = MyStyles.LIGHT_BACKGROUND_COLOR
                        paddingBottom = 1.em
                        borderColor = Color.white
                        borderWidth = 2.px

                    }
                    h2 { +"000 - Intro" }
                    p { +"The hosts John and Andy introduce themselves, and what their goal is with this podcast." }
                    p {
                        a(href = "https://archive.org/details/GenerationQ-000-Intro") { +"MP3" }
                    }
                    p {
                        a(href = "https://tenforward.social/@TanagraTooter/104560793009745258") { +"Discuss this episode" }
                    }
                    p {
                        a(href = "https://www.deviantart.com/benttibisson/art/Star-Trek-the-Next-Generation-painting-617241159") { +"Artwork: Bentti Bisson" }
                    }
                    p {
                        a(href = "https://soundcloud.com/nes-house") {
                            +"Intro Music: NES House - Star Trek the next generation House"
                        }
                    }
                    p {
                        a(href = "https://soundcloud.com/aeop") {
                            +"Outro Music: Aeop - A308 - What Do You Want Will Riker"
                        }
                    }
                }
            }
        }
        footer { myFooter() }
    }
}