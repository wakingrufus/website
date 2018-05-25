package com.github.wakingrufus.website.lib.code

import kotlinx.html.CODE
import kotlinx.html.HtmlTagMarker

@HtmlTagMarker
class BLOCK(val indentation: Int = 0) {
    var prefix: (CODE.() -> Unit)? = null
    var body: List<CODE.() -> Unit> = ArrayList()
    fun line(block: CODE.() -> Unit) {
        body += {
            line(this@BLOCK.indentation + 1) {
                block(this)
            }
        }
    }

    fun block(block: BLOCK.() -> Unit) {
        body += { BLOCK(indentation = this@BLOCK.indentation + 1).apply(block)(this) }
    }

    fun prefix(block: CODE.() -> Unit) {
        prefix = block
    }

    operator fun invoke(code: CODE) {
        this.let { block ->
            code.apply {
                line(block.indentation) {
                    block.prefix?.let {
                        it(this)
                        +" "
                    }
                    +"{"
                }
                block.body.forEach { it(this) }
                line(block.indentation) { +"}" }
            }
        }
    }
}
