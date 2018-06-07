package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.*
import com.github.wakingrufus.website.slideshows.functionalKotlinSlideshow
import kotlinx.html.*
import java.io.File
import java.util.*

object Paths {
    val INDEX_PATH = "index.html"
    val MASTODON_JFX_PATH = "mastodon-jfx.html"
    val CSS_PATH = "styles.css"
    val SLIDESHOW_CSS_PATH = "slideshow_styles.css"
    val FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME = "functional-kotlin-slideshow"
}

fun BODY.sideNav() {
    apply {
        sideNavBar {
            li { a(href = Paths.INDEX_PATH) { +"Home" } }
            li { a(href = Paths.MASTODON_JFX_PATH) { +"mastodon-jfx" } }
            li { a(href = Paths.FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME + "/0.html") { +"Functional Kotlin" } }
        }
    }
}


class MyWebsite {

    fun build(baseDir: File): Website {
        return website(baseDir) {
            cssFile(path = Paths.CSS_PATH, cssString = MyStyles().styles())
            htmlPage(path = Paths.INDEX_PATH, builder = mainPage())
            htmlPage(path = Paths.MASTODON_JFX_PATH, builder = mastodonJfx())
            apply(functionalKotlinSlideshow())
        }
    }
}

fun mainPage(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("wakingrufus")
        sideNav()
        content {
            div {
                p { +"I am a software developer working on open source projects in kotlin. " }
                h3 { +"You can also find me at: " }
                ul {
                    li {
                        a(href = "http://www.github.com/wakingrufus") {
                            +"GitHub"
                        }
                    }
                    li {
                        a(href = "https://mastodon.technology/@wakingrufus") {
                            +"mastodon.technology"
                        }
                    }
                    li {
                        a(href = "https://twitter.com/wakingrufus") {
                            +"Twitter"
                        }
                    }
                }
            }
        }
    }
}


fun mastodonJfx(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("mastodon-jfx")
        sideNav()
        content {

            div {
                p {
                    +"mastodon-jfx is kotlin client for mastodon. See more at "
                    a(href = "https://github.com/wakingrufus/mastodon-jfx") { +"GitHub" }
                    +"."
                }
            }
        }
    }
}