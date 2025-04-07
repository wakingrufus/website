package com.github.wakingrufus.website.lib.slides

import com.github.wakingrufus.website.lib.CssBuilderPage
import com.github.wakingrufus.website.testOutputDir
import kotlinx.css.CssBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class SlideshowTest {

    @Test
    fun getFiles() {
        val slideshow = Slideshow(
                baseDir = File(testOutputDir(), "test").apply {
                    mkdir()
                },
                rootCss = CssBuilderPage(p = "style.css", builder = CssBuilder()))
        slideshow.slide(title = "title") {
        }

        val actual = slideshow.files()
        assertEquals(2, actual.size)
        assertEquals("0.html", actual[0].name)
        assertEquals("style.css", actual[1].name)
    }
}