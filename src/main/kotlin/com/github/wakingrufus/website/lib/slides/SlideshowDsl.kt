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

fun DIV.singleSlide(block: DIV.() -> Unit) {
    return div {
        style = css {
            display = Display.block
            width = 100.pct
            height = 80.pct
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

fun DIV.slidePicture(name: String) = slidePicture(name) {

}

fun DIV.slidePicture(name: String, block: CSSBuilder.() -> Unit) {
    val imageResource = this::class.java.classLoader.getResourceAsStream(name)
    val imageBase64 = Base64.getEncoder().encodeToString(imageResource.readBytes())
    return img(src = "data:image/png;base64, $imageBase64") {
        style = css {
            verticalAlign = VerticalAlign.top
            height = LinearDimension.auto
            width = LinearDimension.auto
            maxHeight = 100.pct
            block(this)
        }

    }
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
            block.invoke(this)
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