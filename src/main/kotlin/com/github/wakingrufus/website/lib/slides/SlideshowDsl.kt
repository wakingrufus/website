package com.github.wakingrufus.website.lib.slides

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.lib.code.sampleCode
import com.github.wakingrufus.website.lib.css
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.html.*
import java.util.*


fun BODY.slide(block: DIV.() -> Unit) {
    return div {
        style = css {
            display = Display.block
            width = 100.pct
            height = 100.pct
        }
        block(this)
    }
}

fun DIV.slideshowTitle(block: H1.() -> Unit) {
    return div {
        div {
            style = css {
                display = Display.block
                width = 100.pct
                height = 30.pct
            }
        }
        h1 {
            style = css {
                display = Display.block
                width = 100.pct
                color = MyStyles.FONT_COLOR
                textAlign = TextAlign.center
                fontSize = 8.em
            }
            block(this)
        }
    }
}

fun DIV.slideshowSubTitle(block: H3.() -> Unit) {
    return div {
        div {
            style = css {
                display = Display.block
                width = 100.pct
                height = 30.pct
            }
        }
        h3 {
            style = css {
                display = Display.block
                width = 100.pct
                color = MyStyles.FONT_COLOR_WEAK
                textAlign = TextAlign.center
                fontSize = 4.em
            }
            block(this)
        }
    }
}

fun DIV.slideshowTitleFooter(block: DIV.() -> Unit) {
    return div {
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

fun DIV.slideshowTitleFooterLine(block: P.() -> Unit) {
    return p {
        style = css {
            display = Display.block
            width = 100.pct
            color = MyStyles.FOOTER_FONT_COLOR
            textAlign = TextAlign.center
            fontSize = 3.em
            marginTop = 0.px
            marginBottom = 0.px
        }
        block(this)
    }
}

fun DIV.slideTitle(block: H2.() -> Unit) {
    return h2 {
        style = css {
            display = Display.block
            width = 100.pct
            color = MyStyles.TITLE_FONT_COLOR
            textAlign = TextAlign.left
            fontSize = 4.em
            marginBottom = 0.px
        }
        block(this)
    }
}

fun DIV.slideSubTitle(block: H4.() -> Unit) {
    return h4 {
        style = css {
            display = Display.block
            width = 100.pct
            color = MyStyles.FONT_COLOR_WEAK
            textAlign = TextAlign.left
            fontSize = 2.em
            height = 1.em
            marginTop = 0.px
            marginBottom = 0.px
        }
        block(this)
    }
}

fun DIV.splitSlide(leftBlock: DIV.() -> Unit, rightBlock: DIV.() -> Unit) {
    return div {
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

fun DIV.slideContent(block: DIV.() -> Unit) {
    return div {
        style = css {
            display = Display.block
            width = 100.pct
            height = 80.pct
            paddingTop = 1.em
        }
        block(this)
    }
}

fun DIV.slideList(block: UL.() -> Unit) {
    return ul {
        style = css {
            color = MyStyles.FONT_COLOR
            textAlign = TextAlign.left
            fontSize = 2.em
            lineHeight = LineHeight("1.5")
            display = Display.inlineBlock
            padding(right = 2.em, left = 1.em)
        }
        block(this)
    }
}

@HtmlTagMarker
class PICTURE(val name: String, val alt: String) {
    var css: (CSSBuilder.() -> Unit)? = null
    var caption: (SPAN.() -> Unit)? = null
    fun customCss(block: CSSBuilder.() -> Unit) {
        css = block
    }

    fun caption(block: SPAN.() -> Unit) {
        caption = block
    }

    operator fun invoke(div: DIV) {
        div.apply {
            figure {
                style = css {
                    display = Display.inlineBlock
                    verticalAlign = VerticalAlign.top
                    height = LinearDimension.auto
                    width = LinearDimension.auto
                    maxHeight = 100.pct
                    this@PICTURE.css?.invoke(this)
                }
                val imageResource = this::class.java.classLoader.getResourceAsStream(this@PICTURE.name)
                val imageBase64 = Base64.getEncoder().encodeToString(imageResource.readBytes())
                img(src = "data:image/png;base64, $imageBase64", alt = this@PICTURE.alt) {
                    style = css {
                        //   verticalAlign = VerticalAlign.top
                        height = 90.pct
                        //   width = LinearDimension.auto
                        maxHeight = 90.pct
                        this@PICTURE.css?.let {
                            it(this)
                        }
                    }
                }
                this@PICTURE.caption?.let {
                    figcaption {
                        span {
                            it(this)
                        }
                    }
                }
            }
        }
    }

}

fun DIV.slideTable(block: TABLE.() -> Unit) {
    return table {
        style = css {
            fontSize = 3.em
            borderStyle = BorderStyle.solid
            borderColor = Color.white
            borderWidth = 4.px
            borderCollapse = BorderCollapse.collapse
        }
        block(this)
    }
}

fun TBODY.row(cells: List<String>) {
    return tr {
        cells.forEach({
            td {
                style = css {
                    borderStyle = BorderStyle.solid
                    borderColor = Color.white
                    borderWidth = 1.px
                    padding(.4.em)
                }
                +it
            }
        })
    }
}

fun TABLE.headers(headers: List<String>) {
    return thead {
        style = css {
            fontWeight = FontWeight.bold
        }
        tr {
            style = css {
                borderStyle = BorderStyle.solid
                borderColor = Color.white
                borderWidth = 4.px
            }
            headers.forEach({
                td {
                    style = css {
                        borderStyle = BorderStyle.solid
                        borderColor = Color.white
                        borderWidth = 1.px
                        padding(.4.em)
                    }
                    +it
                }
            })
        }
    }
}

fun DIV.slidePicture(name: String, alt: String) = slidePicture(name = name, alt = alt) {
}

fun DIV.slidePicture(name: String, alt: String, block: PICTURE.() -> Unit) {
    PICTURE(name = name, alt = alt).apply(block)(this)
}

fun DIV.slideCode(block: CODE.() -> Unit) {
    return div {
        style = css {
            //    display = Display.inlineBlock
            verticalAlign = VerticalAlign.top
            fontSize = 2.em
            display = Display.inlineBlock
        }
        sampleCode {
            block(this)
        }

    }
}

fun CSSBuilder.center() {
    this.apply({
        display = Display.block
        marginRight = LinearDimension.auto
        marginLeft = LinearDimension.auto
    })
}