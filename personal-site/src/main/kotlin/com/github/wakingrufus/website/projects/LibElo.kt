package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.p

val libElo = htmlPage("lib-elo.html") {
    article("lib-elo") {
        htmlSection {
            p {
                +"lib-elo is kotlin library which implements an Elo game rating system. See more at "
                a(href = "https://github.com/wakingrufus/lib-elo") { +"GitHub" }
                +"."
            }
        }
        footer(myFooter)
    }
}