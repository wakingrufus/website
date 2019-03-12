package com.github.wakingrufus.website.lib.rss

import com.rometools.rome.feed.synd.*
import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML

@DslMarker
annotation class RssDsl

@RssDsl
class RssFeed : SyndFeedImpl()

fun rss(block: RssFeed.() -> Unit): SyndFeed {
    return RssFeed().apply(block)
}

fun entries(block: Entries.() -> Unit): Entries {
    return Entries().apply(block)
}

fun SyndFeed.entry(block: Entry.() -> Unit) {
    this.entries.add(Entry().apply(block))
}
//
//fun Entry.content(content: String) {
//    this.description = SyndContentImpl().apply {
//        value = content
//        type = "text/string"
//    }
//}
//
//fun Entry.content(block: HTML.() -> Unit) {
//    this.description = SyndContentImpl().apply {
//        value = java.io.StringWriter().appendHTML(prettyPrint = false).html {
//            apply(block)
//        }.toString()
//        type = "text/html"
//    }
//}

fun rssCategory(block: SyndCategory.() -> Unit): SyndCategory {
    return SyndCategoryImpl().apply(block)
}