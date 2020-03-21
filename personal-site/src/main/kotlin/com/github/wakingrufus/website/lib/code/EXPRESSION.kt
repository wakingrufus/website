package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class EXPRESSION(val indentation: Int = 0) {
    var body: List<CODE.() -> Unit> = listOf()
    fun block(block: BLOCK.() -> Unit) {
        body += { BLOCK(this@EXPRESSION.indentation).apply(block)(this) }
    }

    fun inline(block: CODE.() -> Unit) {
        body += block
    }

    fun range(from: Int, to: Int){
        body += {
            number(from)
            +".."
            number(to)
        }
    }

    fun whenExpression(whenBlock: WHEN.() -> Unit) {
        body += { WHEN(indentation = this@EXPRESSION.indentation).apply(whenBlock)(this) }
    }

    fun on(subject: CODE.() -> Unit, block: SUBJECT.() -> Unit) {
        body += { SUBJECT(baseIndentation = this@EXPRESSION.indentation, subject = subject).apply(block)(this) }
    }

    fun call(operator: String = ".",
             name: String,
             argsOnDifferentLines: Boolean = false,
             baseIndentation: Int = indentation,
             call: CALL.() -> Unit) {
        if (body.isNotEmpty()) {
            body += { +operator }
        }
        body += {
            CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
                    .apply(call)(this)
        }
    }

    operator fun invoke(code: CODE) {
        body.forEach {
            it(code)
        }
    }
}