package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.writeHtmlPage
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileWriter

class MyWebsiteTest {

    @Test
    fun `test mainPage`() {
        writeHtmlPage(FileWriter(File(testOutputDir(), "index.html")), mainPage())
    }
}