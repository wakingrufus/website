package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class CALL(val name: String, val baseIndentation: Int = 0, val argsOnDifferentLines: Boolean = true) {
    var arguments: List<ARGUMENT> = ArrayList()

    fun argument(name: String? = null, value: (CODE.() -> Unit)) {
        arguments += ARGUMENT(name = name, valueBlock = value)
    }

    operator fun invoke(code: CODE) {
        code.apply {
            +this@CALL.name
            +"("
            this@CALL.arguments.forEachIndexed { i, a ->
                if (this@CALL.argsOnDifferentLines) {
                    +"\n"
                    indent(this@CALL.baseIndentation + 1)
                }
                a(this)
                if (i < this@CALL.arguments.size - 1) {
                    +","
                }
            }
            +")"
        }
    }
}
