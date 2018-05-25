package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker


@HtmlTagMarker
class FUNCTION(val name: String,
               val returnType: String? = null,
               val indentation: Int = 0,
               val paramsOnSeparateLines: Boolean = true,
               val expression: Boolean = false) {
    var parameters: List<PARAMETER> = ArrayList()
    var body: (CODE.() -> Unit)? = null

    fun parameter(name: String, type: String?, value: (CODE.() -> Unit)? = null) {
        parameters += PARAMETER(name = name, type = type).apply { value?.let { value(it) } }
    }

    fun body(bodyText: BLOCK.() -> Unit) {
        body = { BLOCK(indentation = this@FUNCTION.indentation).apply(bodyText)(this) }
    }

    fun expression(value: CODE.() -> Unit) {
        body = value
    }

    private fun buildExpression(code: CODE) {
        code.apply {
            +" = "
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
            keyword("fun")
            +" "
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
            }
            if (this@FUNCTION.expression) {
                this@FUNCTION.buildExpression(this)
            } else {
                this@FUNCTION.buildFunctionBody(this)
            }
        }
    }
}
