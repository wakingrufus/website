package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.slides.Slideshow
import org.junit.Test
import java.io.File

class MainKtTest {

    @Test
    fun `test website`() {
        MyWebsite().build(testOutputDir()).apply {
            files()
            slideshows.map(Slideshow::files)
        }
    }
}

fun testOutputDir(): File {
    val d = File(File("build"), "output")
    if (!d.exists()) d.mkdir()
    return d
}