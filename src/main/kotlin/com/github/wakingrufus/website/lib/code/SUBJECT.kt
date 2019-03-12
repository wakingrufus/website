package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class SUBJECT(val baseIndentation: Int = 0, val subject: CODE.() -> Unit) {

    var calls: List<CODE.() -> Unit> = ArrayList()

    fun call(name: String,
             argsOnDifferentLines: Boolean = false,
             baseIndentation: Int = this@SUBJECT.baseIndentation,
             nullSafe: Boolean = false,
             block: CALL.() -> Unit = {}) : SUBJECT {
        calls += {
            val call = CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
                    .apply(block)
            if (nullSafe) {
                +"?"
            }
            if (call.isInfix) {
                +" "
            } else {
                +"."
            }
            call(this)
        }
        return this
    }

    fun callInvoke(block: CALL.() -> Unit = {}) {
        calls += {
            CALL(name = "", argsOnDifferentLines = false, baseIndentation = this@SUBJECT.baseIndentation)
                    .apply(block)(this)
        }
    }

    fun property(name: String, nullSafe: Boolean = false) {
        calls += {
            if (nullSafe) {
                +"?"
            }
            +"."
            propertyName(name)
        }
    }

    fun elvis(expression: EXPRESSION.() -> Unit) {
        calls += {
            +" ?: "
            EXPRESSION().apply(expression)(this)
        }
    }

    fun index(expression: EXPRESSION.() -> Unit) {
        calls += {
            +"["
            EXPRESSION().apply(expression)(this)
            +"]"
        }
    }

    fun index(number: Number) {
        calls += {
            number(number)
        }
    }

    fun breakAndCall(name: String,
                     argsOnDifferentLines: Boolean = true,
                     nullSafe: Boolean = false,
                     block: CALL.() -> Unit) : SUBJECT {
        calls += {
            +"\n"
            indent(this@SUBJECT.baseIndentation + 2)
            if (nullSafe) {
                +"?"
            }
            +"."
            CALL(name = name,
                    argsOnDifferentLines = argsOnDifferentLines,
                    baseIndentation = this@SUBJECT.baseIndentation + 2)
                    .apply(block)(this)
        }
        return this
    }

    operator fun invoke(code: CODE) {
        code.apply {
            this@SUBJECT.subject(code)
            this@SUBJECT.calls.forEach {
                it(this)
            }
        }
    }
}
