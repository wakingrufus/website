package com.github.wakingrufus.website.lib.article

import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.HtmlPage
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.css
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.standardHead
import kotlinx.css.em
import kotlinx.css.pct
import kotlinx.html.*

@WebsiteDsl
class ArticleBuilder(val title: String) {
    private var nav: Navigation? = null
    private var footer: FOOTER.() -> Unit = {}
    private var content: ARTICLE.() -> Unit = {}
    private val sections: MutableList<Section> = mutableListOf()
    private var source: String? = null

    fun nav(nav: Navigation.() -> Unit) {
        this.nav = Navigation().apply(nav)
    }

    fun htmlSection(content: ARTICLE.() -> Unit) {
        sections.add(HtmlSection(content))
    }

    fun markdownSection(path: String) {
        sections.add(MarkdownSection(path))
    }

    fun content(block: ARTICLE.() -> Unit) {
        content = block
    }

    fun markdownContent(source: String) {
        this.source = source
    }

    fun getContent(): ARTICLE.() -> Unit = {
        sections.forEach {
            this.apply(it.content)
        }
    }

    operator fun invoke(page: BODY) {
        page.apply {
            pageTitle(this@ArticleBuilder.title)
            this@ArticleBuilder.nav?.invoke(this)
            content {
                style = css {
                    marginLeft = 1.em
                    marginRight = 1.em
                    if (nav != null) {
                        maxWidth = 79.pct
                    }
                }
                article {
                    getContent().invoke(this)
                }
            }
            footer { this.footer() }
        }
    }

    fun footer(footer: FOOTER.() -> Unit) {
        this.footer = footer
    }

}

fun HtmlPage.article(title: String, block: ArticleBuilder.() -> Unit) {
    val article = ArticleBuilder(title).apply(block)
    this.standardHead()
    this@article.body { article.invoke(this) }
    this.title(article.title)
}
