package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class STATEMENT(val indentation: Int = 0) {
    var assignment: ASSIGNMENT? = null
    var isReturn: Boolean = false
    var body: (List<CODE.() -> Unit>) = ArrayList()

    fun returns(block: EXPRESSION.() -> Unit) {
        isReturn = true
        body += {
            EXPRESSION(indentation = this@STATEMENT.indentation).apply(block)(this)
        }
    }

    fun assignment(modifier: String? = null,
                   name: String,
                   format: CODE.(String) -> Unit = CODE::propertyName,
                   type: String? = null,
                   operator: String = "=") {
        assignment = ASSIGNMENT(modifier = modifier, name = name, format = format, type = type, operator = operator)
    }

    fun inlineExpression(block: CODE.() -> Unit) {
        body += {
            block(this)
        }
    }

    fun expression(block: EXPRESSION.() -> Unit) {
        body += { EXPRESSION(indentation = this@STATEMENT.indentation).apply(block)(this) }
    }

    fun on(subject: CODE.() -> Unit, block: SUBJECT.() -> Unit) {
        body += { SUBJECT(baseIndentation = this@STATEMENT.indentation, subject = subject).apply(block)(this) }
    }

    fun call(name: String, argsOnDifferentLines: Boolean = false, baseIndentation: Int = indentation, call: CALL.() -> Unit = {}) {
        body += {
            CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
                    .apply(call)(this)
        }
    }

    fun chain(lineBreak: Boolean = false, block: STATEMENT.() -> Unit) {
        body += {
            if (lineBreak) {
                +"\n"
                indent(this@STATEMENT.indentation + 1)
            }
            STATEMENT(indentation = this@STATEMENT.indentation + 1)
                    .apply { inlineExpression { +"." } }
                    .apply(block)(this)
        }

    }

    operator fun invoke(code: CODE) {
        this.let { block ->
            code.apply {
                block.assignment?.let {
                    it(this)
                }
                if (block.isReturn) {
                    code.keyword("return ")
                }
                block.body.forEachIndexed { index, function ->
                    function(this)
                }
            }
        }
    }
}
