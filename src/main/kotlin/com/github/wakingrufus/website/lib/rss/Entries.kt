package com.github.wakingrufus.website.lib.rss

@RssDsl
class Entries {
    var entries: List<Entry> = ArrayList()
}

fun Entries.entry(block: Entry.() -> Unit) {
    entries += Entry().apply(block)
}