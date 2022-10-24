package com.github.wakingrufus.website.projects

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.p

val jamm = htmlPage("jamm.html") {
    article("JaMM") {
        htmlSection {
            p {
                +"JaMM is a queue-based music library and player for Linux/Mac/Windows desktop. "
                +"JaMM is written in JavaFx, and distributed with its own runtime using jlink and jpackage. "
                +"See more at "
                a(href = "https://github.com/wakingrufus/JaMM") { +"GitHub" }
                +"."
            }
        }
        footer(myFooter)
    }
}
