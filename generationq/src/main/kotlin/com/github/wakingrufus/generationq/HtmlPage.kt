package com.github.wakingrufus.generationq

import com.github.wakingrufus.website.WebsiteDsl
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.Writer

@WebsiteDsl
class HtmlPage(val path: String) {
    private var title: String? = null
    private var description: String? = null
    private var body: BODY.() -> Unit = { }
    private var head: HEAD.() -> Unit = {}

    fun getTitle(): String {
        return title ?: ""
    }

    fun getDescription(): String {
        return description ?: title ?: ""
    }

    fun title(title: String) {
        this.title = title
    }

    fun description(description: String) {
        this.description = description
    }

    fun getContent(): BODY.() -> Unit = {
        this.apply(body)
    }

    fun head(block: HEAD.() -> Unit) {
        head = block
    }

    fun writeHtmlPage(writer: Writer) {
        writer.use {
            it.write("<!DOCTYPE html>")
            it.appendHTML().html {
                lang = "en"
                head(head)
                body {
                    apply(body)
                }
            }
        }
    }

    fun body(block: BODY.() -> Unit) {
        body = block
    }


}

fun writeHtmlPage(writer: Writer, builder: HTML.() -> Unit) {
    writer.use {
        it.write("<!DOCTYPE html>")
        it.appendHTML().html(block = builder)
    }
}
