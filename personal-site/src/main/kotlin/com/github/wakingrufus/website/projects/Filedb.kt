package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.p

val filedb = htmlPage(Paths.FILEDB_PATH) {
    article("Filedb") {
        htmlSection {
            div {
                p {
                    +"filedb is kotlin library which allow you to use a file sync service "
                    +"(such as syncthing) as a persistance backend for an application. See more at "
                    a(href = "https://github.com/wakingrufus/filedb") { +"GitHub" }
                    +"."
                }
            }
        }
        footer(myFooter)
    }
}