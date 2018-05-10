package com.github.wakingrufus.website.lib.code

import com.github.wakingrufus.website.lib.css
import kotlinx.css.Color
import kotlinx.css.FontStyle
import kotlinx.html.*


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

fun CODE.property(text: String) {
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

fun CODE.declareFunction(name: String,
                         args: String? = null,
                         returnType: String? = null,
                         block: CODE.() -> Unit) {
    this.apply {
        keyword("fun")
        +" "
        functionName(name)
        args?.let {
            +"("
            +it
            +")"
        }
        returnType?.let {
            +" : "
            +it
        }
        +"{\n  "
        block.invoke(this)
        +"\n}"
    }

}

fun CODE.declareReturn(block: CODE.() -> Unit) {
    this.apply {
        keyword("return ")
        block.invoke(this)
    }

}

fun CODE.declareVal(name: String, assignment: String) {
    this.apply {
        keyword("val")
        +" "
        property(name)
        +assignment
    }

}

/*
fun DIV.sampleKotlinCode(block: CodeSample.() -> Unit) {
    return pre {
        code {
            text(block)
        }
    }
}
        */