package com.github.wakingrufus.rss

@RssDsl
class Entries {
    var entries: List<Entry> = ArrayList()
    fun entry(block: Entry.() -> Unit) {
        entries += Entry().apply(block)
    }
}
