package com.github.wakingrufus.website

import com.github.wakingrufus.recipe.Recipe
import com.github.wakingrufus.website.articles.*
import com.github.wakingrufus.website.cooking.RecipeIndex
import com.github.wakingrufus.website.lib.*
import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.cooking.html
import com.github.wakingrufus.website.music.music2020
import com.github.wakingrufus.website.music.music2021
import com.github.wakingrufus.website.projects.*
import com.github.wakingrufus.website.slideshows.functionalKotlinSlideshow
import com.github.wakingrufus.website.slideshows.kotlin2019Slideshow
import com.github.wakingrufus.website.slideshows.staticWebSlideshow
import com.github.wakingrufus.website.slideshows.whyDoesAgileFail
import kotlinx.css.Display
import kotlinx.css.display
import kotlinx.css.em
import kotlinx.css.paddingLeft
import kotlinx.html.*
import java.io.File

object Paths {
    val INDEX_PATH = "index.html"
    val FILEDB_PATH = "filedb.html"
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
            page(mastodonJfx)
            page(filedb)
            page(tourney)
            page(libElo)
            page(personalSite)
            page(feeds)
            page(antipatterns)
            page(staticWeb)
            page(travel)
            page(adhocPolymorphism)
            page(failAgile)
            page(coHost)
            page(music2020)
            page(music2021)
            page(criterion2021)
            page(jamm)

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
    title("wakingrufus's home page")
    description("Personal home page for wakingrufus, a software developer working on open source projects in kotlin.")
    body {
        pageTitle("wakingrufus")
        content {
            div {
                style = css {
                    paddingLeft = 1.em
                }
                p { +"I am a software engineer working on open source projects in kotlin." }
                h3 { +"You can also find me at: " }
                ul {
                    li {
                        a(href = "http://www.github.com/wakingrufus") {
                            +"GitHub"
                        }
                    }
                    li {
                        a(href = "https://bigshoulders.city/@wakingrufus") {
                            rel = "me"
                            +"Fediverse"
                        }
                    }
                    li {
                        a(href = "https://twitter.com/wakingrufus") {
                            +"Twitter"
                        }
                    }
                }
//                p {
//                    +"My "
//                    a(href = travel.path) { +"Travel Guide" }
//                }

            }
            myDashboard()
        }
        footer { myFooter() }
    }
}

val myDashboard: DIV.() -> Unit = {
    dashboard {
        panel("Open Source Projects") {
            h3 {
                a(href = jamm.path) { +"JaMM" }
                +": A desktop media player / music library"
            }
            ul {
                li { a(href = personalSite.path) { +"This Website" } }
                li { a(href = mastodonJfx.path) { +"mastodon-jfx" } }
                li { a(href = tourney.path) { +"Tourney" } }
                li { a(href = libElo.path) { +"lib-elo" } }
                li { a(href = filedb.path) { +"filedb" } }
            }
            p {
                a(href = "https://packagecloud.io/wakingrufus/public") {
                    +"My Public Maven and Debian Repo"
                }
            }
        }
        topicPanel("Writing / Speaking") {
            topic("Social Media / Fediverse"){
                entry("Concerns about Cohost", coHost.path)
            }
            topic("Developer Productivity / Platform Engineering") {
                entry("Developer Productivity Showdown Oct 2022", "https://www.youtube.com/watch?v=o6Epqyh_C3w")
            }
            topic("Developing Gradle Plugins") {
                entry("Video (Coming Soon)", "")
                entry("Slides", "https://wakingrufus.github.io/developing-gradle-plugins/")
            }
            topic(failAgile.getTitle()) {
                entry("Article", failAgile.path)
            }
            topic("Static Web development and Kotlin DSLs") {
                entry("Article", staticWeb.path)
                entry("Slides", Paths.STATIC_WEB_SLIDESHOW_BASE_NAME + "/0.html")
                entry("Presentation Video", "https://archive.org/details/march2019-static_web_development_kotlin_dsls")
                entry("Tutorial Video", "https://archive.org/details/kotlin-web-dsl")
            }
            topic(adhocPolymorphism.getTitle()) {
                entry("Article", adhocPolymorphism.path)
            }
            topic("Functional Kotlin") {
                entry("Slides", Paths.FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME + "/0.html")
            }
            topic("Software Development Antipatterns") {
                entry("Refactoring as a Separate Ticket", antipatterns.path + "#refactoring")
            }
            topic("Kotlin in 2019") {
                entry("Video (CKUG)", "https://youtu.be/nLKJJASfRh4")
                entry("Slides", Paths.KOTLIN_2019_SLIDESHOW_BASE_NAME + "/0.html")
            }
        }
    }
    dashboard {
        h2 {
            +"Other Interests"
        }
        panel("Music") {
            h3 {
                a(href = music2020.path) {
                    +"Best Music of 2020"
                }
            }
            h3 {
                a(href = music2021.path) {
                    +"Best Music of 2021"
                }
            }
        }
        panel("TV / Film") {
            h3 {
                a(href = criterion2021.path) {
                    +"2021 Criterion Challenge Recap"
                }
            }
            h3 {
                a(href = "https://generationq.neocities.org/") { +"Generation Q" }
                +": Star Trek TNG Podcast"
            }
        }
        panel("Cooking") {
            a { id = "recipes" }
            ul {
                RecipeIndex.recipes.forEach { recipe ->
                    li { a(href = recipe.name.replace(" ", "") + ".html") { +recipe.name } }
                }
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
