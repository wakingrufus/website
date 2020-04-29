package com.github.wakingrufus.website.lib

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.WebsiteDsl
import com.github.wakingrufus.website.lib.dashboard.Dashboard
import com.github.wakingrufus.website.lib.slides.Slideshow
import com.rometools.rome.feed.synd.SyndFeed
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.util.data.MutableDataSet
import kotlinx.css.*
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
    private var rssFeeds: List<RssFeed> = ArrayList()
    var slideshows: List<Slideshow> = ArrayList()

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

    fun upload() = (slideshows.flatMap(Slideshow::files).plus(files())).also { allFiles ->
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

fun DIV.dashboard(dash: Dashboard.() -> Unit) {
    return div {
        style = css {
            paddingLeft = 1.em
            paddingRight = 1.em
        }
        Dashboard().apply(dash)(this)
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