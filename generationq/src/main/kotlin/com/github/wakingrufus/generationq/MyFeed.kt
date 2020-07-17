package com.github.wakingrufus.generationq

import com.github.wakingrufus.rss.Entries
import com.github.wakingrufus.rss.entries
import com.github.wakingrufus.rss.rss
import com.github.wakingrufus.rss.rssCategory
import com.rometools.rome.feed.synd.SyndFeed
import kotlinx.html.body
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
}

fun Entries.article(articlePage: HtmlPage, date: Instant) {
    entry {
        title = articlePage.getTitle()
        author = "wakingrufus"
        categories = listOf(article)
        link = "https://wakingrufus.neocities.org/" + articlePage.path
        publishedDate = Date.from(date)
        content {
            body {
                this.apply(articlePage.getContent())
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
