package com.github.wakingrufus.website

import com.github.wakingrufus.website.cooking.allRecipes
import com.github.wakingrufus.website.cooking.chickPeaCurry
import com.github.wakingrufus.website.cooking.recipeIndex
import com.github.wakingrufus.website.lib.*
import com.github.wakingrufus.website.lib.cooking.Recipe
import com.github.wakingrufus.website.lib.cooking.html
import com.github.wakingrufus.website.slideshows.functionalKotlinSlideshow
import com.github.wakingrufus.website.slideshows.staticWebSlideshow
import kotlinx.html.*
import java.io.File

object Paths {
    val INDEX_PATH = "index.html"
    val MASTODON_JFX_PATH = "mastodon-jfx.html"
    val ANTIPATTERNS_PATH = "antipatterns.html"
    val STATIC_WEB_ARTICLE_PATH = "staticwebarticle.html"
    val CSS_PATH = "styles.css"
    val SLIDESHOW_CSS_PATH = "slideshow_styles.css"
    val FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME = "functional-kotlin-slideshow"
    val RSS_PATH = "rss.xml"
    val SITE_UPDATES_RSS_PATH = "site-updates.xml"
    val FEEDS_PAGE = "feeds.html"
    val RECIPE_PAGE = "recipes.html"
    val STATIC_WEB_SLIDESHOW_BASE_NAME = "static-web"
    val TRAVEL_PATH = "travel.html"
    val DEVELOPMENT_PATH = "development.html"
}

fun BODY.sideNav() {
    apply {
        sideNavBar {
            li { a(href = Paths.INDEX_PATH) { +"Home" } }
            li { a(href = Paths.MASTODON_JFX_PATH) { +"mastodon-jfx" } }
            li { a(href = Paths.DEVELOPMENT_PATH) { +"Development" } }
            li { a(href = Paths.TRAVEL_PATH) { +"Travel Guide" } }
            li { a(href = Paths.RECIPE_PAGE) { +"Recipes" } }
            li { a(href = Paths.FEEDS_PAGE) { +"Feeds" } }
        }
    }
}

class MyWebsite {

    fun build(baseDir: File): Website {
        return website(baseDir) {
            cssFile(path = Paths.CSS_PATH, cssString = MyStyles().styles())
            htmlPage(path = Paths.INDEX_PATH, builder = mainPage())
            htmlPage(path = Paths.MASTODON_JFX_PATH, builder = mastodonJfx())
            htmlPage(path = Paths.FEEDS_PAGE, builder = feeds())
            htmlPage(path = Paths.ANTIPATTERNS_PATH, builder = antipatterns())
            htmlPage(path = Paths.STATIC_WEB_ARTICLE_PATH, builder = staticweb())
            htmlPage(path = Paths.TRAVEL_PATH, builder = travel())
            htmlPage(path = Paths.RECIPE_PAGE, builder = recipeIndex())
            htmlPage(path = Paths.DEVELOPMENT_PATH, builder = development())
            allRecipes.forEach {
                htmlPage(it.name.replace(" ", "") + ".html", it.invoke().recipePage())
            }
            rssFeed(path = Paths.RSS_PATH, feedContents = feed())
            //    rssFeed(path = Paths.SITE_UPDATES_RSS_PATH, feedContents = siteUpdateFeed())
            apply(functionalKotlinSlideshow())
            apply(staticWebSlideshow())
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
                        // TODO: add rel="me"
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

fun development(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("mastodon-jfx")
        sideNav()
        content {
            p { +"This page contains a collection of various articles and presentations about software development I have authored." }
            h3 { +"Static Web development and Kotlin DSLs" }
            p { a(href = Paths.STATIC_WEB_ARTICLE_PATH) { +"Article" } }
            p { a(href = Paths.STATIC_WEB_SLIDESHOW_BASE_NAME + "/0.html") { +"Slides" } }
            h3 { +"Functional Kotlin" }
            p { a(href = Paths.FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME + "/0.html") { +"Slides" } }
            h3 { +"Software Development Antipatterns" }
            p { a(href = Paths.ANTIPATTERNS_PATH + "#refactoring") { +"Refactoring" } }
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

fun feeds(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Feeds")
        sideNav()
        content {
            ul {
                li { a(href = "rss/" + Paths.RSS_PATH) { +"All Updates" } }
                //   li { a(href = "rss/"+Paths.SITE_UPDATES_RSS_PATH) { +"Site Updates" } }
            }

        }
    }
}

fun Recipe.recipePage(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle(this@recipePage.name)
        sideNav()
        content {
            this@body.apply(this@recipePage.html())

        }
    }
}

fun recipes(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Recipes")
        sideNav()
        content {
            this@body.apply(chickPeaCurry().html())

        }
    }
}