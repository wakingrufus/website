package com.github.wakingrufus.website.lib.article

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.*
import kotlinx.css.em
import kotlinx.html.*
import java.nio.charset.Charset

@WebsiteDsl
class ARTICLE(val title: String) {
    private var nav: BODY.() -> Unit = {}
    private var footer: FOOTER.() -> Unit = {}
    private var content: DIV.() -> Unit = {}
    private val sections: MutableList<Section> = mutableListOf()
    private var source: String? = null

    fun nav(nav: BODY.() -> Unit) {
        this.nav = nav
    }

    fun htmlSection(content: DIV.() -> Unit ){
        sections.add(HtmlSection(content))
    }

    fun markdownSection(path: String ){
        sections.add(MarkdownSection(path))
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
                content {
                    style = css {
                        marginLeft = 1.em
                    }
                    sections.forEach {
                        div {
                            this.apply(it.content)
                        }
                    }
                }
                footer { this.footer() }
            }

        }
    }

    fun footer(footer: FOOTER.() -> Unit){
        this.footer = footer
    }

}

fun HtmlPage.article(title: String, article: ARTICLE.() -> Unit) {
    this.builder { ARTICLE(title).apply(article)(this) }
}
