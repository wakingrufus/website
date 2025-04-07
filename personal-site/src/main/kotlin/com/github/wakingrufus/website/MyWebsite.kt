package com.github.wakingrufus.website

import com.github.wakingrufus.recipe.Recipe
import com.github.wakingrufus.website.articles.adhocPolymorphism
import com.github.wakingrufus.website.articles.antipatterns
import com.github.wakingrufus.website.articles.coHost
import com.github.wakingrufus.website.articles.criterion2021
import com.github.wakingrufus.website.articles.criterion2022
import com.github.wakingrufus.website.articles.failAgile
import com.github.wakingrufus.website.articles.staticWeb
import com.github.wakingrufus.website.cooking.RecipeIndex
import com.github.wakingrufus.website.lib.HtmlPage
import com.github.wakingrufus.website.lib.Website
import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.cooking.html
import com.github.wakingrufus.website.lib.css
import com.github.wakingrufus.website.lib.dashboard
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.lib.website
import com.github.wakingrufus.website.music.music2020
import com.github.wakingrufus.website.music.music2021
import com.github.wakingrufus.website.music.music2022
import com.github.wakingrufus.website.music.music2023
import com.github.wakingrufus.website.music.music2024
import com.github.wakingrufus.website.slideshows.functionalKotlinSlideshow
import com.github.wakingrufus.website.slideshows.kotlin2019Slideshow
import com.github.wakingrufus.website.slideshows.staticWebSlideshow
import com.github.wakingrufus.website.slideshows.whyDoesAgileFail
import kotlinx.css.Display
import kotlinx.css.Margin
import kotlinx.css.TextAlign
import kotlinx.css.VerticalAlign
import kotlinx.css.display
import kotlinx.css.em
import kotlinx.css.height
import kotlinx.css.margin
import kotlinx.css.paddingLeft
import kotlinx.css.paddingRight
import kotlinx.css.px
import kotlinx.css.textAlign
import kotlinx.css.verticalAlign
import kotlinx.html.DIV
import kotlinx.html.FOOTER
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.footer
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.h3
import kotlinx.html.img
import kotlinx.html.li
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.p
import kotlinx.html.picture
import kotlinx.html.span
import kotlinx.html.style
import kotlinx.html.title
import kotlinx.html.ul
import kotlinx.html.unsafe
import java.io.File

object Paths {
    val INDEX_PATH = "index.html"
    val CSS_PATH = "styles.css"
    val SLIDESHOW_CSS_PATH = "slideshow_styles.css"
    val FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME = "functional-kotlin-slideshow"
    val KOTLIN_2019_SLIDESHOW_BASE_NAME = "kotlin-2019"
    val RSS_PATH = "rss.xml"
    val SITE_UPDATES_RSS_PATH = "site-updates.xml"
    val STATIC_WEB_SLIDESHOW_BASE_NAME = "static-web"
    val FAIL_AGILE_SLIDESHOW_BASE_NAME = "fail-agile"
}

val myFooter: FOOTER.() -> Unit = {
    div {
        style = css {
            display = Display.block
        }
        a(href = mainPage.path) { +"Home" }
        +" - "
        a(href = feeds.path) { +"Feeds" }
    }
}

fun HtmlPage.standardHead() {
    this.head {
        style(type = "text/css") { unsafe { raw(MyStyles().globalStyles()) } }
        link(href = Paths.CSS_PATH, rel = "stylesheet")
        link(
            href = "https://wakingrufus.neocities.org/rss/" + Paths.RSS_PATH, type = "application/rss+xml",
            rel = "alternate"
        ) {
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
            page(feeds)
            page(antipatterns)
            page(staticWeb)
            page(travel)
            page(adhocPolymorphism)
            page(failAgile)
            page(coHost)
            page(music2020)
            page(music2021)
            page(music2022)
            page(music2023)
            page(music2024)
            page(criterion2021)
            page(criterion2022)

            resource("fediverse-sm.png")
            resource("fediverse-60.png")
            resource("github-sm.png")
            resource("github-60.png")

            RecipeIndex.recipes.forEach {
                page(it.recipePage())
            }

            rssFeed(path = Paths.RSS_PATH, feedContents = feed())
            //    rssFeed(path = Paths.SITE_UPDATES_RSS_PATH, feedContents = siteUpdateFeed())
            apply(functionalKotlinSlideshow())
            apply(staticWebSlideshow())
            apply(whyDoesAgileFail())
            apply(kotlin2019Slideshow())
        }
    }
}

