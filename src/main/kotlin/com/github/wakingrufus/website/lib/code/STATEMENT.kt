package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class STATEMENT(val indentation: Int = 0) {
    var body: (List<CODE.() -> Unit>) = ArrayList()
    var blockCall: BLOCK? = null
    fun expression(block: CODE.() -> Unit) {
        body += {
            +"\n"
            indent(this@STATEMENT.indentation)
            block(this)
        }
    }

    fun inlineExpression(block: CODE.() -> Unit) {
        body += {
            block(this)
        }
    }

    fun on(subject: CODE.() -> Unit, block: SUBJECT.() -> Unit) {
        body += { SUBJECT(subject).apply(block)(this) }
    }

    fun call(name: String, argsOnDifferentLines: Boolean = false, baseIndentation: Int = 0, call: CALL.() -> Unit) {
        body += {
            +"."
            CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
                    .apply(call)(this)
        }
    }

    fun chain(block: STATEMENT.() -> Unit) {
        body += {
            +"\n"
            indent(this@STATEMENT.indentation + 1)
            STATEMENT(indentation = this@STATEMENT.indentation + 1)
                    //   .apply { +"." }
                    .apply(block)(this)
        }

    }

//    fun block(block: BLOCK.() -> Unit) {
//        blockCall = BLOCK(indentation = indentation + 1).apply(block)
//    }

    operator fun String.unaryPlus() {
        this.let {
            expression {
                +it
            }
        }
    }

    operator fun invoke(code: CODE) {
        this.let { block ->
            code.apply {
                block.body.forEachIndexed { index, function ->
                    function(this)
                }

                block.blockCall?.let {
                    +" "
                    it(this)
                }
            }
        }
    }
}
