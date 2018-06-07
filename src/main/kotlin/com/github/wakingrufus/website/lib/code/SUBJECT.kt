package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class SUBJECT(val subject: CODE.() -> Unit) {

    var calls: List<CODE.() -> Unit> = ArrayList()

    fun call(name: String, argsOnDifferentLines: Boolean = true, baseIndentation: Int = 0, block: CALL.() -> Unit) {
        calls += {
            +"."
            CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
                    .apply(block)(this)
        }
    }

    fun breakAndCall(name: String,
                     argsOnDifferentLines: Boolean = true,
                     baseIndentation: Int = 0,
                     block: CALL.() -> Unit) {
        calls += {
            +"\n"
            indent(baseIndentation + 2)
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
