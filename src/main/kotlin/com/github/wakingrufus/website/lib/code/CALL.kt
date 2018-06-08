package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class CALL(val name: String, val baseIndentation: Int = 0, val argsOnDifferentLines: Boolean = true) {
    var arguments: List<ARGUMENT> = ArrayList()
    var lambdaBlock: BLOCK? = null

    fun argument(name: String? = null, value: (CODE.() -> Unit)) {
        arguments += ARGUMENT(name = name, valueBlock = value)
    }

    fun lambda(inline: Boolean = false, value: (BLOCK.() -> Unit)) {
        lambdaBlock = BLOCK(indentation = baseIndentation + 1, inline = inline).apply(value)
    }

    operator fun invoke(code: CODE) {
        code.apply {
            if (this@CALL.name[0].isLowerCase()) functionCall(this@CALL.name) else +this@CALL.name
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
