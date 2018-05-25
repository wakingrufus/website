package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class CLASS(val modifiers: List<String> = emptyList(),
            val name: String,
            val superClass: String? = null) {

    var properties: List<PROPERTY> = ArrayList()
    var functions: List<FUNCTION> = ArrayList()
    var companionObject: (CODE.() -> Unit)? = null

    fun value(name: String, type: String, valueBlock: (CODE.() -> Unit)? = null) {
        properties += PROPERTY(modifier = "val", name = name, type = type).apply { valueBlock?.let { value(it) } }
    }

    fun variable(name: String, type: String, valueBlock: (CODE.() -> Unit)? = null) {
        properties += PROPERTY(modifier = "var", name = name, type = type).apply { valueBlock?.let { value(it) } }
    }

    fun property(modifier: String?, name: String, type: String, valueBlock: (CODE.() -> Unit)? = null) {
        properties += PROPERTY(modifier = modifier, name = name, type = type).apply { valueBlock?.let { value(it) } }
    }

    fun function(name: String, returnType: String? = null, block: FUNCTION.() -> Unit) {
        functions += FUNCTION(name = name, returnType = returnType, indentation = 1).apply(block)
    }

    fun companionObject(objectBlock: CODE.() -> Unit) {
        companionObject = objectBlock
    }

    operator fun invoke(code: CODE) {
        this.let {
            code.apply {
                it.modifiers.plus("class").forEach({
                    keyword("$it ")
                })
                +it.name
                +"("
                it.properties.forEach({
                    +"\n"
                    indent(1)
                    it(this)
                })
                +") "
                it.superClass?.let {
                    +": "
                    +it
                }

                if(it.companionObject != null || it.functions.isNotEmpty()) {
                    +"{\n"
                   it.companionObject?.let {
                        indent(1)
                        keyword("companion object")
                        +" : "
                        it(this)
                    }
                    it.functions.forEach {
                        it(this)
                    }
                    +"}"
                }
                +"\n"
            }
        }

    }
}