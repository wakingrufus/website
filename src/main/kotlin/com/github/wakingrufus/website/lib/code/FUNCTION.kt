package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE


@CodeDsl
class FUNCTION(val name: String,
               val returnType: String? = null,
               val indentation: Int = 0,
               val paramsOnSeparateLines: Boolean = true,
               val expression: Boolean = false) {
    var parameters: List<PARAMETER> = ArrayList()
    var body: (BLOCK)? = null
    var returnStatement: (CODE.() -> Unit)? = null

    fun parameter(name: String, type: String?, value: (CODE.() -> Unit)? = null) {
        parameters += PARAMETER(name = name, type = type).apply { value?.let { value(it) } }
    }

    fun body(bodyText: BLOCK.() -> Unit) {
        body = BLOCK(indentation = indentation).apply(bodyText)
    }

    fun returnStatement(block: CODE.() -> Unit) {
        returnStatement = block
    }

    private fun buildExpression(code: CODE) {
        code.apply {
            +" = "
            body?.let {
                it(this)
            }
            +"\n"
        }
    }

    private fun buildFunctionBody(code: CODE) {
        code.apply {
            body?.let {
                it.invoke(this)
            }
            returnStatement?.let {
                +"\n"
                line {
                    indent(indentation+1)
                    declareReturn(it)
                }
            }
        }
    }

    operator fun invoke(code: CODE) {
        code.apply {
            indent(indentation)
            keyword("fun")
            +" "
            functionName(name)
            +"("
            parameters.forEachIndexed { i, a ->
                if (paramsOnSeparateLines) {
                    +"\n"
                    indent(indentation + 1)
                }
                a.invoke(this)
                if (i < parameters.size - 1) {
                    +", "
                }
            }
            +")"
            returnType?.let {
                +" : "
                +it
            }
            if (expression) buildExpression(this) else buildFunctionBody(this)
        }
    }
}
