package com.github.wakingrufus.generationq

import kotlinx.css.*
import kotlinx.html.*
import java.awt.Image.SCALE_SMOOTH
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.*
import javax.imageio.ImageIO


class Episode(val title: String, val url: String) {
    var discussionUrl: String? = null
    var imageName: String? = null
    private var description: DIV.() -> Unit = {}
    fun description(builder: DIV.() -> Unit) {
        this.description = builder
    }

    fun resize(url: URL, width: Int): ByteArray {
        val original = ImageIO.read(url)
        val targetHeight = original.height / (original.width / width)
        val resizedImage = original.getScaledInstance(width, targetHeight, SCALE_SMOOTH)
        val outputImage = BufferedImage(width, targetHeight, BufferedImage.TYPE_INT_RGB)
        outputImage.graphics.drawImage(resizedImage, 0, 0, null)
        val os = ByteArrayOutputStream()
        ImageIO.write(outputImage, "PNG", os)
        return os.toByteArray()
    }

    operator fun invoke(div: DIV) {
        div.apply {
            div {
                style = css {
                    backgroundColor = MyStyles.LIGHT_BACKGROUND_COLOR
                    paddingTop = 0.5.em
                    paddingLeft = 0.5.em
                    paddingBottom = 0.5.em
                    paddingRight = 0.5.em
                    borderColor = Color.white
                    borderWidth = 2.px
                    borderStyle = BorderStyle.solid
                    display = Display.flex
                    flexDirection = FlexDirection.row
                }
                div {
                    style = css {
                        alignSelf = Align.center
                        display = Display.inlineBlock
                        flexBasis = FlexBasis("200px")
                    }
                    imageName
                            ?.let { this::class.java.classLoader.getResource(it) }
                            ?.also {
                                img(src = "data:image/png;base64,"
                                        + String(Base64.getEncoder().encode(resize(it, 200)))) {
                                    style = css {
                                        display = Display.inlineBlock
                                        maxWidth = 200.px
                                    }
                                }
                            }
                }
                div {
                    style = css {
                        display = Display.block
                        marginLeft = 0.5.em
                        paddingLeft = 0.5.em
                    }
                    h2 {
                        style = css {
                            marginTop = 0.px
                        }
                        +this@Episode.title
                    }
                    description()
                    p {
                        a(href = url) { +"MP3" }
                    }
                    p {
                        a(href = discussionUrl) { +"Discuss this episode" }
                    }
                    p {
                        span {
                            a(href = "https://www.deviantart.com/benttibisson/art/Star-Trek-the-Next-Generation-painting-617241159") {
                                +"Artwork: Bentti Bisson"
                            }
                        }
                        span {
                            +" - "
                        }
                        span {
                            a(href = "https://soundcloud.com/nes-house") {
                                +"Intro Music: NES House - Star Trek the next generation House"
                            }
                        }
                        span {
                            +" - "
                        }
                        span {
                            a(href = "https://soundcloud.com/aeop") {
                                +"Outro Music: Aeop - A308 - What Do You Want Will Riker"
                            }
                        }
                    }
                }
            }
        }
    }
}