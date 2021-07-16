package com.github.wakingrufus.generationq

import com.github.wakingrufus.website.WebsiteDsl
import kotlinx.css.CSSBuilder
import kotlinx.css.TextAlign
import kotlinx.css.backgroundColor
import kotlinx.css.textAlign
import kotlinx.html.*
import mu.KLogging
import java.io.File
import java.io.FileWriter

@WebsiteDsl
class Website(private val baseDir: File) {
    companion object : KLogging()

    private var htmlFiles: List<HtmlPage> = ArrayList()
    private var cssFiles: List<CssPage> = ArrayList()
    private var uploaders: List<Uploader> = ArrayList()

    fun cssFile(path: String, builder: CSSBuilder) {
        cssFiles += CssBuilderPage(path, builder)
    }

    fun cssFile(path: String, cssString: String) {
        cssFiles += CssStringPage(path, cssString)
    }

    fun page(path: String, pageBuilder: HtmlPage.() -> Unit) {
        htmlFiles += HtmlPage(path).apply(pageBuilder)
    }

    fun page(htmlPage: HtmlPage) {
        htmlFiles += htmlPage
    }

    fun upload(uploader: Uploader) {
        uploaders += uploader
    }

    fun files(): List<File> {
        return htmlFiles.map {
            File(baseDir, it.path).apply {
                it.writeHtmlPage(FileWriter(this))
            }

        }.plus(cssFiles.map {
            File(baseDir, it.path).apply {
                it.write(FileWriter(this))
            }
        })
    }

    fun upload() = files().also { allFiles ->
        uploaders.forEach { u ->
            allFiles.filter { u.check(baseDir, it) }.forEach {
                u.upload(baseDir, it)
            }
        }
    }
}

fun website(baseDir: File, builder: Website.(baseDir: File) -> Unit): Website {
    return Website(baseDir).apply { builder(baseDir) }
}


fun css(builder: CSSBuilder.() -> Unit): String {
    return CSSBuilder().apply(builder).toString()
}

fun BODY.content(block: DIV.() -> Unit) {
    return div {
        classes = setOf("page-content")
        block(this)
    }
}


fun BODY.pageTitle(title: String) {
    return h1(classes = "") {
        style = css {
            backgroundColor = MyStyles.BACKGROUND_COLOR
            textAlign = TextAlign.center
        }
        +title
    }
}

fun htmlPage(path: String, builder: HtmlPage.() -> Unit): HtmlPage {
    return HtmlPage(path).apply(builder)
}