package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.WebsiteDsl
import kotlinx.html.CODE
import kotlinx.html.code

@WebsiteDsl
class FOR(val indentation: Int = 0,
          val iterator: String) {
    private var collection: CODE.() -> Unit = {}
    private var logic: CODE.() -> Unit = {}

    fun collectionExpression(expression: EXPRESSION.() -> Unit) {
        collection = { EXPRESSION(indentation = indentation).apply(expression)(this) }
    }

    fun logicExpression(expression: EXPRESSION.() -> Unit) {
        logic = { EXPRESSION(indentation = indentation).apply(expression)(this) }
    }

    fun logicBlock(block: BLOCK.() -> Unit) {
        logic = { BLOCK(indentation = indentation + 1, inline = false).apply(block)(this) }
    }

    operator fun invoke(code: CODE) {
        code.keyword("for")
        code.code {
            +" ("
            +iterator
            keyword(" in ")
        }
        collection(code)
        code.code { +") " }
        logic(code)
    }
}