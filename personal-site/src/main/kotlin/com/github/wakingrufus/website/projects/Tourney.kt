package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.p

val tourney = htmlPage("tourney.html") {
    article("Tourney") {
        htmlSection {
            p {
                +"Tourney is a desktop application which allows you to run an Elo gaming league. See more at "
                a(href = "https://github.com/wakingrufus/elo-league-jfx") { +"GitHub" }
                +"."
            }
        }
        footer(myFooter)
    }
}
