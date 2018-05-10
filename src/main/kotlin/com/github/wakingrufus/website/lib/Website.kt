package com.github.wakingrufus.website.lib

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.lib.slides.Slideshow
import kotlinx.css.*
import kotlinx.html.*
import java.io.File
import java.io.FileWriter
import java.util.*

class Website(private val baseDir: File) {
    private var htmlFiles: List<HtmlPage> = ArrayList()
    private var cssFiles: List<CssPage> = ArrayList()
    private var uploaders: List<Uploader> = ArrayList()
    var slideshows: List<Slideshow> = ArrayList()

    fun cssFile(path: String, builder: CSSBuilder) {
        cssFiles += CssBuilderPage(path, builder)
    }

    fun cssFile(path: String, cssString: String) {
        cssFiles += CssStringPage(path, cssString)
    }

    fun htmlPage(path: String, builder: HTML.() -> Unit) {
        htmlFiles += HtmlPage(path, builder)
    }

    fun slideshow(name: String,
                  rootCss: CssPage,
                  block: Slideshow.() -> Unit) {
        slideshows += Slideshow(
                baseDir = File(baseDir, name).apply { mkdir() },
                rootCss = rootCss).apply(block)
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

    fun upload() = slideshows.flatMap(Slideshow::files).plus(files())
            .forEach({ file -> uploaders.forEach({ u -> u.upload(baseDir,file) }) })
}

fun website(baseDir: File, builder: Website.(baseDir: File) -> Unit): Website {
    return Website(baseDir).apply { builder(baseDir) }
}


fun css(builder: CSSBuilder.() -> Unit): String {
    return CSSBuilder().apply(builder).toString()
}

fun BODY.content(block: DIV.() -> Unit) {
    return div {
        style = css {
            display = Display.inlineBlock
            //    width = 100.pct
        }
        block(this)
    }
}

fun BODY.sideNavBar(block: UL.() -> Unit) {
    return div {
        style = css {
            display = Display.inlineBlock
            paddingRight = 2.em
            borderRightStyle = BorderStyle.solid
            borderRightColor = MyStyles.BORDER_COLOR
            verticalAlign = VerticalAlign.top
        }
        ul {
            style = css {
                listStyleType = ListStyleType.none
                color = MyStyles.LINK_COLOR
            }
            block(this)
        }
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
