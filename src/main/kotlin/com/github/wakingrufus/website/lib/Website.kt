package com.github.wakingrufus.website.lib

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.lib.slides.Slideshow
import com.rometools.rome.feed.synd.SyndFeed
import kotlinx.css.CSSBuilder
import kotlinx.css.ListStyleType
import kotlinx.css.TextAlign
import kotlinx.css.VerticalAlign
import kotlinx.html.*
import mu.KLogging
import java.io.File
import java.io.FileWriter

class Website(private val baseDir: File) {
    companion object : KLogging()

    private var htmlFiles: List<HtmlPage> = ArrayList()
    private var cssFiles: List<CssPage> = ArrayList()
    private var uploaders: List<Uploader> = ArrayList()
    private var rssFeeds: List<RssFeed> = ArrayList()
    var slideshows: List<Slideshow> = ArrayList()

    fun cssFile(path: String, builder: CSSBuilder) {
        cssFiles += CssBuilderPage(path, builder)
    }

    fun cssFile(path: String, cssString: String) {
        cssFiles += CssStringPage(path, cssString)
    }

    fun htmlPage(path: String, builder: HTML.() -> Unit) {
        htmlFiles += HtmlPage(path).apply { this.builder(builder) }
    }

    fun page(path: String, pageBuilder: HtmlPage.() -> Unit) {
        htmlFiles += HtmlPage(path).apply (pageBuilder)
    }

    fun rssFeed(rssDir: String = "rss", path: String, feedContents: SyndFeed) {
        rssFeeds += RssFeedBuilder(
                file = File(baseDir, rssDir).let {
                    it.mkdir()
                    File(it, path)
                }, builder = feedContents)
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
        }).plus(rssFeeds.map {
            it.file.apply {
                logger.info(this.canonicalPath)
                it.write(FileWriter(it.file))
            }
        })
    }

    fun upload() = slideshows.flatMap(Slideshow::files).plus(files())
            .forEach { file -> uploaders.forEach { u -> u.upload(baseDir, file) } }
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

fun BODY.sideNavBar(block: UL.() -> Unit) = div {
    classes += "navBar"
    style = css {
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

fun BODY.pageTitle(title: String) {
    return h1(classes = "") {
        style = css {
            backgroundColor = MyStyles.BACKGROUND_COLOR
            textAlign = TextAlign.center
        }
        +title
    }
}
