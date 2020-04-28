package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.p

val mockSns = htmlPage("mock-sns.html") {
    article("mock-sns") {
        htmlSection {
            p {
                +"mock-sns is kotlin library which provides a test fixture for Amazon SNS. See more at "
                a(href = "https://github.com/wakingrufus/mock-sns") { +"GitHub" }
                +"."
            }
        }
        footer(myFooter)
    }
}