package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.lib.WebsiteDsl
import kotlinx.html.CODE

@WebsiteDsl
class LAMBDA(val indentation: Int = 0) {
    var parameters: String? = null
    private var expression: EXPRESSION? = null
    private var block: BLOCK? = null

    fun block(body: BLOCK.() -> Unit) {
        block = BLOCK().apply(body)
        expression = null
    }

    operator fun invoke(code: CODE) {
        parameters?.let {
            code.apply {
                +it
                +" -> "
            }
        }
        block?.invoke(code)
        expression?.invoke(code)
    }
}