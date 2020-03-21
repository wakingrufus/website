package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class PROPERTY(val modifier: String? = null,
               val name: String,
               val type: String?) {
    var value: (CODE.() -> Unit)? = null
    var delegate : CALL? = null

    fun value(valueBlock: CODE.() -> Unit) {
        value = valueBlock
    }

    fun delegate(name: String, call: CALL.() -> Unit){
       delegate = CALL(name).apply(call)
    }

    operator fun invoke(code: CODE) {
        this.let { property ->
            code.apply {
                property.modifier?.let {
                    keyword(property.modifier)
                    +" "
                }
                propertyName(property.name)
                property.type?.let {
                    +": "
                    +it
                }
                property.value?.let {
                    +" = "
                    it.invoke(this)
                }
                property.delegate?.let{
                    this.propertyName(" by ")
                 it.invoke(this)
                }
            }
        }
    }
}
