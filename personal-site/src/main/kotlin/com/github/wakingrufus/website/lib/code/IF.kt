package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.WebsiteDsl
import kotlinx.html.CODE
import kotlinx.html.code

@WebsiteDsl
class IF(val indentation: Int = 0) {
    private var condition: CODE.() -> Unit = {}
    private var trueCase: CODE.() -> Unit = {}
    private var falseCase: (CODE.() -> Unit)? = null

    fun conditionExpression(expression: EXPRESSION.() -> Unit) {
        condition = { EXPRESSION(indentation = indentation).apply(expression)(this) }
    }

    fun trueExpression(expression: EXPRESSION.() -> Unit) {
        trueCase = { EXPRESSION(indentation = indentation).apply(expression)(this) }
    }

    fun trueblock(block: BLOCK.() -> Unit) {
        trueCase = { BLOCK(indentation = indentation + 1, inline = false).apply(block)(this) }
    }

    fun falseExpression(expression: EXPRESSION.() -> Unit) {
        falseCase = { EXPRESSION(indentation = indentation).apply(expression)(this) }
    }

    fun falseblock(block: BLOCK.() -> Unit) {
        falseCase = { BLOCK(indentation = indentation).apply(block)(this) }
    }

    operator fun invoke(code: CODE) {
        code.keyword("if")
        code.code { +" (" }
        condition(code)
        code.code { +") " }
        trueCase(code)
        falseCase?.let {
            it(code)
        }
    }
}