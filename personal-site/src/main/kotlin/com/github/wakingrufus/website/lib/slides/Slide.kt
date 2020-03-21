package com.github.wakingrufus.website.lib.slides

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.css
import kotlinx.css.*
import kotlinx.html.DIV
import kotlinx.html.HTML
import kotlinx.html.div
import kotlinx.html.style

class Slide(val title: String? = null,
            val subTitle: String? = null,
            val template: (mainCssPath: String,
                           title: String,
                           subTitle: String?,
                           slideNumber: Int,
                           totalSlides: Int,
                           content: DIV.() -> Unit) -> HTML.() -> Unit,
            val content: DIV.() -> Unit)

@WebsiteDsl
class SLIDE {
    var title: String? = null
    var subTitle: String? = null
    private var template: (mainCssPath: String,
                           title: String,
                           subTitle: String?,
                           slideNumber: Int,
                           totalSlides: Int,
                           content: DIV.() -> Unit) -> HTML.() -> Unit = ::slideTemplate
    private var content: DIV.() -> Unit = {}

    fun template(template: (mainCssPath: String,
                            title: String,
                            subTitle: String?,
                            slideNumber: Int,
                            totalSlides: Int,
                            content: DIV.() -> Unit) -> HTML.() -> Unit) {
        this.template = template
    }


    fun splitSlide(leftBlock: DIV.() -> Unit, rightBlock: DIV.() -> Unit) {
        content =  { div {
            style = css {
                display = Display.block
                width = 100.pct
                height = 80.pct
            }
            div {
                style = css {
                    verticalAlign = VerticalAlign.top
                    display = Display.inlineBlock
                    width = 50.pct
                    height = 100.pct
                }
                leftBlock(this)
            }
            div {
                style = css {
                    verticalAlign = VerticalAlign.top
                    display = Display.inlineBlock
                    width = 40.pct
                    height = 100.pct
                }
                rightBlock(this)
            }
        }
        }
    }

    fun content(builder: DIV.() -> Unit) {
        content = builder
    }

    fun titleFooter(block: DIV.() -> Unit) {
        content = {
            div {
                style = css {
                    display = Display.block
                    width = 100.pct
                    height = 30.pct
                    color = MyStyles.FOOTER_FONT_COLOR
                    textAlign = TextAlign.center
                }
                block(this)
            }
        }
    }

    fun slideContent(block: DIV.() -> Unit) {
        content = {
            div {
                style = css {
                    display = Display.block
                    width = 100.pct
                    height = 80.pct
                    paddingTop = 1.em
                }
                block(this)
            }
        }
    }

    operator fun invoke(): Slide {
        return Slide(title, subTitle, template, content)
    }
}

fun slide(block: SLIDE.() -> Unit): SLIDE.() -> Unit {
    return block
}