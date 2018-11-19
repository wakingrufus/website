package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE

class WHEN(val indentation: Int = 0) {
    var subject: (CODE.() -> Unit)? = null
    var conditions: List<CODE.() -> Unit> = listOf()

    fun subject(code: CODE.() -> Unit) {
        subject = code
    }

    fun pair(condition: CODE.() -> Unit, result: CODE.() -> Unit) {
        conditions += {
            condition(this)
            +" -> "
            result(this)
        }
    }

    operator fun invoke(code: CODE) {
        code.apply {
            keyword("when ")
            subject?.let {
                +"("
                it(this)
                +") "
            }
            block(indentation + 1) {
                conditions.forEach {
                    statement{
                        inlineExpression(it)
                    }
                }
            }
        }
    }
}