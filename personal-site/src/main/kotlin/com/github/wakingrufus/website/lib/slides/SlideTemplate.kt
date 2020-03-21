package com.github.wakingrufus.website.lib.slides

import kotlinx.html.DIV
import kotlinx.html.HTML

interface SlideTemplate{
    fun build(mainCssPath: String,
              title: String,
              slideNumber: Int,
              totalSlides: Int,
              content: DIV.() -> Unit): HTML.() -> Unit
}