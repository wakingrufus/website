package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.sideNav
import kotlinx.html.*

val tourney: HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Tourney")
        sideNav()
        content {
            div {
                p {
                    +"Tourney is a desktop application which allows you to run an Elo gaming league. See more at "
                    a(href = "https://github.com/wakingrufus/elo-league-jfx") { +"GitHub" }
                    +"."
                }
            }
        }
    }
}