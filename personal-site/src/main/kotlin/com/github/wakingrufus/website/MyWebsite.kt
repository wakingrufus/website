package com.github.wakingrufus.website

import com.github.wakingrufus.recipe.Recipe
import com.github.wakingrufus.website.articles.*
import com.github.wakingrufus.website.cooking.RecipeIndex
import com.github.wakingrufus.website.lib.*
import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.cooking.html
import com.github.wakingrufus.website.projects.*
import com.github.wakingrufus.website.slideshows.functionalKotlinSlideshow
import com.github.wakingrufus.website.slideshows.kotlin2019Slideshow
import com.github.wakingrufus.website.slideshows.staticWebSlideshow
import com.github.wakingrufus.website.slideshows.whyDoesAgileFail
import kotlinx.css.*
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
                            rel = "me"
                            +"mastodon.technology"
                        }
                    }
                    li {
                        a(href = "https://twitter.com/wakingrufus") {
                            +"Twitter"
                        }
                    }
                }
                p {
                    +"My "
                    a(href = travel.path) { +"Travel Guide" }
                }
                p {
                    +"I am also one of the hosts of "
                    a(href = "https://generationq.neocities.org/") { +"Generation Q" }
                    +", a Star Trek TNG Podcast"
                }
            }
            myDashboard()
        }
        footer { myFooter() }
    }
}

val myDashboard: DIV.() -> Unit = {
    dashboard {
        panel("Projects") {
            ul {
                li { a(href = personalSite.path) { +"This Website" } }
                li { a(href = mastodonJfx.path) { +"mastodon-jfx" } }
                li { a(href = tourney.path) { +"Tourney" } }
                li { a(href = libElo.path) { +"lib-elo" } }
                li { a(href = filedb.path) { +"filedb" } }
                li { a(href = jamm.path) { +"Jamm" } }
            }
            p {
                a(href = "https://packagecloud.io/wakingrufus/public/maven2") {
                    +"My Public Maven Repo"
                }
            }
        }
        panel("Software Development") {
            h3 { a(href = failAgile.path) { +failAgile.getTitle() } }
            h3 { a(href = adhocPolymorphism.path) { +adhocPolymorphism.getTitle() } }
            div {
                style = css {
                    backgroundColor = MyStyles.BACKGROUND_COLOR
                    paddingBottom = 1.em
                }
                h3 { +"Static Web development and Kotlin DSLs" }
                span {
                    a(href = staticWeb.path) { +"Article" }
                    +" - "
                    a(href = Paths.STATIC_WEB_SLIDESHOW_BASE_NAME + "/0.html") { +"Slides" }
                    +" - "
                    a(href = "https://archive.org/details/march2019-static_web_development_kotlin_dsls") { +"Presentation Video" }
                    +" - "
                    a(href = "https://archive.org/details/kotlin-web-dsl") { +"Tutorial Video" }
                }
            }
            h3 {
                a(
                    href = Paths.FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME + "/0.html"
                ) { +"Functional Kotlin Presentation Slides" }
            }
            div {
                style = css {
                    backgroundColor = MyStyles.BACKGROUND_COLOR
                    paddingBottom = 1.em
                }
                h3 { +"Software Development Antipatterns" }
                span { a(href = antipatterns.path + "#refactoring") { +"Refactoring as a Separate Ticket" } }
            }
            h3 { a(href = Paths.KOTLIN_2019_SLIDESHOW_BASE_NAME + "/0.html") { +"Kotlin in 2019 Presentation Slides" } }
        }
        panel("Music") {
            a(href = music2021.path) {
                +"Best Music of 2021"
            }
        }
        panel("Film") {
            a(href = criterion2021.path) {
                +"2021 Criterion Challenge Recap"
            }
        }
        panel("Recipes") {
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
