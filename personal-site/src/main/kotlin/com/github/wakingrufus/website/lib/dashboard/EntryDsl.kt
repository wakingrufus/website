package com.github.wakingrufus.website.lib.dashboard

import com.github.wakingrufus.website.WebsiteDsl

@WebsiteDsl
class EntryDsl(val name: String) {
    private val links = mutableListOf<Pair<String, String>>()

    @WebsiteDsl
    fun link(label: String, url: String) {
        links.add(label to url)
    }

    internal operator fun invoke(): TopicEntry = TopicEntry(name, links)
}