package com.github.wakingrufus.website.lib

import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.p
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.StringWriter

class HtmlPageKtTest {

    @Test
    fun htmlPage() {
        val stringWriter = StringWriter()
        writeHtmlPage(stringWriter) {
            body {
                div {
                    p { +"text inside" }
                }
            }
        }

        assertEquals(
                "<html>\n" +
                        "  <body>\n" +
                        "    <div>\n" +
                        "      <p>text inside</p>\n" +
                        "    </div>\n" +
                        "  </body>\n" +
                        "</html>\n",
                stringWriter.toString())
    }
}