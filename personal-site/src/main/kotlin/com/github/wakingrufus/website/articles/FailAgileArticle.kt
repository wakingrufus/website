package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter

val failAgile = htmlPage("fail-agile.html") {
    article("Why Does Agile Fail?") {
        markdownSection("fail-agile.md")
        footer(myFooter)
    }
}
