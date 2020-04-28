package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.p

val mastodonJfx = htmlPage("mastodon-jfx.html") {
    article("mastodon-jfx") {
        htmlSection {
            p {
                +"mastodon-jfx is kotlin client for mastodon. See more at "
                a(href = "https://github.com/wakingrufus/mastodon-jfx") { +"GitHub" }
                +"."
            }
        }
        footer(myFooter)
    }
}