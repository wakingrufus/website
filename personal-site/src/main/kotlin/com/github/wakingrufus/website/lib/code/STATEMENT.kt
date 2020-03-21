package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.WebsiteDsl
import kotlinx.html.CODE

@WebsiteDsl
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
        body += { block(this) }
    }

    fun expression(block: EXPRESSION.() -> Unit) {
        body += { EXPRESSION(indentation = this@STATEMENT.indentation).apply(block)(this) }
    }

    fun constructor(className: String, call: CALL.() -> Unit = {}): SUBJECT {
        val subject = on(subject = { call(className, block = call) }) {}
        // body += { subject(this) }
        return subject
    }

    fun on(subject: CODE.() -> Unit, block: SUBJECT.() -> Unit): SUBJECT {
        val s = SUBJECT(baseIndentation = this@STATEMENT.indentation, subject = subject).apply(block)
        body += { s(this) }
        return s
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

    fun forLoop(iterator: String, collection: String, loopLogic: BLOCK.() -> Unit) {
        body += {
            keyword("for")
            +"("
            +iterator
            keyword(" in ")
            +collection
            +") "
            block(indentation = indentation) {
                loopLogic(this)
            }
        }
    }

    fun comment(comment: String) {
        body += {
            commentLine(comment)
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
