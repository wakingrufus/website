package com.github.wakingrufus.website.lib

import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.article.ARTICLE
import kotlinx.html.DIV
import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import java.io.Writer

@WebsiteDsl
class HtmlPage(val path: String) {
    private var builder: HTML.() -> Unit = {}
    private var article: ARTICLE? = null

    @Deprecated("use article or other typed builder")
    fun builder(block: HTML.() -> Unit) {
        builder = block
    }

    fun getTitle(): String {
        return article?.title ?: ""
    }

    fun getContent(): DIV.() -> Unit {
        return article?.getContent() ?: {}
    }

    fun writeHtmlPage(writer: Writer) {
        writer.use {
            it.appendHTML().html(block = {
                builder(this)
                article?.invoke(this)
            })
        }
    }

    fun article(title: String, article: ARTICLE.() -> Unit) {
        this.article = ARTICLE(title).apply(article)
    }
}

fun writeHtmlPage(writer: Writer, builder: HTML.() -> Unit) {
    writer.use {
        it.appendHTML().html(block = builder)
    }
}
