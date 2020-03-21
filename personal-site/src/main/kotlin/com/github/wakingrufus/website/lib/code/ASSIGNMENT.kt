package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE

class ASSIGNMENT(val modifier: String? = null,
                 var name: String,
                 val type: String? = null,
                 var format: CODE.(String) -> Unit = CODE::propertyName,
                 var operator: String = "=") {
    operator fun invoke(code: CODE) {
        code.apply {
            modifier?.let {
                keyword(modifier)
                +" "
            }
            format(name)
            type?.let {
                +": "
                +it
            }
            +" $operator "
        }
    }

}