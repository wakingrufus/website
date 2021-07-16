package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.lib.css
import kotlinx.css.Color
import kotlinx.css.FontStyle
import kotlinx.css.color
import kotlinx.css.fontStyle
import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker
import kotlinx.html.span
import kotlinx.html.style

@HtmlTagMarker
class CALL(val name: String,
           val baseIndentation: Int = 0,
           val argsOnDifferentLines: Boolean = true) {

    var genericType: String? = null
    var arguments: List<ARGUMENT> = ArrayList()
    private var lambdaBlock: BLOCK? = null
    var callType: (String) -> CODE.() -> Unit = ::functionCall
    var isInfix = false

    fun argument(name: String? = null, value: (CODE.() -> Unit)) {
        arguments += ARGUMENT(name = name, valueBlock = value)
    }

    fun argumentExp(name: String? = null, value: (EXPRESSION.() -> Unit)) {
        arguments += ARGUMENT(name = name, valueBlock = { EXPRESSION().apply(value)(this) })
    }

    fun lambda(inline: Boolean = false, indentation: Int = baseIndentation, value: (BLOCK.() -> Unit)) {
        lambdaBlock = BLOCK(indentation = indentation, inline = inline).apply(value)
    }

    fun extensionFunction() {
        callType = ::extensionFunctionCall
    }

    fun packageFunction() {
        callType = ::packageFunctionCall
    }

    fun infix() {
        isInfix = true
    }

    operator fun invoke(code: CODE) {
        callType(name)(code)
        genericType?.also {
            code.apply {
                +"<$it>"
            }
        }
        code.apply {
            //  if (this@CALL.name[0].isLowerCase()) functionCall(this@CALL.name) else +this@CALL.name
            if (this@CALL.arguments.isNotEmpty() || this@CALL.lambdaBlock == null) {
                if (this@CALL.isInfix) {
                    +" "
                } else {
                    +"("
                }
                this@CALL.arguments.forEachIndexed { i, a ->
                    if (this@CALL.argsOnDifferentLines) {
                        +"\n"
                        indent(this@CALL.baseIndentation + 1)
                    }
                    a(this)
                    if (i < this@CALL.arguments.size - 1) {
                        +","
                        if (!this@CALL.argsOnDifferentLines) {
                            +" "
                        }
                    }
                }
                if (!this@CALL.isInfix) {
                    +")"
                }
            }
            this@CALL.lambdaBlock?.let {
                +" "
                it(this)
            }
        }
    }
}


fun functionCall(text: String): CODE.() -> Unit {
    return {
        span {
            style = css {
            }
            +text
        }
    }
}

fun annotationCall(text: String): CODE.() -> Unit {
    return {
        this.keyword(text)
    }
}

fun packageFunctionCall(text: String): CODE.() -> Unit {
    return {
        span {
            style = css {
                fontStyle = FontStyle.italic
            }
            +text
        }
    }
}

fun extensionFunctionCall(text: String): CODE.() -> Unit {
    return {
        span {
            style = css {
                color = Color("#FFC66D")
                fontStyle = FontStyle.italic
            }
            +text
        }
    }
}