package com.github.wakingrufus.website.lib.article

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.HtmlPage
import com.github.wakingrufus.website.lib.WebsiteDsl
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import kotlinx.html.*

@WebsiteDsl
class ARTICLE(val title: String) {
    private var nav: BODY.() -> Unit = {}
    private var content: DIV.() -> Unit = {}

    fun nav(nav: BODY.() -> Unit) {
        this.nav = nav
    }

    fun content(block: DIV.() -> Unit) {
        content = block
    }

    operator fun invoke(page: HTML) {
        page.apply {
            head {
                link(href = Paths.CSS_PATH, rel = "stylesheet")
            }
            body {
                pageTitle(this@ARTICLE.title)
                this@ARTICLE.nav(this)
                content(this@ARTICLE.content)
            }
        }
    }

}


fun HtmlPage.article(title: String, article: ARTICLE.() -> Unit) {
    this.builder { ARTICLE(title).apply(article)(this) }
}