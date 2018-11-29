package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker


@HtmlTagMarker
class FUNCTION(val operator: Boolean = false,
               val name: String,
               val returnType: String? = null,
               val indentation: Int = 0,
               val paramsOnSeparateLines: Boolean = true,
               val extensionOf: String? = null) {
    var parameters: List<PARAMETER> = ArrayList()
    var body: (CODE.() -> Unit)? = null
    var expression: Boolean = false

    fun parameter(name: String, type: String?, value: (CODE.() -> Unit)? = null) {
        parameters += PARAMETER(name = name, type = type).apply { value?.let { value(it) } }
    }

    fun body(indentation: Int = this.indentation, bodyText: BLOCK.() -> Unit) {
        body = { BLOCK(indentation = indentation, inline = false).apply(bodyText)(this) }
        expression = false
    }
    fun expression2(value: EXPRESSION.() -> Unit) {
        body = { EXPRESSION().apply(value)(this) }
        expression = true
    }
    fun expression(value: CODE.() -> Unit) {
        body = { value(this) }
        expression = true
    }

    private fun buildExpression(code: CODE) {
        code.apply {
            +"= "
            this@FUNCTION.body?.invoke(this)
            +"\n"
        }
    }

    private fun buildFunctionBody(code: CODE) {
        code.apply {
            this@FUNCTION.body?.invoke(this)
        }
    }

    operator fun invoke(code: CODE) {
        code.apply {
            indent(this@FUNCTION.indentation)
            if (this@FUNCTION.operator) {
                keyword("operator ")
            }
            keyword("fun")
            +" "
            this@FUNCTION.extensionOf?.let {
                +it
                +"."
            }
            functionName(this@FUNCTION.name)
            +"("
            this@FUNCTION.parameters.forEachIndexed { i, a ->
                if (this@FUNCTION.paramsOnSeparateLines) {
                    +"\n"
                    indent(this@FUNCTION.indentation + 2)
                }
                a.invoke(this)
                if (i < this@FUNCTION.parameters.size - 1) {
                    +", "
                }
            }
            +") "
            this@FUNCTION.returnType?.let {
                +": "
                +it
                +" "
            }
            if (this@FUNCTION.expression) {
                this@FUNCTION.buildExpression(this)
            } else {
                this@FUNCTION.buildFunctionBody(this)
            }
        }
    }
}
