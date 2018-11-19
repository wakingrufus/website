package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class CLASS(val modifiers: List<String> = emptyList(),
            val name: String,
            val superClass: String? = null,
            val propsOnSeparateLines: Boolean = true) {

    var constructorProperties: List<PROPERTY> = ArrayList()
    var properties: List<PROPERTY> = ArrayList()
    var functions: List<FUNCTION> = ArrayList()
    var companionObject: (CODE.() -> Unit)? = null

    fun value(name: String, type: String, inConstructor: Boolean = true, valueBlock: (CODE.() -> Unit)? = null) {
        property(modifier = "val", name = name, type = type, inConstructor = inConstructor, valueBlock = valueBlock)
    }

    fun variable(name: String, type: String, inConstructor: Boolean = true, valueBlock: (CODE.() -> Unit)? = null) {
        property(modifier = "var", name = name, type = type, inConstructor = inConstructor, valueBlock = valueBlock)
    }

    fun property(modifier: String?, name: String, type: String, inConstructor: Boolean = true, valueBlock: (CODE.() -> Unit)? = null) {
        if (inConstructor) {
            constructorProperties += PROPERTY(modifier = modifier, name = name, type = type).apply { valueBlock?.let { value(it) } }
        } else {
            properties += PROPERTY(modifier = modifier, name = name, type = type).apply { valueBlock?.let { value(it) } }
        }
    }

    fun function(operator: Boolean = false,
                 name: String,
                 returnType: String? = null,
                 paramsOnSeparateLines: Boolean = true,
                 block: FUNCTION.() -> Unit) {
        functions += FUNCTION(
                operator = operator,
                name = name,
                returnType = returnType,
                paramsOnSeparateLines = paramsOnSeparateLines,
                indentation = 1)
                .apply(block)
    }

    fun companionObject(objectBlock: BLOCK.() -> Unit) {
        companionObject = { BLOCK(indentation = 1, inline = false).apply(objectBlock)(this) }
    }

    operator fun invoke(code: CODE) {
        this.let {
            code.apply {
                it.modifiers.plus("class").forEach {
                    keyword("$it ")
                }
                +it.name
                +"("
                it.constructorProperties.forEach {
                    if (this@CLASS.propsOnSeparateLines) {
                        +"\n"
                        indent(2)
                    } else {
                        +" "
                    }
                    it(this)
                }
                +") "
                it.superClass?.let {
                    +": "
                    +it
                }

                if (it.companionObject != null || it.functions.isNotEmpty() || it.properties.isNotEmpty()) {
                    +"{\n"
                    it.companionObject?.let {
                        indent(1)
                        keyword("companion object")
                        +" : "
                        it(this)
                        +"\n"
                    }
                    it.properties.forEach {
                        line(1){
                            it(this)
                        }
                    }
                    it.functions.forEach {
                        it(this)
                        +"\n"
                    }
                    +"}"
                }
                +"\n\n"
            }
        }

    }
}