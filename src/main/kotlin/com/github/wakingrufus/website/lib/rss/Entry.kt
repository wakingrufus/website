package com.github.wakingrufus.website.lib.rss

import com.rometools.rome.feed.synd.SyndContentImpl
import com.rometools.rome.feed.synd.SyndEntryImpl
import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import java.io.StringWriter

@RssDsl
class Entry : SyndEntryImpl() {

    fun content(content: String) {
        description = SyndContentImpl().apply {
            value = content
            type = "text/string"
        }
    }

    fun content(block: HTML.() -> Unit) {
        description = SyndContentImpl().apply {
            value = StringWriter().appendHTML(prettyPrint = false).html {
                apply(block)
            }.toString()
            type = "text/html"
        }
    }
}
