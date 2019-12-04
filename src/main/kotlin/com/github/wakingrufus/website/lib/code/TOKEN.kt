package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.lib.WebsiteDsl
import kotlinx.html.CODE

@WebsiteDsl
class TOKEN(val tokenName: String, val baseIndentation: Int = 0, val format: CODE.(String) -> Unit = CODE::plain) {
    fun call(callName: String,
             argsOnDifferentLines: Boolean = false,
             baseIndentation: Int = 0,
             nullSafe: Boolean = false,
             block: CALL.() -> Unit = {}): CODE.() -> Unit = {
        on({ format(tokenName) }) {
            call(callName, argsOnDifferentLines, baseIndentation, nullSafe, block)
        }
    }
}