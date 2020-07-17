package com.github.wakingrufus.generationq

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class MainKtTest {

    @Test
    fun `test website`() {
        MyWebsite().build(testOutputDir()).apply {
            files()
        }
    }

    @Test
    @Disabled("used to check functionality that talks to neocities. needs api key")
    fun `test neocitiesCheck`() {
        MyWebsite().build(testOutputDir()).run {
            upload(NeocitiesTestUploader(""))
            upload()
        }
    }
}

fun testOutputDir(): File {
    val d = File(File("build"), "output")
    if (!d.exists()) d.mkdir()
    return d
}