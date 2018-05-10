package com.github.wakingrufus.website

import org.junit.Test
import com.github.wakingrufus.website.lib.writeHtmlPage
import java.io.File
import java.io.FileWriter

class MyWebsiteTest {

    @Test
    fun `test mainPage`() {
        writeHtmlPage(FileWriter(File(testOutputDir(), "index.html")), mainPage())
    }
}