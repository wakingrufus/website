package com.github.wakingrufus.website.lib

import kotlinx.css.CssBuilder
import java.io.Writer

sealed class CssPage(val path: String) {
    abstract fun write(writer: Writer)
}

fun writeCssString(writer: Writer, cssString: String) {
    writer.use {
        it.write(cssString)
    }
}

class CssBuilderPage(val p: String, val builder: CssBuilder) : CssPage(path = p) {
    override fun write(writer: Writer) = writeCssString(writer, builder.toString())
}

class CssStringPage(val p: String, val builder: String) : CssPage(path = p) {
    override fun write(writer: Writer) = writeCssString(writer, builder)
}