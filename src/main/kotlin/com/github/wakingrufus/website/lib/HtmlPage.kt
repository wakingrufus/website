package com.github.wakingrufus.website.lib

import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import java.io.Writer

class HtmlPage(val path: String, val builder: HTML.() -> Unit) {
    fun writeHtmlPage(writer: Writer) {
        writer.use {
            it.appendHTML().html(block = builder)
        }
    }
}

fun writeHtmlPage(writer: Writer, builder: HTML.() -> Unit) {
    writer.use {
        it.appendHTML().html(block = builder)
    }
}
