package com.github.wakingrufus.website

import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileWriter

class MyWebsiteTest {

    @Test
    fun `test mainPage`() {
        mainPage.writeHtmlPage(FileWriter(File(testOutputDir(), "index.html")))
    }
}
