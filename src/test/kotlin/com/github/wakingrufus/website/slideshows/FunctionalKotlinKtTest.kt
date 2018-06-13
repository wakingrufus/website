package com.github.wakingrufus.website.slideshows

import org.junit.Assert.assertEquals
import org.junit.Test

class FunctionalKotlinKtTest {

    @Test
    fun `test sayHello`() {
        val sb = StringBuilder()
        sayHello(printFn = { sb.append(it) },
                readFn = { "input" })
        assertEquals("Hello, input", sb.toString())
    }
}