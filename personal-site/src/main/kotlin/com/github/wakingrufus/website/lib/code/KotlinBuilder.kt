package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

/**
 * Entry point into the kotlin DSL.
 * Provides the top level constructs in the kotlin language: classes, functions, objects, and values
 */
@HtmlTagMarker
class KotlinBuilder {
    private val statements: MutableList<CODE.() -> Unit> = mutableListOf()
    fun declareClass(modifiers: List<String> = emptyList(),
                     name: String,
                     propsOnSeparateLines: Boolean = true,
                     block: CLASS.() -> Unit = {}) {
        statements += {
            CLASS(modifiers = modifiers, name = name, propsOnSeparateLines = propsOnSeparateLines)
                    .apply(block)(this)
            +"\n"
        }
    }

    fun declareFunction(name: String,
                        returnType: String? = null,
                        argsOnSeparateLines: Boolean = false,
                        indentation: Int = 0,
                        extentionOf: String? = null,
                        block: FUNCTION.() -> Unit): SUBJECT {
        statements += {
            FUNCTION(name = name,
                    returnType = returnType,
                    paramsOnSeparateLines = argsOnSeparateLines,
                    extensionOf = extentionOf,
                    indentation = indentation)
                    .apply(block)(this)
            +"\n"
        }
        return SUBJECT(baseIndentation = indentation, subject = { +name })
    }

    fun dataClass(name: String,
                  propsOnSeparateLines: Boolean = true,
                  block: CLASS.() -> Unit = {}) {
        statements += {
            CLASS(modifiers = listOf("data"), propsOnSeparateLines = propsOnSeparateLines, name = name)
                    .apply(block)(this)
            +"\n"
        }
    }

    fun annotationClass(name: String,
                        propsOnSeparateLines: Boolean = true,
                        block: CLASS.() -> Unit = {}) {
        statements += {
            CLASS(modifiers = listOf("annotation"), propsOnSeparateLines = propsOnSeparateLines, name = name)
                    .apply(block)(this)
            +"\n"
        }
    }

    fun statement(block: STATEMENT.() -> Unit) {
        statements += { STATEMENT().apply(block)(this) }
    }

    fun subjectStatement(block: STATEMENT.() -> SUBJECT) : SUBJECT {
        val statement = STATEMENT()
        val subject = statement.let {
            block(it)
        }
        statements += { statement(this) }
        return subject
    }

    //TODO: add object support

    operator fun invoke(code: CODE) {
        code.apply {
            this@KotlinBuilder.statements.forEach {
                this.apply(it)
            }
        }
    }
}

@HtmlTagMarker
fun CODE.kotlin(block: KotlinBuilder.() -> Unit) {
    KotlinBuilder().apply(block)(this)
}