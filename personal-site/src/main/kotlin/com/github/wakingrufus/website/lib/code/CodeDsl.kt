package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.lib.css
import kotlinx.css.Color
import kotlinx.css.FontStyle
import kotlinx.css.FontWeight
import kotlinx.css.em
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.TextDecorationLine
import kotlinx.html.*

@HtmlTagMarker
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

fun CODE.it() {
    return span {
        style = css {
            fontWeight = FontWeight("999")
        }
        +"it"
    }
}

fun CODE.string(string: String) {
    return span {
        style = css {
            color = Color("#6A8759")
        }
        +"\""
        +string
        +"\""
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

@HtmlTagMarker
fun CODE.block(indentation: Int = 0, inline: Boolean = false, code: BLOCK.() -> Unit) {
    BLOCK(indentation = indentation, inline = inline).apply(code)(this)
}

@HtmlTagMarker
fun CODE.call(name: String,
              argsOnDifferentLines: Boolean = false,
              baseIndentation: Int = 0,
              block: CALL.() -> Unit = {}) {
    this.apply {
        CALL(name = name,
                argsOnDifferentLines = argsOnDifferentLines,
                baseIndentation = baseIndentation)
                .apply(block)(this)
    }
}

@HtmlTagMarker
fun CODE.constructor(name: String,
                     argsOnDifferentLines: Boolean = false,
                     baseIndentation: Int = 0,
                     block: CALL.() -> Unit) {
    this.apply {
        CALL(name = name, argsOnDifferentLines = argsOnDifferentLines, baseIndentation = baseIndentation)
                .apply(block)(this)
    }
}

@HtmlTagMarker
fun CODE.on(subject: CODE.() -> Unit, block: SUBJECT.() -> Unit = {}) {
    SUBJECT(subject = subject).apply(block)(this)
}
@HtmlTagMarker
fun CODE.on(subject: SUBJECT, block: SUBJECT.() -> Unit = {}) {
    subject.apply(block)(this)
}

fun CODE.plain(text: String) {
    return span {
        style = css {
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

fun CODE.variablePropertyName(text: String) {
    return span {
        style = css {
            fontStyle = FontStyle.italic
            color = Color("#9876AA")
            textDecoration = TextDecoration(lines = setOf(TextDecorationLine.underline),
                    color = Color("#BCA5C4"))
        }
        +text
    }
}

fun CODE.commentLine(text: String) {
    return span {
        style = css {
            color = Color("#808080")
        }
        +"// "
        +text
    }
}

@HtmlTagMarker
fun CODE.declareClass(modifiers: List<String> = emptyList(),
                      name: String,
                      propsOnSeparateLines: Boolean = true,
                      block: CLASS.() -> Unit = {}) {
    CLASS(modifiers = modifiers, name = name, propsOnSeparateLines = propsOnSeparateLines)
            .apply(block)(this)
}

@HtmlTagMarker
fun CODE.dataClass(name: String,
                   propsOnSeparateLines: Boolean = true,
                   block: CLASS.() -> Unit = {}) {
    this.apply {
        CLASS(modifiers = listOf("data"), propsOnSeparateLines = propsOnSeparateLines, name = name)
                .apply(block)(this)
    }
}

@HtmlTagMarker
fun CODE.annotationClass(name: String,
                         propsOnSeparateLines: Boolean = true,
                         block: CLASS.() -> Unit = {}) {
    this.apply {
        CLASS(modifiers = listOf("annotation"), propsOnSeparateLines = propsOnSeparateLines, name = name)
                .apply(block)(this)
    }
}

@HtmlTagMarker
fun CODE.declareFunction(name: String,
                         returnType: String? = null,
                         argsOnSeparateLines: Boolean = false,
                         indentation: Int = 0,
                         extentionOf: String? = null,
                         block: FUNCTION.() -> Unit) : SUBJECT {
    this.apply {
        FUNCTION(name = name,
                returnType = returnType,
                paramsOnSeparateLines = argsOnSeparateLines,
                extensionOf = extentionOf,
                indentation = indentation)
                .apply(block)(this)
        +"\n"
    }
    return SUBJECT(baseIndentation = indentation,subject = {+name})
}

//TODO: fix this up to create the block for you and use EXPRESSION instead of CODE
@HtmlTagMarker
fun CODE.declareFunctionExpression(name: String,
                                   returnType: String? = null,
                                   argsOnSeparateLines: Boolean = true,
                                   parameters: List<PARAMETER> = listOf(),
                                   expression: CODE.() -> Unit) {
    this.apply {
        FUNCTION(name = name, returnType = returnType, paramsOnSeparateLines = argsOnSeparateLines)
                .apply {
                    this.parameters = parameters
                    expression(expression)
                }(this)
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

fun CODE.scope(text: String) {
    return span {
        style = css {
            color = Color("#467CDA")
        }
        +text
    }
}

@HtmlTagMarker
fun CODE.declareProperty(modifier: String? = null, name: String, type: String? = null, value: (CODE.() -> Unit)? = null) {
    PROPERTY(modifier = modifier, name = name, type = type)
            .apply { value?.let { value(it) } }
            .invoke(this)
}

fun CODE.indent(level: Int) {
    +"  ".repeat(level)
}
