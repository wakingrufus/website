package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter

val failAgile = htmlPage(Paths.FAIL_AGILE_BLOG) {
    article("Why Does Agile Fail?") {
        markdownSection("fail-agile.md")
        footer(myFooter)
    }
}
