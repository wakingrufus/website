package com.github.wakingrufus.website.lib.dashboard

import com.github.wakingrufus.website.WebsiteDsl
import kotlinx.html.P

@WebsiteDsl
data class SubPanel(
    val name: String,
    val link: String? = null
) {
    var entries: MutableList<TopicEntry> = mutableListOf()
    var content: P.() -> Unit = {}
    var expandable: Boolean = false

    @WebsiteDsl
    fun expandable() {
        expandable = true
    }

    @WebsiteDsl
    fun description(content: P.() -> Unit) {
        this.content = content
    }

    @WebsiteDsl
    fun entry(name: String, url: String) {
        entries.add(TopicEntry(name, listOf(name to url)))
    }

    @WebsiteDsl
    fun entry(name: String, entry: EntryDsl.() -> Unit) {
        entries.add(EntryDsl(name).apply(entry).invoke())
    }
}