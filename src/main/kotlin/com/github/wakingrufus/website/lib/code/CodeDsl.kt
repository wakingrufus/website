package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.lib.css
import kotlinx.css.Color
import kotlinx.css.FontStyle
import kotlinx.html.*

@DslMarker
annotation class CodeDsl

fun DIV.sampleCode(block: CODE.() -> Unit) {
    return pre {
        code {
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
    this.apply {
        CLASS(modifiers = modifiers, name = name, superClass = superClass)
                .apply(block)(this)
    }

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
                         block: FUNCTION.() -> Unit) {
    this.apply {
        FUNCTION(name = name, returnType = returnType)
                .apply(block)(this)
    }
}

fun CODE.declareReturn(block: CODE.() -> Unit) {
    this.apply {
        keyword("return ")
        block.invoke(this)
    }

}

fun CODE.declareProperty(modifier: String? = null, name: String, type: String? = null, value: (CODE.() -> Unit)? = null) {
    PROPERTY(modifier = modifier, name = name, type = type, statement = true)
            .apply { value?.let { value(it) } }
            .invoke(this)
}

fun CODE.indent(level: Int) {
    +"  ".repeat(level)
}

@CodeDsl
class PROPERTY(val modifier: String? = null,
               val name: String,
               val type: String?,
               val statement: Boolean = false) {
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
            if (statement) +"\n"
        }
    }
}

@CodeDsl
class FUNCTION(val name: String,
               val returnType: String? = null,
               val indentation: Int = 0) {
    var arguments: List<CODE.() -> Unit> = ArrayList()
    var body: (CODE.() -> Unit)? = null

    fun argument(name: String, type: String, value: (CODE.() -> Unit)? = null) {
        arguments += { PROPERTY(name = name, type = type).apply { value?.let { value(it) } }(this) }
    }

    fun body(bodyText: CODE.() -> Unit) {
        body = bodyText
    }

    operator fun invoke(code: CODE) {
        code.apply {
            indent(indentation)
            keyword("fun")
            +" "
            functionName(name)
            +"("
            arguments.forEachIndexed { i, a ->
                +"\n"
                indent(indentation + 1)
                this.apply(a)
                if (i < arguments.size - 1) {
                    +","
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
                +"\n"
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

    fun property(modifier: String?, name: String, type: String, valueBlock: (CODE.() -> Unit)? = null) {
        properties += PROPERTY(modifier = modifier, name = name, type = type).apply { valueBlock?.let { value(it) } }
    }

    fun function(name: String, returnType: String? = null, block: FUNCTION.() -> Unit) {
        functions += FUNCTION(name = name, returnType = returnType, indentation = 1).apply(block)
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
            functions.forEach {
                it(this)
            }
            +"}\n\n"
        }
    }
}