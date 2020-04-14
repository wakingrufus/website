package com.github.wakingrufus.website.lib.article

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet
import kotlinx.html.DIV
import kotlinx.html.unsafe
import java.nio.charset.Charset

sealed class Section(open val content: DIV.() -> Unit)
class MarkdownSection(val sourcePath: String) : Section({
    this.unsafe {
        raw(HtmlRenderer.builder(MutableDataSet()).build()
                .render(Parser.builder(MutableDataSet()).build()
                        .parse(this::class.java.classLoader.getResourceAsStream(sourcePath)?.readBytes()?.toString(Charset.defaultCharset()))))
    }
})

class HtmlSection(override val content: DIV.() -> Unit) : Section(content)