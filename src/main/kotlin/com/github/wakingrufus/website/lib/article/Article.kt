package com.github.wakingrufus.website.lib.article

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.*
import kotlinx.html.*
import java.nio.charset.Charset

@WebsiteDsl
class ARTICLE(val title: String) {
    private var nav: BODY.() -> Unit = {}
    private var content: DIV.() -> Unit = {}
    private var source: String? = null

    fun nav(nav: BODY.() -> Unit) {
        this.nav = nav
    }

    fun content(block: DIV.() -> Unit) {
        content = block
    }

    fun markdownContent(source: String) {
        this.source = source
    }

    operator fun invoke(page: HTML) {
        page.apply {
            head {
                link(href = Paths.CSS_PATH, rel = "stylesheet")
            }
            body {
                pageTitle(this@ARTICLE.title)
                this@ARTICLE.nav(this)
                if (this@ARTICLE.source != null) {
                    this@body.markdownContent(this::class.java.classLoader.getResourceAsStream(this@ARTICLE.source).readBytes().toString(Charset.defaultCharset()))
                } else {
                    content(this@ARTICLE.content)
                }
            }
        }
    }

}


fun HtmlPage.article(title: String, article: ARTICLE.() -> Unit) {
    this.builder { ARTICLE(title).apply(article)(this) }
}