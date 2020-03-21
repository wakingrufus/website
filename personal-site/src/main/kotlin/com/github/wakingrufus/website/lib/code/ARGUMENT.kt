package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class ARGUMENT(val name: String?, val valueBlock: CODE.() -> Unit) {

    operator fun invoke(code: CODE) {
        code.apply {
            this@ARGUMENT.name?.let {
                parameterName(it)
                parameterName(" = ")
            }
            code.apply(this@ARGUMENT.valueBlock)
        }
    }
}