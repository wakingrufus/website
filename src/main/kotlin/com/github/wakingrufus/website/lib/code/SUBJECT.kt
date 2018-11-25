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
             block: CALL.() -> Unit = {}) {
        calls += {
            if (nullSafe) {
                +"?"
            }
            +"."
            CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
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

    fun breakAndCall(name: String,
                     argsOnDifferentLines: Boolean = true,
                     baseIndentation: Int = 0,
                     nullSafe: Boolean = false,
                     block: CALL.() -> Unit) {
        calls += {
            +"\n"
            indent(baseIndentation + 2)
            if (nullSafe) {
                +"?"
            }
            +"."
            CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
                    .apply(block)(this)
        }
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
