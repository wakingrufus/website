package com.github.wakingrufus.website.lib.slides

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.CssPage
import com.github.wakingrufus.website.lib.writeHtmlPage
import kotlinx.html.*
import mu.KLogging
import java.io.File
import java.io.FileWriter

class Slideshow(val baseDir: File,
                val rootCss: CssPage) {
    companion object : KLogging()

    var slides: List<Slide> = ArrayList()

    fun files(): List<File> {
        return slides.mapIndexed { index, slide ->
            File(baseDir, "$index.html").apply {
                logger.info("creating file: " + this.canonicalPath)
                writeHtmlPage(FileWriter(this),
                        slide.template(rootCss.path, slide.title, slide.subTitle, index, slides.size, slide.content))
            }
        }.plus(
                File(baseDir, rootCss.path).apply {
                    rootCss.write(FileWriter(this))
                }
        )
    }

    fun slide(title: String, subTitle: String? = null, block: DIV.() -> Unit) {
        slides += Slide(title = title, subTitle = subTitle, template = ::slideTemplate, content = block)
    }

    fun titleSlide(title: String, subTitle: String? = null, block: DIV.() -> Unit) {
        slides += Slide(title = title,
                subTitle = subTitle,
                template = ::slideshowTitleSlide,
                content = block)
    }
}

fun slideshowTitleSlide(mainCssPath: String,
                        title: String,
                        subTitle: String? = null,
                        slideNumber: Int,
                        totalSlides: Int,
                        content: DIV.() -> Unit): HTML.() -> Unit = {
    head {
        link(href = mainCssPath, rel = "stylesheet")
        title { +title }
    }
    body {
        slide {
            slideshowTitle { +title }
            subTitle?.let {
                slideshowSubTitle { +it }
            }
            this.apply(content)
            div {
                a(href = if (slideNumber > 0) (slideNumber - 1).toString() + ".html" else "../" + Paths.INDEX_PATH) {
                    tabIndex = "2"
                    +"Back"
                }
                +" "
                a(href = "../" + Paths.INDEX_PATH) {
                    tabIndex = "3"
                    +"Home"
                }
                +" "
                a(href = if (slideNumber < totalSlides - 1) "${slideNumber + 1}.html" else "../" + Paths.INDEX_PATH) {
                    tabIndex = "1"
                    +"Next"
                }
            }
        }
    }
}


fun slideTemplate(mainCssPath: String,
                  title: String,
                  subTitle: String? = null,
                  slideNumber: Int,
                  totalSlides: Int,
                  content: DIV.() -> Unit): HTML.() -> Unit = {
    head {
        link(href = mainCssPath, rel = "stylesheet")
        meta { charset = "UTF-8" }
        title { +title }
    }
    body {
        slide {
            slideTitle { +title }
            slideSubTitle { +subTitle.orEmpty() }
            this.apply(content)
            div {
                a(href = if (slideNumber > 0) (slideNumber - 1).toString() + ".html" else "../" + Paths.INDEX_PATH) {
                    tabIndex = "2"
                    +"Back"
                }
                +" "
                a(href = "../" + Paths.INDEX_PATH) {
                    tabIndex = "3"
                    +"Home"
                }
                +" "
                a(href = if (slideNumber < totalSlides - 1) "${slideNumber + 1}.html" else "../" + Paths.INDEX_PATH) {
                    tabIndex = "1"
                    +"Next"
                }
            }
        }
    }
}