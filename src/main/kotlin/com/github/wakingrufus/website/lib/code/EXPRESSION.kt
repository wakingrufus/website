package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE

class EXPRESSION(val indentation : Int = 0){
    var body: List<CODE.() -> Unit> = listOf()
    fun block(block: BLOCK.() -> Unit){
        body += {BLOCK(indentation).apply(block)(this)}
    }

    // TODO: eliminate
    @Deprecated(message = "try more specific expression builders", replaceWith = ReplaceWith("block"))
    fun code(code: CODE.() -> Unit){
        body += code
    }

    fun whenExpression(indentation: Int = 0, whenBlock: WHEN.() -> Unit) {
        body += { WHEN(indentation = indentation).apply(whenBlock)(this) }
    }

    fun call(name: String, argsOnDifferentLines: Boolean = false, baseIndentation: Int = indentation, call: CALL.() -> Unit) {
        body += {
            CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
                    .apply(call)(this)
        }
    }

    operator fun invoke(code: CODE){
        body.forEach {
           it(code)
        }
    }
}