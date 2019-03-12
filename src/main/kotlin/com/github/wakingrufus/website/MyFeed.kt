package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.rss.*
import com.rometools.rome.feed.synd.SyndFeed
import kotlinx.html.body
import kotlinx.html.div
import java.time.*
import java.util.*

val siteUpdates = rssCategory {
    name = "site-updates"
    taxonomyUri = Paths.SITE_UPDATES_RSS_PATH
}

val article = rssCategory {
    name = "article"
    taxonomyUri = Paths.RSS_PATH
}

val allEntries = entries {
    entry {
        title = "RSS feed is live!"
        author = "wakingrufus"
        categories = listOf(siteUpdates)
        link = "https://wakingrufus.neocities.org/rss/" + Paths.RSS_PATH
        publishedDate = Date.from(ZonedDateTime.of(LocalDate.of(2018, Month.JUNE, 20),
                LocalTime.of(8, 0, 0),
                ZoneOffset.ofHours(-5)).toInstant())
        content("I added RSS feeds")
    }
    entry {
        title = "Antipatterns: Refactoring as a Separate Ticket"
        author = "wakingrufus"
        categories = listOf(article)
        link = "https://wakingrufus.neocities.org/" + Paths.ANTIPATTERNS_PATH + "#refactoring"
        publishedDate = Date.from(ZonedDateTime.of(LocalDate.of(2018, Month.JUNE, 22),
                LocalTime.of(8, 0, 0),
                ZoneOffset.ofHours(-5)).toInstant())
        content {
            body {
                div {
                    this.apply(com.github.wakingrufus.website.refactoring())
                }
            }
        }
    }
    entry {
        title = "Travel Guide"
        author = "wakingrufus"
        categories = listOf(siteUpdates)
        link = "https://wakingrufus.neocities.org/" + Paths.TRAVEL_PATH
        publishedDate = Date.from(ZonedDateTime.of(
                LocalDate.of(2018, Month.NOVEMBER, 1),
                LocalTime.of(8, 0, 0),
                ZoneOffset.ofHours(-5)
        ).toInstant())
        content("I have a new page where I recommend places to visit while travelling based on my own travels.")
    }
    entry {
        title = "Static Web with Kotlin DSLs"
        author = "wakingrufus"
        categories = listOf(siteUpdates)
        link = "https://wakingrufus.neocities.org/" + Paths.STATIC_WEB_ARTICLE_PATH
        publishedDate = Date.from(ZonedDateTime.of(
                LocalDate.of(2018, Month.DECEMBER, 14),
                LocalTime.of(17, 0, 0),
                ZoneOffset.ofHours(-6)
        ).toInstant())
        content {
            body {
                div {
                    introToStaticWeb()(this)
                    lambdaAsFinalParameter()(this)
                    lambdaWithReceiver()(this)
                    dslMarker()(this)
                    htmlDsl()(this)
                    codeReuse()(this)
                    dslExtension()(this)
                    usage()(this)
                    conclusion()(this)
                    additionalResources()(this)
                }
            }
        }

    }
    entry {
        title = "Recipes"
        author = "wakingrufus"
        categories = listOf(siteUpdates)
        link = "https://wakingrufus.neocities.org/" + Paths.RECIPE_PAGE
        publishedDate = Date.from(ZonedDateTime.of(
                LocalDate.of(2019, Month.JANUARY, 29),
                LocalTime.of(18, 0, 0),
                ZoneOffset.ofHours(-6)
        ).toInstant())
        content("I have a new page where I collect recipes using my new recipe DSL")
    }
}

fun siteUpdateFeed(): SyndFeed = rss {
    feedType = "rss_2.0"
    title = "wakingrufus site updates"
    description = "site updates for wakingrufus.neocities.org"
    link = "https://wakingrufus.neocities.org"
    entries = allEntries.entries
            .filter { it.categories.contains(siteUpdates) }
            .sortedBy { it.publishedDate }
            .map {
                it.apply {
                    source = this@rss
                }
            }
}

fun feed(): SyndFeed = rss {
    feedType = "rss_2.0"
    title = "wakingrufus"
    description = "site updates for wakingrufus.neocities.org"
    link = "https://wakingrufus.neocities.org"
    entries = allEntries.entries
            .sortedBy { it.publishedDate }
}
