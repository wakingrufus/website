package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

val mastodonJfx: HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("mastodon-jfx")
        content {
            div {
                p {
                    +"mastodon-jfx is kotlin client for mastodon. See more at "
                    a(href = "https://github.com/wakingrufus/mastodon-jfx") { +"GitHub" }
                    +"."
                }
            }
        }
        footer { myFooter() }
    }
}