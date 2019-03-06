package com.github.wakingrufus.website.slideshows

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.CssStringPage
import com.github.wakingrufus.website.lib.Website
import com.github.wakingrufus.website.lib.code.*
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
        slide(title = "Disadvantages", block = staticWebDisadvantages())
        slide(title = "Generators", block = staticWebGenerators())
        slide(title = "Lambda as Final Parameter", block = lambdaAsFinalParameter())
        slide(title = "Lambda with Receiver", block = lambdaWithReceiver())
        slide(title = "DslMarker", block = dslMarker())
        slide(title = "HTML DSL", block = staticWebKotlinHtml())
        slide(title = "CSS DSL", block = staticWebKotlinCss())
        slide(title = "Code Reuse", block = staticWebKotlinReuse())
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
            li { +"Speed: Fast loading times and rendering" }
            li { +"Portability: Can be served by any host" }
            li { +"Scalability: " }
            li { +"Accessibility: Screen readers and printing" }
            li { +"Simplicity/Maintenance" }
        }
    }
}

fun staticWebDisadvantages(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Verbose" }
            li { +"Duplication" }
            li { +"Extensions (RSS, etc)" }
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

fun lambdaAsFinalParameter(): DIV.() -> Unit = {
    slideContent {
        splitSlide(leftBlock = {
            slideCode {
                declareFunction("f") {
                    parameter("lambda", type = "(Int) -> String")
                }
                declareProperty(modifier = "val", name = "s") {
                    call("f") {
                        argument(name = "lambda") {
                            block {
                                expression {
                                    on({ it() }) {
                                        call(name = "toString")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }) {
            slideCode {
                declareProperty(modifier = "val", name = "s") {
                    call("f") {
                        lambda {
                            expression {
                                on({ it() }) {
                                    call(name = "toString")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun lambdaWithReceiver(): DIV.() -> Unit = {
    slideContent {
        slideCode {
            declareFunction("builder", "String", false) {
                body {
                    returns {
                        on({
                            call("StringBuilder")
                        }) {
                            call(name = "append") {
                                argument {
                                    string("content")
                                }
                            }
                            call("build")
                        }
                    }
                }
            }
            declareFunction("stringDsl", "String", false) {
                parameter(name = "receiver", type = "StringBuilder.() -> Unit")
                body {
                    expression {
                        on({
                            call("StringBuilder") {}
                        }) {
                            call(name = "apply") {
                                extensionFunction()
                                argument { +"receiver" }
                            }
                            call("build")
                        }
                    }
                }
            }
            declareFunction("dsl", "String", false) {
                expression {
                    on({
                        call(name = "stringDsl") {
                            packageFunction()
                            lambda(inline = false) {
                                expression {
                                    on({
                                        call("append") {
                                            argument {
                                                string("content")
                                            }
                                        }
                                    })
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}

fun dslMarker(): DIV.() -> Unit = {
    slideContent {
        splitSlide(leftBlock = {
            slideCode {
                keyword("@DslMarker")
                +"\n"
                annotationClass("HtmlTagMarker")
            }
        }) {

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
        splitSlide(leftBlock = {
            slideCode {
                declareFunction(name = "sideNavBar", extentionOf = "BODY") {
                    parameter(name = "block", type = "UL.() -> Unit")
                    expression {
                        call("div") {
                            lambda {
                                assignment(name = "classes", operator = "+=") {
                                    inlineExpression { string("navBar") }
                                }
                                assignment(name = "style") {
                                    call("css") {
                                        lambda {
                                            assignment(name = "verticalAlign") {
                                                inlineExpression {
                                                    +"VerticalAlign.top"
                                                }
                                            }
                                        }
                                    }
                                }
                                statement {
                                    call("ul") {
                                        lambda {
                                            assignment(name = "style") {
                                                call(name = "css") {
                                                    lambda {
                                                        assignment(name = "listStyleType") {
                                                            inlineExpression {
                                                                +"ListStyleType.none"
                                                            }
                                                        }
                                                        assignment(name = "color") {
                                                            call(name = "Color") {
                                                                argument { string("#9999EE") }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            statement {
                                                call(name = "block") {
                                                    argument { keyword("this") }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }) {
            slideCode {
                declareFunction(name = "sideNav", extentionOf = "BODY") {
                    body {
                        call(name = "apply") {
                            lambda {
                                call(name = "sideNavBar") {
                                    lambda {
                                        call(name = "li") {
                                            lambda(inline = true) {
                                                call(name = "a") {
                                                    argument(name = "href") { string("index.html") }
                                                    lambda(inline = true) {
                                                        statement {
                                                            inlineExpression {
                                                                +"+"
                                                                string("Home")
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        call(name = "li") {
                                            lambda(inline = true) {
                                                call(name = "a") {
                                                    argument(name = "href") { string("travel.html") }
                                                    lambda(inline = true) {
                                                        statement {
                                                            inlineExpression {
                                                                +"+"
                                                                string("Travel Guide")
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                declareFunction(name = "mainPage", returnType = "HTML.() -> Unit") {
                    expression {
                        block {
                            call(name = "head") {
                                lambda { }
                            }
                            call(name = "body") {
                                lambda {
                                    expression {
                                        call(name = "sideNav") {
                                            extensionFunction()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}