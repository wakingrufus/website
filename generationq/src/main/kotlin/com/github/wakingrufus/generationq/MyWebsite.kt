package com.github.wakingrufus.generationq

import kotlinx.css.*
import kotlinx.html.*
import java.io.File

object Paths {
    val CSS_PATH = "styles.css"
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
        link(href = "http://generationq.libsyn.com/rss", type = "application/rss+xml", rel = "alternate") {
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
                    +"If you like us, please consider supporting us via "
                    a(href = "https://www.patreon.com/generationqpod") { +"Patreon" }
                    +" and get some bonus content as well."
                }
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
                h1 { +"Latest Episode" }
                GenerationQ.episodes.last()(this)
            }

            div {
                style = css {
                    paddingLeft = 1.em
                }
                h1 { +"Episodes" }
                span { a(href = "#" + GenerationQ.season1.id()) { +GenerationQ.season1.name } }
                GenerationQ.episode1(this)

                div {
                    style = css {
                        paddingTop = 1.em
                        paddingRight = 1.em
                        paddingBottom = 1.em
                        paddingLeft = 1.em
                        borderWidth = 2.px
                        borderColor = Color.silver
                    }
                    GenerationQ.season1.invoke(this)
                }
            }
        }
        footer { myFooter() }
    }
}