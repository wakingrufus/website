package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class BLOCK(val indentation: Int = 0, var inline: Boolean = true) {
    var prefix: (CODE.() -> Unit)? = null
    var body: List<CODE.() -> Unit> = ArrayList()
    var statements: List<STATEMENT> = listOf()

    fun call(name: String, argsOnDifferentLines: Boolean = false, call: CALL.() -> Unit) {
        statement{
            call(name = name, baseIndentation = indentation, argsOnDifferentLines = argsOnDifferentLines, call = call)
        }
    }

    fun expression(block: CODE.() -> Unit) {
        statement {
            inlineExpression(block)
        }
    }

    fun statement(statement: STATEMENT.() -> Unit) {
        if(statements.isNotEmpty()){
            inline = false
        }
        statements += STATEMENT(indentation = this@BLOCK.indentation + 1).apply(statement)
    }

    fun returns(block: EXPRESSION.() -> Unit){
        statement {
            returns (block)
        }
    }

    fun assignment(modifier: String? = null,
                   name: String,
                   type: String? = null,
                   format: CODE.(String) -> Unit = CODE::propertyName,
                   operator: String = "=",
                   value: (STATEMENT.() -> Unit)) {
        statement {
            assignment(modifier = modifier, name = name, type = type, format = format, operator = operator)
            value(this)
        }
    }

    fun block(block: BLOCK.() -> Unit) {
        body += { BLOCK(indentation = this@BLOCK.indentation + 1, inline = false).apply(block)(this) }
    }

    operator fun invoke(code: CODE) {
        this.let { block ->
            code.apply {
                +"{"
                block.body.forEach {
                    it(this)
                }
                block.statements.forEach {
                    if(this@BLOCK.inline){
                        +" "
                    } else{
                        +"\n"
                        indent(this@BLOCK.indentation+1)
                    }
                    it(this)
                }
                if (!this@BLOCK.inline) {
                    +"\n"
                    indent(this@BLOCK.indentation)
                } else {
                    +" "
                }
                +"}"
            }
        }
    }
}
