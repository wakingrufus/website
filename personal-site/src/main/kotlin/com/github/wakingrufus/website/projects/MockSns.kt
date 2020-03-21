package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

val mockSns: HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("mock-sns")
        content {
            div {
                p {
                    +"mock-sns is kotlin library which provides a test fixture for Amazon SNS. See more at "
                    a(href = "https://github.com/wakingrufus/mock-sns") { +"GitHub" }
                    +"."
                }
            }
        }
        footer { myFooter() }
    }
}