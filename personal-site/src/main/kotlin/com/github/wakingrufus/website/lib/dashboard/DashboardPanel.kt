package com.github.wakingrufus.website.lib.dashboard

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.css
import kotlinx.css.BorderStyle
import kotlinx.css.Color
import kotlinx.css.Cursor
import kotlinx.css.Display
import kotlinx.css.TextAlign
import kotlinx.css.VerticalAlign
import kotlinx.css.backgroundColor
import kotlinx.css.borderColor
import kotlinx.css.borderStyle
import kotlinx.css.borderTopColor
import kotlinx.css.borderTopStyle
import kotlinx.css.borderTopWidth
import kotlinx.css.cursor
import kotlinx.css.display
import kotlinx.css.em
import kotlinx.css.marginBottom
import kotlinx.css.marginTop
import kotlinx.css.maxWidth
import kotlinx.css.paddingBottom
import kotlinx.css.paddingLeft
import kotlinx.css.paddingRight
import kotlinx.css.paddingTop
import kotlinx.css.pct
import kotlinx.css.px
import kotlinx.css.textAlign
import kotlinx.css.verticalAlign
import kotlinx.css.width
import kotlinx.html.DETAILS
import kotlinx.html.DIV
import kotlinx.html.a
import kotlinx.html.details
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.h3
import kotlinx.html.img
import kotlinx.html.p
import kotlinx.html.span
import kotlinx.html.style
import kotlinx.html.summary

@WebsiteDsl
class DashboardPanel {
    private var title: String? = null
    private var content: DIV.() -> Unit = {}
    private val subPanels = mutableListOf<SubPanel>()
    private var expandable: Boolean = false

    @WebsiteDsl
    fun expandable() {
        expandable = true
    }

    @WebsiteDsl
    fun title(title: String) {
        this.title = title
    }

    @WebsiteDsl
    fun content(c: DIV.() -> Unit) {
        content = c
    }

    @WebsiteDsl
    fun subPanel(title: String, href: String? = null, imageUrl: String? = null, content: SubPanel.() -> Unit) {
        subPanels.add(SubPanel(title, href, imageUrl).apply(content))
    }

    @WebsiteDsl
    fun project(org: String, name: String, description: SubPanel.() -> Unit) {
        subPanel(name, "https://github.com/$org/$name", "https://img.shields.io/github/stars/$org/$name", description)
    }

    private fun DIV.summary() {
        this@DashboardPanel.title?.let { t ->
            h2 {
                style = css {
                    textAlign = TextAlign.center
                }
                +t
            }
        }
    }

    private fun DETAILS.expandableSummary() {
        summary {
            style = css {
                display = Display.block
                cursor = Cursor.pointer
            }
            this@DashboardPanel.title?.let { t ->
                h2 {
                    style = css {
                        textAlign = TextAlign.center
                    }
                    +t
                }
            }
        }
    }

    private fun DIV.subPanelSummary(subPanel: SubPanel) {
        div {
            style = css {
                marginTop = 8.px
                display = Display.block
            }
            h3 {
                style = css {
                    display = Display.inlineBlock
                    width = 49.pct
                    marginTop = 2.px
                    marginBottom = 2.px
                }
                if (subPanel.link == null) {
                    +subPanel.name
                } else {
                    a(href = subPanel.link) { +subPanel.name }
                }
            }
            div {
                style = css {
                    display = Display.inlineBlock
                    width = 49.pct
                    textAlign = TextAlign.right
                    verticalAlign = VerticalAlign.middle
                }
                if (subPanel.image != null) {
                    a(href = subPanel.link) {
                        img(src = subPanel.image) {
                        }
                    }
                }
            }
        }
    }

    private fun DETAILS.expandableSubPanelSummary(subPanel: SubPanel) {
        summary {
            style = css {
                //      display = Display.inline
                cursor = Cursor.pointer
            }
            h3 {
                style = css { display = Display.inlineBlock }
                if (subPanel.link == null) {
                    span {
                        style = css { display = Display.inlineBlock }
                        +subPanel.name
                    }
                } else {
                    a(href = subPanel.link) { +subPanel.name }
                }
            }
        }
    }

    private fun DIV.renderSubPanelContent(subPanel: SubPanel) {
        p {
            style = css {
                paddingRight = 4.px
            }
            subPanel.content.invoke(this)
        }
        div {
            style = css {
                paddingBottom = 12.px
            }
            subPanel.entries.forEach { topicEntry ->
                div {
                    style = css {
                        paddingTop = 4.px
                        paddingBottom = 12.px
                    }
                    if (topicEntry.links.size == 1) {
                        a(href = topicEntry.links.first().second) { +topicEntry.name }
                    } else {
                        +topicEntry.name
                        topicEntry.links.forEach {
                            +" ["
                            a(href = it.second) { +it.first }
                            +"] "
                        }
                    }
                }
            }
        }
    }

    private fun DIV.renderSubpanels() {
        subPanels.forEach { subPanel ->
            div {
                style = css {
                    borderTopStyle = BorderStyle.solid
                    borderTopColor = Color.darkGreen
                    borderTopWidth = 1.px
                }
                if (subPanel.expandable) {
                    details {
                        expandableSubPanelSummary(subPanel)
                        div {
                            renderSubPanelContent(subPanel)
                        }
                    }
                } else {
                    subPanelSummary(subPanel)
                    renderSubPanelContent(subPanel)
                }
            }
        }
    }


    operator fun invoke(div: DIV) {
        div.apply {
            div {
                style = css {
                    backgroundColor = MyStyles.SUB_BACKGROUND_COLOR
                    borderStyle = BorderStyle.solid
                    borderColor = MyStyles.FONT_COLOR
                    paddingLeft = 1.em
                    paddingRight = 1.em
                    display = Display.inlineBlock
                    verticalAlign = VerticalAlign.top
                    maxWidth = 40.em
                }
                if (expandable) {
                    details {
                        expandableSummary()
                        div {
                            this.content()
                            renderSubpanels()
                        }
                    }
                } else {
                    summary()
                    content()
                    renderSubpanels()
                }
            }
        }
    }
}