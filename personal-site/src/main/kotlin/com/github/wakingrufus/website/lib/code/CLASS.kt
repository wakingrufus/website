package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class CLASS(val modifiers: List<String> = emptyList(),
            val name: String,
            val propsOnSeparateLines: Boolean = true) {
    // TODO: class annotations
    private var annotation: String? = null
    var superClass: String? = null
    var constructorProperties: List<PROPERTY> = ArrayList()
    var properties: List<PROPERTY> = ArrayList()
    private var functions: List<FUNCTION> = ArrayList()
    var companionObject: (CODE.() -> Unit)? = null
    var genericTypes: List<String> = ArrayList()

    fun annotation(annotation: String){
        this.annotation = annotation
    }

    fun value(name: String, type: String, inConstructor: Boolean = true, valueBlock: (CODE.() -> Unit)? = null) {
        property(modifier = "val", name = name, type = type, inConstructor = inConstructor, valueBlock = valueBlock)
    }

    fun variable(name: String, type: String, inConstructor: Boolean = true, valueBlock: (CODE.() -> Unit)? = null) {
        property(modifier = "var", name = name, type = type, inConstructor = inConstructor, valueBlock = valueBlock)
    }

    fun property(modifier: String?, name: String, type: String, inConstructor: Boolean = true, valueBlock: (CODE.() -> Unit)? = null) : TOKEN {
        if (inConstructor) {
            constructorProperties += PROPERTY(modifier = modifier, name = name, type = type).apply { valueBlock?.let { value(it) } }
        } else {
            properties += PROPERTY(modifier = modifier, name = name, type = type).apply { valueBlock?.let { value(it) } }
        }
        return TOKEN(
                tokenName = name,
                format = when {
                    modifier == null -> CODE::plain
                    modifier.contains("val") -> CODE::propertyName
                    modifier.contains("var") -> CODE::variablePropertyName
                    else -> CODE::plain
                })
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
                it.annotation?.let{
                    keyword("@$it")
                    +"\n"
                }
                it.modifiers.plus("class").forEach {
                    keyword("$it ")
                }
                +it.name
                if(it.genericTypes.isNotEmpty()){
                    +"<"
                    +it.genericTypes.joinToString(", ")
                    +">"
                }
                +"("
                it.constructorProperties.forEach {prop ->
                    if (it.propsOnSeparateLines) {
                        +"\n"
                        indent(2)
                    } else {
                        +" "
                    }
                    prop(this)
                }
                +") "
                it.superClass?.let {
                    +": $it() "
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
                        indent(1)
                        it(this)
                        +"\n"
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