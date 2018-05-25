package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class PARAMETER(val name: String, val type: String?) {
    var value: (CODE.() -> Unit)? = null

    fun value(valueBlock: CODE.() -> Unit) {
        value = valueBlock
    }

    operator fun invoke(code: CODE) {
        code.apply {
            +this@PARAMETER.name
            this@PARAMETER.type?.let {
                +": "
                +it
            }
            this@PARAMETER.value?.let {
                +" = "
                it.invoke(this)
            }
        }
    }
}
