package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class BLOCK(val indentation: Int = 0, val inline: Boolean = true) {
    var prefix: (CODE.() -> Unit)? = null
    var body: List<CODE.() -> Unit> = ArrayList()
    fun line(block: CODE.() -> Unit) {
        body += {
            line(this@BLOCK.indentation + 1) {
                block(this)
            }
        }
    }

    fun expression(block: CODE.() -> Unit) {
        body += {
            +"\n"
            indent(this@BLOCK.indentation + 1)
            block(this)
        }
    }

    fun inlineExpression(block: CODE.() -> Unit) {
        body += {
            block(this)
        }
    }

    fun statement(statement: STATEMENT.() -> Unit) {
        body += { STATEMENT(indentation = this@BLOCK.indentation + 1).apply(statement)(this) }
    }

    fun assignment(modifier: String? = null, name: String, type: String? = null, value: (STATEMENT.() -> Unit)) {
        body += {
            +"\n"
            indent(this@BLOCK.indentation + 1)
            modifier?.let {
                keyword("$it ")
            }
            +name
            type?.let {
                +": "
                +it
            }
            +" = "
            STATEMENT(this@BLOCK.indentation + 1).apply(value)(this)
        }
    }

    fun block(block: BLOCK.() -> Unit) {
        body += { BLOCK(indentation = this@BLOCK.indentation + 1, inline = false).apply(block)(this) }
    }

    operator fun String.unaryPlus(): Unit {
        this.let {
            line {
                +it
            }
        }
    }

    operator fun invoke(code: CODE) {
        this.let { block ->
            code.apply {
                +"{"
                block.body.forEach {
                    it(this)
                }
                if (!this@BLOCK.inline) {
                    +"\n"
                    indent(this@BLOCK.indentation)
                }
                +"}"
            }
        }
    }
}