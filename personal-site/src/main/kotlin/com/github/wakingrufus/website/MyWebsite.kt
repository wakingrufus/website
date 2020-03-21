package com.github.wakingrufus.website

import com.github.wakingrufus.website.articles.antipatterns
import com.github.wakingrufus.website.articles.failAgile
import com.github.wakingrufus.website.articles.staticWeb
import com.github.wakingrufus.recipe.Recipe
import com.github.wakingrufus.website.articles.adhocPolymorphism
import com.github.wakingrufus.website.cooking.allRecipes
import com.github.wakingrufus.website.lib.*
import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.cooking.html
import com.github.wakingrufus.website.projects.*
import com.github.wakingrufus.website.slideshows.functionalKotlinSlideshow
import com.github.wakingrufus.website.slideshows.kotlin2019Slideshow
import com.github.wakingrufus.website.slideshows.staticWebSlideshow
import com.github.wakingrufus.website.slideshows.whyDoesAgileFail
import kotlinx.css.Display
import kotlinx.css.em
import kotlinx.html.*
import java.io.File

object Paths {
    val INDEX_PATH = "index.html"
    val MASTODON_JFX_PATH = "mastodon-jfx.html"
    val TOURNEY_PATH = "tourney.html"
    val FILEDB_PATH = "filedb.html"
    val WEBSITE_PATH = "website.html"
    val CSS_PATH = "styles.css"
    val SLIDESHOW_CSS_PATH = "slideshow_styles.css"
    val FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME = "functional-kotlin-slideshow"
    val KOTLIN_2019_SLIDESHOW_BASE_NAME = "kotlin-2019"
    val RSS_PATH = "rss.xml"
    val SITE_UPDATES_RSS_PATH = "site-updates.xml"
    val FEEDS_PAGE = "feeds.html"
    val STATIC_WEB_SLIDESHOW_BASE_NAME = "static-web"
    val FAIL_AGILE_SLIDESHOW_BASE_NAME = "fail-agile"
    val TRAVEL_PATH = "travel.html"
    val FAIL_AGILE_BLOG = "fail-agile.html"
}

val myFooter: FOOTER.() -> Unit = {
    div {
        style = css {
            display = Display.block
        }
        a(href = Paths.INDEX_PATH) { +"Home" }
        +" - "
        a(href = Paths.FEEDS_PAGE) { +"Feeds" }
    }
}

class MyWebsite {

    fun build(baseDir: File): Website {
        return website(baseDir) {
            cssFile(path = Paths.CSS_PATH, cssString = MyStyles().styles())
            htmlPageBuilder(path = Paths.INDEX_PATH, builder = mainPage())
            htmlPageBuilder(path = Paths.MASTODON_JFX_PATH, builder = mastodonJfx)
            page(filedb)
            htmlPageBuilder(path = Paths.TOURNEY_PATH, builder = tourney)
            page(libElo)
            htmlPageBuilder(path = Paths.WEBSITE_PATH, builder = personalSite)
            htmlPageBuilder(path = Paths.FEEDS_PAGE, builder = feeds())
            page(antipatterns)
            page(staticWeb)
            htmlPageBuilder(path = Paths.TRAVEL_PATH, builder = travel())
            page(adhocPolymorphism)
            page(failAgile)

            allRecipes.forEach {
                page(it.invoke().recipePage())
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

fun mainPage(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
        link(href = "https://wakingrufus.neocities.org/rss/" + Paths.RSS_PATH, type = "application/rss+xml", rel = "alternate") {
            title = "RSS"
        }
    }
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
                    a(href = Paths.TRAVEL_PATH) { +"Travel Guide" }
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
                li { a(href = Paths.WEBSITE_PATH) { +"This Website" } }
                li { a(href = Paths.MASTODON_JFX_PATH) { +"mastodon-jfx" } }
                li { a(href = Paths.TOURNEY_PATH) { +"Tourney" } }
                li { a(href = libElo.path) { +"lib-elo" } }
                li { a(href = filedb.path) { +"filedb" } }
            }
        }
        panel("Software Development") {
//            h3 { +"Using ad-hoc Polymorphism to test functional Kotlin" }
//            p { a(href = adhocPolymorphism.path) { +"Article" } }
            h3 { +"Static Web development and Kotlin DSLs" }
            span { a(href = staticWeb.path) { +"Article" } }
            span { +" - " }
            span { a(href = Paths.STATIC_WEB_SLIDESHOW_BASE_NAME + "/0.html") { +"Slides" } }
            span { +" - " }
            span { a(href = "https://archive.org/details/march2019-static_web_development_kotlin_dsls") { +"Video" } }
            h3 { +"Functional Kotlin" }
            p { a(href = Paths.FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME + "/0.html") { +"Slides" } }
            h3 { +"Software Development Antipatterns" }
            p { a(href = antipatterns.path + "#refactoring") { +"Refactoring" } }
//            h3 { +"Fail Agile" }
//            p { a(href = Paths.FAIL_AGILE_BLOG) { +"Article" } }
            h3 { +"Kotlin in 2019" }
            p { a(href = Paths.KOTLIN_2019_SLIDESHOW_BASE_NAME + "/0.html") { +"Slides" } }
        }
        panel("Recipes") {
            a { id = "recipes" }
            ul {
                allRecipes.forEach { recipe ->
                    li { a(href = recipe.name.replace(" ", "") + ".html") { +recipe.name } }
                }
            }
        }
    }
}

fun feeds(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
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