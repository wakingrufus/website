package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.lib.css
import kotlinx.css.Color
import kotlinx.css.FontStyle
import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker
import kotlinx.html.span
import kotlinx.html.style

@HtmlTagMarker
class CALL(val name: String,
           val baseIndentation: Int = 0,
           val argsOnDifferentLines: Boolean = true) {
    var subject : SUBJECT? = null
    var arguments: List<ARGUMENT> = ArrayList()
    var lambdaBlock: BLOCK? = null
    var callType: (String) -> CODE.() -> Unit = ::functionCall

    fun argument(name: String? = null, value: (CODE.() -> Unit)) {
        arguments += ARGUMENT(name = name, valueBlock = value)
    }

    fun argumentExp(name: String? = null, value: (EXPRESSION.() -> Unit)) {
        arguments += ARGUMENT(name = name, valueBlock = {EXPRESSION().apply(value)(this)})
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

    operator fun invoke(code: CODE) {
        code.apply {
            this@CALL.callType(this@CALL.name)(this)
            //  if (this@CALL.name[0].isLowerCase()) functionCall(this@CALL.name) else +this@CALL.name
            if (this@CALL.arguments.isNotEmpty() || this@CALL.lambdaBlock == null) {
                +"("
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
                +")"
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