val mainPage = htmlPage("index.html") {
    standardHead()
    title("John Burns")
    description("Personal home page for John Burns, a software developer working on open source projects in kotlin.")
    body {
        style = css {
            textAlign = TextAlign.center
        }
        content {
            h1 {
                style = css {
                    textAlign = TextAlign.center
                }
                +"John Burns"
            }
            div {
                style = css {
                    paddingLeft = 1.em
                }
                p {
                    +"Platform Engineer. "
                    +"OSS Developer. "
                    +"Community organizer. "
                    +"Conference speaker. "
                }
            }
            div {
                style = css {
                    textAlign = TextAlign.center
                    display = Display.inlineBlock
                }
                span {
                    style = css {
                        height = 48.px
                        display = Display.inlineBlock
                    }
                    a(href = "https://bigshoulders.city/@wakingrufus") {
                        style = css {
                            paddingRight = 0.5.em
                            display = Display.inlineBlock
                        }
                        picture {
                            img(alt = "fediverse logo", src = "fediverse-sm.png") {
                                height = "30"
                                width = "30"
                            }
                        }
                    }
                    p {
                        style = css {
                            display = Display.inlineBlock
                            paddingRight = 2.em
                            verticalAlign = VerticalAlign.top
                            margin = Margin(0.px)
                        }
                        a(href = "https://bigshoulders.city/@wakingrufus") {
                            +"@wakingrufus@bigshoulders.city"
                        }
                    }
                }
                span {
                    style = css {
                        height = 48.px
                        display = Display.inlineBlock
                    }
                    a(href = "https://github.com/wakingrufus") {
                        style = css {
                            paddingRight = 0.5.em
                            display = Display.inlineBlock
                        }
                        picture {
                            img(alt = "github logo", src = "github-sm.png") {
                                height = "30"
                                width = "30"
                            }
                        }
                    }
                    p {
                        style = css {
                            display = Display.inlineBlock
                            paddingRight = 2.em
                            verticalAlign = VerticalAlign.top
                            margin = Margin(0.px)
                        }
                        a(href = "https://github.com/wakingrufus") {
                            +"wakingrufus"
                        }
                    }
                }
            }
            myDashboard()
        }
        footer { myFooter() }
    }
}

