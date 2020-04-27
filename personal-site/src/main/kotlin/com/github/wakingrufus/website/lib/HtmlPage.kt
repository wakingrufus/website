package com.github.wakingrufus.website.lib

import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.article.ArticleBuilder
import kotlinx.html.DIV
import kotlinx.html.HTML
import kotlinx.html.article
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import java.io.Writer

@WebsiteDsl
class HtmlPage(val path: String) {
    private var builder: HTML.() -> Unit = {}
    private var article: ArticleBuilder? = null

    @Deprecated("use article or other typed builder")
    fun builder(block: HTML.() -> Unit) {
        builder = block
    }

    fun getTitle(): String {
        return article?.title ?: ""
    }

    fun getContent(): DIV.() -> Unit = {
        this@HtmlPage.article?.getContent()?.also{
            article(block = it)
        }
    }

    fun writeHtmlPage(writer: Writer) {
        writer.use {
            it.appendHTML().html(block = {
                builder(this)
                article?.invoke(this)
            })
        }
    }

    fun article(title: String, article: ArticleBuilder.() -> Unit) {
        this.article = ArticleBuilder(title).apply(article)
    }
}

fun writeHtmlPage(writer: Writer, builder: HTML.() -> Unit) {
    writer.use {
        it.appendHTML().html(block = builder)
    }
}
