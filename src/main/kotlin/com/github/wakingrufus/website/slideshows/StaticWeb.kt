package com.github.wakingrufus.website.slideshows

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.CssStringPage
import com.github.wakingrufus.website.lib.Website
import com.github.wakingrufus.website.lib.code.call
import com.github.wakingrufus.website.lib.code.number
import com.github.wakingrufus.website.lib.code.string
import com.github.wakingrufus.website.lib.code.variablePropertyName
import com.github.wakingrufus.website.lib.css
import com.github.wakingrufus.website.lib.slides.*
import kotlinx.css.Color
import kotlinx.css.em
import kotlinx.html.*


fun staticWebSlideshow(): Website.() -> Unit = {
    slideshow(
            name = Paths.STATIC_WEB_SLIDESHOW_BASE_NAME,
            rootCss = CssStringPage(Paths.SLIDESHOW_CSS_PATH, MyStyles().slideShowStyles())) {
        titleSlide(title = "Static Web", subTitle = "With Kotlin and DSLs", block = staticWebTitleSlide())
        slide(title = "Documents, not Apps", block = staticWebNotApps())
        slide(title = "Advantages", block = staticWebAdvantages())
        slide(title = "Generators", block = staticWebGenerators())
        slide(title = "HTML DSL", block = staticWebKotlinHtml())
        slide(title = "Kotlin to the rescue", block = staticWebKotlinCss())
    }
}


fun staticWebTitleSlide(): DIV.() -> Unit = {
    slideshowTitleFooter {
        slideshowTitleFooterLine { +"John Burns" }
        slideshowTitleFooterLine { +"wakingrufus@gmail.com" }
        slideshowTitleFooterLine {
            a(href = "http://wakingrufus.neocities.org") { +"http://wakingrufus.neocities.org" }
        }
    }

}

fun staticWebNotApps(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Web 2.0 is a hack" }
            li { +"When all you have is a hammer..." }
            li { +"Many websites have no business being webapps" }
        }
    }
}


fun staticWebAdvantages(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Security" }
            li { +"Reliability" }
            li { +"Speed" }
            li { +"Hosting" }
            li { +"Scalability" }
        }
    }
}

fun staticWebDisadvantages(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Verbose" }
            li { +"Duplication" }
        }
    }
}

fun staticWebGenerators(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Jekyll (GH pages)" }
            li { +"Hugo" }
            li { +"Concise and reusable elements" }
            li { +"Limited features" }
        }
    }
}

fun staticWebKotlinHtml(): DIV.() -> Unit = {
    slideContent {
        splitSlide(leftBlock = {
            slideCode {
                call("h1") {
                    extensionFunction()
                    lambda(inline = false, indentation = 0) {

                        expression {
                            +"+"
                            string("Header")
                        }
                    }
                }
                +"\n"
                call("p") {
                    extensionFunction()
                    lambda(inline = false) {
                        expression {
                            +"+"
                            string("Paragraph with link: ")
                        }
                        expression {
                            call("a", baseIndentation = 1) {
                                extensionFunction()
                                argument(name = "href") {
                                    string("http://www.duckduckgo.com")
                                }
                                lambda {
                                    expression {
                                        +"+"
                                        string("DDG")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }) {
            h1 { +"Header" }
            p {
                +"Paragraph with link: "
                a(href = "http://www.duckduckgo.com") {
                    +"DDG"
                }
            }
        }
    }

}


fun staticWebKotlinCss(): DIV.() -> Unit = {
    slideContent {
        splitSlide(leftBlock = {
            slideCode {
                call("p") {
                    extensionFunction()
                    lambda(inline = false, indentation = 0) {
                        assignment(name = "style", format = CODE::variablePropertyName) {
                            call("css", baseIndentation = 1) {
                                lambda {
                                    assignment(name = "fontSize", format = CODE::variablePropertyName) {
                                        on(subject = {
                                            number(2)
                                        }) {
                                            property("em")
                                        }
                                    }
                                }
                            }
                        }
                        expression {
                            +"+"
                            string("Paragraph")
                        }
                    }
                }
            }
        }) {
            p {
                style = css {
                    fontSize = 2.em
                    color = Color.green
                }
                +"Paragraph"
            }
        }
    }
}

fun staticWebKotlinReuse(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"html dsl" }
            li { +"css dsl" }
            li { +"resuse via functions" }
            li { +"DSL extensions" }
        }
    }
}

fun staticWebKotlinExtensions(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"html dsl" }
            li { +"css dsl" }
            li { +"resuse via functions" }
            li { +"DSL extensions" }
        }
    }
}