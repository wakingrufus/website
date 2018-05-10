package com.github.wakingrufus.website.lib.slides

import kotlinx.html.DIV
import kotlinx.html.HTML

class Slide(val title: String,
            val template: (mainCssPath: String,
                           title: String,
                           slideNumber: Int,
                           totalSlides: Int,
                           content: DIV.() -> Unit) -> HTML.() -> Unit,
            val content: DIV.() -> Unit)


