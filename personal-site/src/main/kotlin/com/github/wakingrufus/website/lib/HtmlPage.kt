package com.github.wakingrufus.website.lib

import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.article.ArticleBuilder
import com.github.wakingrufus.website.standardHead
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.Writer

@WebsiteDsl
class HtmlPage(val path: String) {
    private var title: String? = null
    private var body: BODY.() -> Unit = { }
    private var head: HEAD.() -> Unit = {}

    fun getTitle(): String {
        return title ?: ""
    }

    fun title(title: String){
        this.title = title
    }

    fun getContent(): BODY.() -> Unit = {
      this.apply(body)
    }

    fun head(block: HEAD.() -> Unit){
        head = block
    }

    fun writeHtmlPage(writer: Writer) {
        writer.use {
            it.write("<!DOCTYPE html>")
            it.appendHTML().html{
                head(head)
                body {
                    apply(body)
                }
            }
        }
    }

    fun body(block: BODY.() -> Unit){
        body = block
    }


}

fun writeHtmlPage(writer: Writer, builder: HTML.() -> Unit) {
    writer.use {
        it.write("<!DOCTYPE html>")
        it.appendHTML().html(block = builder)
    }
}
