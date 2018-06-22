package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.rss.content
import com.github.wakingrufus.website.lib.rss.entries
import com.github.wakingrufus.website.lib.rss.entry
import com.github.wakingrufus.website.lib.rss.rss
import com.rometools.rome.feed.synd.SyndCategory
import com.rometools.rome.feed.synd.SyndCategoryImpl
import com.rometools.rome.feed.synd.SyndFeed
import kotlinx.html.body
import kotlinx.html.div
import java.time.*
import java.util.*

val siteUpdates: SyndCategory = SyndCategoryImpl().apply {
    name = "site-updates"
    taxonomyUri = Paths.SITE_UPDATES_RSS_PATH
}
val article: SyndCategory = SyndCategoryImpl().apply {
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
