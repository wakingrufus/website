package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.sideNav
import kotlinx.html.*

val personalSite: HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("My Personal Site")
        sideNav()
        content {
            div {
                p {
                    +"This website is written in pure kotlin using the HTML and CSS DSL. See more at: "
                    a(href = "https://github.com/wakingrufus/website") { +"GitHub" }
                    +"."
                }
            }
        }
    }
}