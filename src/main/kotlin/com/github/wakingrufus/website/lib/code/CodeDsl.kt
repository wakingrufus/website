package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.lib.css
import kotlinx.css.Color
import kotlinx.css.FontStyle
import kotlinx.css.FontWeight
import kotlinx.css.em
import kotlinx.html.*

@DslMarker
annotation class CodeDsl

fun DIV.sampleCode(block: CODE.() -> Unit) {
    return pre {
        code {
            style = css {
                fontSize = 1.2.em
                fontWeight = FontWeight.bold
            }
            block(this)
        }
    }
}

fun CODE.keyword(text: String) {
    return span {
        style = css {
            color = Color("#CC7832")
        }
        +text
    }
}

fun CODE.number(value: Number) {
    return span {

        style = css {
            color = Color("#6897BB")
        }
        +value.toString()
    }
}

fun CODE.line(indentation: Int = 0, code: CODE.() -> Unit) {
    indent(indentation)
    code.invoke(this)
    +"\n"
}

fun CODE.call(name: String, baseIndentation: Int = 0, block: CALL.() -> Unit) {
    this.apply { CALL(name = name, baseIndentation = baseIndentation).apply(block)(this) }
}

fun CODE.functionName(text: String) {
    return span {
        style = css {
            color = Color("#FFC66D")
        }
        +text
    }
}

fun CODE.propertyName(text: String) {
    return span {
        style = css {
            fontStyle = FontStyle.italic
            color = Color("#9876AA")
        }
        +text
    }
}

fun CODE.comment(text: String) {
    return span {
        style = css {
            color = Color("#808080")
        }
        +text
    }
}

fun CODE.declareClass(modifiers: List<String> = emptyList(),
                      name: String,
                      superClass: String? = null,
                      block: CLASS.() -> Unit = {}) {
    CLASS(modifiers = modifiers, name = name, superClass = superClass)
            .apply(block)(this)
}

fun CODE.dataClass(name: String,
                   superClass: String? = null,
                   block: CLASS.() -> Unit = {}) {
    this.apply {
        CLASS(modifiers = listOf("data"), name = name, superClass = superClass)
                .apply(block)(this)
    }
}

fun CODE.declareFunction(name: String,
                         returnType: String? = null,
                         argsOnSeparateLines: Boolean = true,
                         block: FUNCTION.() -> Unit) {
    this.apply {
        FUNCTION(name = name, returnType = returnType, paramsOnSeparateLines = argsOnSeparateLines)
                .apply(block)(this)
    }
}

fun CODE.parameterName(text: String) {
    return span {
        style = css {
            color = Color("#467CDA")
        }
        +text
    }
}

fun CODE.declareReturn(block: CODE.() -> Unit) {
    this.apply {
        keyword("return ")
        block.invoke(this)
    }

}

fun CODE.declareProperty(modifier: String? = null, name: String, type: String? = null, value: (CODE.() -> Unit)? = null) {
    PROPERTY(modifier = modifier, name = name, type = type)
            .apply { value?.let { value(it) } }
            .invoke(this)
}

fun CODE.indent(level: Int) {
    +"  ".repeat(level)
}

@CodeDsl
class CALL(val name: String, val baseIndentation: Int = 0) {
    var arguments: List<ARGUMENT> = ArrayList()

    fun argument(name: String, value: (CODE.() -> Unit)) {
        arguments += ARGUMENT(name = name, valueBlock = value)
    }

    operator fun invoke(code: CODE) {
        code.apply {
            +name
            +"("
            arguments.forEachIndexed { i, a ->
                +"\n"
                indent(baseIndentation + 1)
                a(this)
                if (i < arguments.size - 1) {
                    +","
                }
            }
            +")"
        }
    }
}

@CodeDsl
class ARGUMENT(val name: String, val valueBlock: CODE.() -> Unit) {

    operator fun invoke(code: CODE) {
        code.apply {
            parameterName(name)
            parameterName(" = ")
            code.apply(valueBlock)
        }
    }
}

@CodeDsl
class PARAMETER(val name: String, val type: String?) {
    var value: (CODE.() -> Unit)? = null

    fun value(valueBlock: CODE.() -> Unit) {
        value = valueBlock
    }

    operator fun invoke(code: CODE) {
        code.apply {
            +name
            type?.let {
                +": "
                +type
            }
            value?.let {
                +" = "
                it.invoke(this)
            }
        }
    }
}

@CodeDsl
class PROPERTY(val modifier: String? = null,
               val name: String,
               val type: String?) {
    var value: (CODE.() -> Unit)? = null

    fun value(valueBlock: CODE.() -> Unit) {
        value = valueBlock
    }

    operator fun invoke(code: CODE) {
        code.apply {
            modifier?.let {
                keyword(modifier)
                +" "
            }
            propertyName(name)
            type?.let {
                +": "
                +type
            }
            value?.let {
                +" = "
                it.invoke(this)
            }
        }
    }
}

@CodeDsl
class FUNCTION(val name: String,
               val returnType: String? = null,
               val indentation: Int = 0,
               val paramsOnSeparateLines: Boolean = true) {
    var parameters: List<PARAMETER> = ArrayList()
    var body: (CODE.() -> Unit)? = null
    var returnStatement: (CODE.() -> Unit)? = null

    fun parameter(name: String, type: String?, value: (CODE.() -> Unit)? = null) {
        parameters += PARAMETER(name = name, type = type).apply { value?.let { value(it) } }
    }

    fun body(bodyText: CODE.() -> Unit) {
        body = bodyText
    }

    fun returnStatement(block: CODE.() -> Unit) {
        returnStatement = block
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
            +"{  "
            body?.let {
                +"\n"
                it.invoke(this)
            }
            returnStatement?.let {
                +"\n"
                line {
                    indent(1)
                    declareReturn(it)
                }
            }
            indent(indentation)
            +"}\n"
        }
    }
}

@CodeDsl
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
        code.apply {
            modifiers.plus("class").forEach({
                keyword("$it ")
            })
            +name
            +"("
            properties.forEach({
                +"\n"
                indent(1)
                it(this)
            })
            +") "
            superClass?.let {
                +": "
                +it
            }
            +"{\n"
            companionObject?.let {
                indent(1)
                keyword("companion object")
                +" : "
                it(this)
            }
            functions.forEach {
                it(this)
            }
            +"}\n\n"
        }
    }
}