val myDashboard: DIV.() -> Unit = {
    dashboard {
        topicPanel("Open Source Projects") {
            subPanel("spring-funk", "https://github.com/wakingrufus/spring-funk") {
                description {
                    +"A framework for declarative DSL configuration for Spring Boot. I founded this project."
                }
            }
            subPanel("ktlint-gradle", "https://github.com/JLLeitschuh/ktlint-gradle") {
                description {
                    +"The most popular gradle plugin for ktlint. "
                    +"I joined this project in 2023 and have been doing ongoing maintenance for it."
                }
            }
            subPanel("JaMM", "https://github.com/wakingrufus/JaMM") {
                description {
                    +"JaMM is a queue-based music library and player for Linux/Mac/Windows desktop. "
                    +"JaMM is written in JavaFx, and distributed with its own runtime using jlink and jpackage. "
                    +"It can be installed from my "
                    a(href = "https://packagecloud.io/wakingrufus/public") {
                        +"Public Debian Repo"
                    }
                    +" or via installer downloads on the GitHub page."
                }
            }
            subPanel("My Personal Site", "https://github.com/wakingrufus/website") {
                description {
                    +"This website is written in pure kotlin using the HTML and CSS DSL. "
                    +"Libraries from this project are published to my "
                    a(href = "https://packagecloud.io/wakingrufus/public") {
                        +"Public Maven Repo"
                    }
                }
            }
            subPanel("Tourney", "https://github.com/wakingrufus/elo-league-jfx") {
                description {
                    +"Tourney is a desktop application which allows you to run an Elo gaming league. "
                }
            }
            subPanel("lib-elo", "https://github.com/wakingrufus/lib-elo") {
                description {
                    +"lib-elo is kotlin library which implements an Elo game rating system. "
                }
            }
        }
        val videoCamera = "\uD83C\uDFA5"
        topicPanel("Writing / Speaking") {
            subPanel("Platform Engineering") {
                expandable()
                entry(
                    "Prefrontal by Cortex",
                    "https://www.listennotes.com/podcasts/prefrontal-by-cortex/john-burns-grubhub-from-T3G-WIn7smU/"
                )
                entry(
                    "Delivering a Great Developer Experience (Devnexus 2024 Slides)",
                    "https://wakingrufus.github.io/developer-experience-platform-engineering/"
                )
                entry("Technology and Friends Podcast", "https://m.youtube.com/watch?v=TYAA3zkJfUc")
                entry(
                    "Intro to Platform Engineering @ GOTO Night Chicago 2024",
                    "https://www.youtube.com/live/12-voRHgaZA?t=5739"
                )
                entry("Bootiful Podcast w/ Josh Long Apr 2023", "https://www.youtube.com/watch?v=DKYN9pGMNcI")
                entry("Developer Productivity Showdown Oct 2022", "https://www.youtube.com/watch?v=o6Epqyh_C3w")
            }
            subPanel("Kotlin") {
                expandable()
                entry("Kotlin for Java Developers") {
                    link("$videoCamera St. Louis JUG", "https://youtu.be/EolIxwpVJJI?t=489")
                    link("Slides", "https://github.com/wakingrufus/kotlin-for-java-devs")
                }
                entry("Functional Kotlin") {
                    link("$videoCamera St. Louis JUG", "https://youtu.be/EolIxwpVJJI?t=2697")
                    link("Slides (Devnexus 2023)", "https://wakingrufus.github.io/functional-kotlin/")
                }
                entry("Kotlin in 2022") {
                    link("$videoCamera CKUG", "https://www.youtube.com/live/PQ61TzbT88M?feature=share&t=2610")
                    link("Slides", "https://wakingrufus.github.io/kotlin-2022/")
                }
                entry("Kotlin in 2019") {
                    link("$videoCamera CKUG", "https://youtu.be/nLKJJASfRh4")
                    link("Slides", Paths.KOTLIN_2019_SLIDESHOW_BASE_NAME + "/0.html")
                }
            }
            subPanel("Spring Boot") {
                expandable()
                entry("Functional Spring Boot"){
                    link("$videoCamera Devnexus 2025", "https://www.youtube.com/watch?v=9njQ8Lun36c")
                    link("Slides", "https://wakingrufus.github.io/functional-spring-boot/")
                }
            }
            subPanel("JVM Community") {
                expandable()
                entry("Foojay Podcast", "https://foojay.io/today/foojay-podcast-27/")
            }
            subPanel("Developing Gradle Plugins") {
                expandable()
                entry("Video (JConf 2022)", "https://www.youtube.com/watch?v=7kNLBmAMAno")
                entry("Slides (JConf 2022)", "https://wakingrufus.github.io/developing-gradle-plugins/")
            }
            subPanel(failAgile.getTitle()) {
                expandable()
                entry("Article", failAgile.path)
            }
            subPanel("Static Web with Kotlin DSLs") {
                expandable()
                entry("Article", staticWeb.path)
                entry("Slides", Paths.STATIC_WEB_SLIDESHOW_BASE_NAME + "/0.html")
                entry("Presentation Video", "https://archive.org/details/march2019-static_web_development_kotlin_dsls")
                entry("Tutorial Video", "https://archive.org/details/kotlin-web-dsl")
            }
            subPanel("Software Development Practices") {
                expandable()
                entry("Testing Functional Kotlin", adhocPolymorphism.path)
                entry("Antipattern: Refactoring as a Separate Ticket", antipatterns.path + "#refactoring")
            }
            subPanel("Social Media / Fediverse") {
                expandable()
                entry("Concerns about Cohost", coHost.path)
            }
        }
    }
    dashboard {
        h2 {
            +"Other Interests"
        }
        panel("Music") {
            h3 { a(href = music2020.path) { +"Best Music of 2020" } }
            h3 { a(href = music2021.path) { +"Best Music of 2021" } }
            h3 { a(href = music2022.path) { +"Best Music of 2022" } }
            h3 { a(href = music2023.path) { +"Best Music of 2023" } }
            h3 { a(href = music2024.path) { +"Best Music of 2024" } }
        }
        panel("TV / Film") {
            h3 {
                a(href = criterion2021.path) {
                    +"2021 Criterion Challenge Recap"
                }
            }
            h3 {
                a(href = criterion2022.path) {
                    +"2022 Criterion Challenge Recap"
                }
            }
            h3 {
                a(href = "https://generationq.neocities.org/") { +"Generation Q" }
                +": Star Trek TNG Podcast"
            }
        }
    }
}
val feeds = htmlPage("feeds.html") {
    standardHead()
    body {
        pageTitle("Feeds")
        content {
            ul {
                li { a(href = "rss/" + Paths.RSS_PATH) { +"All Updates" } }
                //   li { a(href = "rss/"+Paths.SITE_UPDATES_RSS_PATH) { +"Site Updates" } }
            }

        }
        footer { myFooter() }
    }
}

fun Recipe.recipePage() = htmlPage(name.replace(" ", "") + ".html") {
    article(name) {
        htmlSection {
            this.apply(html())
        }
        footer(myFooter)
    }
}
