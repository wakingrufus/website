package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.p

val personalSite = htmlPage("website.html") {
    article("My Personal Site") {
        htmlSection {
            p {
                +"This website is written in pure kotlin using the HTML and CSS DSL. See more at: "
                a(href = "https://github.com/wakingrufus/website") { +"GitHub" }
                +"."
            }
        }
        footer(myFooter)
    }
}
