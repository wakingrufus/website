package com.github.wakingrufus.website.lib

import kotlinx.css.em
import kotlinx.html.*

fun DIV.area(name: String, block: PLACE.() -> Unit) {
    return PLACE(name = name).apply(block)(this)
}

@HtmlTagMarker
class PLACE(val name: String,
            var website: String? = null,
            var map: String? = null,
            var description: P.() -> Unit = {},
            val level: NestedAreaLevel = NestedAreaLevel(1)) {
    private var places: List<PLACE> = ArrayList()
    private var subAreas: List<PLACE> = ArrayList()

    fun place(name: String, website: String? = null, block: PLACE.() -> Unit) {
        places += PLACE(name = name, website = website, level = NestedAreaLevel(6)).apply(block)
    }

    fun subArea(name: String, block: PLACE.() -> Unit) {
        subAreas += PLACE(name = name, level = level.next()).apply(block)
    }

    fun description(block: P.() -> Unit) {
        description = block
    }

    operator fun invoke(code: DIV) {
        code.run {
            a { id = this@PLACE.name.toLowerCase() }
            headerLevel(this@PLACE.level)(this,null) {
                if (this@PLACE.level.value == 6) {
                    style = css {
                        fontSize = .67.em
                        marginBottom = 1.em
                    }
                }
                +this@PLACE.name
            }.apply { }
            this@PLACE.website?.let {
                div {
                    if (this@PLACE.level.value == 6) {
                        style = css {
                            fontSize = .67.em
                            marginBottom = 1.em
                        }
                    }
                    a(href = it) { +"Website" }
                }
            }
            p {
                if (this@PLACE.level.value == 6) {
                    style = css {
                        fontSize = .67.em
                    }
                }
                this@PLACE.description(this)
            }
            div {
                style = css {
                    paddingLeft = this@PLACE.level.value.em
                }
                this@PLACE.places.forEach { place ->
                    place(code)
                }
                this@PLACE.subAreas.forEach { subArea ->
                    subArea(code)
                }
            }
        }
    }
}

fun FlowOrHeadingContent.headerLevel(level: NestedAreaLevel): FlowOrHeadingContent.(String?, HtmlBlockInlineTag.() -> Unit) -> Unit {
    return when (level.value) {
        1 -> FlowOrHeadingContent::h1
        2 -> FlowOrHeadingContent::h2
        3 -> FlowOrHeadingContent::h3
        4 -> FlowOrHeadingContent::h4
        5 -> FlowOrHeadingContent::h5
        6 -> FlowOrHeadingContent::h6
        else -> FlowOrHeadingContent::h1
    }
}

inline class NestedAreaLevel(val value: Int)

fun NestedAreaLevel.next(): NestedAreaLevel {
    return when (this.value) {
        1 -> NestedAreaLevel(2)
        2 -> NestedAreaLevel(3)
        3 -> NestedAreaLevel(4)
        4 -> NestedAreaLevel(5)
        5 -> NestedAreaLevel(6)
        6 -> NestedAreaLevel(6)
        else -> NestedAreaLevel(1)
    }
}
