package com.github.wakingrufus.website.lib.code

import kotlinx.html.body
import kotlinx.html.code
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringWriter

internal class STATEMENTTest {
    @Test
    fun `test inlineStatement`() {
        val instance = STATEMENT()
        instance.inlineExpression {
            +"test"
        }
        val html = StringWriter().appendHTML().html {
            body {
                code {
                    instance(this)
                }
            }
        }
        assertThat(html.toString()).isEqualTo("""<html>
            |  <body><code>test</code></body>
            |</html>
            |""".trimMargin())
    }

    @Test
    fun `test returns`() {
        val instance = STATEMENT()
        instance.returns {
            on({
                +"test"
            }) {}
        }
        val html = StringWriter().appendHTML().html {
            body {
                code {
                    instance(this)
                }
            }
        }
        assertThat(html.toString()).isEqualTo("""<html>
            |  <body><code><span style="color: #CC7832;
">return </span>test</code></body>
            |</html>
            |""".trimMargin())

    }
}