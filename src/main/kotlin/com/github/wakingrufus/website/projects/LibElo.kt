package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.sideNav
import kotlinx.html.*

val libElo: HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("lib-elo")
        sideNav()
        content {
            div {
                p {
                    +"lib-elo is kotlin library which implements an Elo game rating system. See more at "
                    a(href = "https://github.com/wakingrufus/lib-elo") { +"GitHub" }
                    +"."
                }
            }
        }
    }
}