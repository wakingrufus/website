package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.sideNav
import kotlinx.html.*

val filedb: HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Filedb")
        sideNav()
        content {
            div {
                p {
                    +"filedb is kotlin library which allow you to use a file sync service "
                    +"(such as syncthing) as a persistance backend for an application. See more at "
                    a(href = "https://github.com/wakingrufus/filedb") { +"GitHub" }
                    +"."
                }
            }
        }
    }
}