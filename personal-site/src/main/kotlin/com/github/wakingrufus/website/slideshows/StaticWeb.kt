package com.github.wakingrufus.website.slideshows

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.CssStringPage
import com.github.wakingrufus.website.lib.Website
import com.github.wakingrufus.website.lib.code.*
import com.github.wakingrufus.website.lib.css
import com.github.wakingrufus.website.lib.slides.*
import kotlinx.css.Color
import kotlinx.css.color
import kotlinx.css.em
import kotlinx.css.fontSize
import kotlinx.html.*

fun staticWebSlideshow(): Website.() -> Unit = {
    slideshow(
            name = Paths.STATIC_WEB_SLIDESHOW_BASE_NAME,
            rootCss = CssStringPage(Paths.SLIDESHOW_CSS_PATH, MyStyles().slideShowStyles())) {
        titleSlide(title = "Static Web", subTitle = "With Kotlin and DSLs", block = staticWebTitleSlide)
        slide(title = "What is DSL?", block = fromOopToDsl)
        slide(title = "Infix functions", block = infixFunction)
        slide(title = "Lambda as Final Parameter", block = lambdaAsFinalParameter)
        slide(title = "Lambda with Receiver", block = lambdaWithReceiver)
        slide(title = "DslMarker", block = dslMarker)
        slide(title = "Documents, not Apps", block = staticWebNotApps)
        slide(title = "Advantages", block = staticWebAdvantages)
        slide(title = "Disadvantages", block = staticWebDisadvantages)
        slide(title = "Generators", block = staticWebGenerators)
        slide(title = "HTML DSL", block = staticWebKotlinHtml)
        slide(title = "CSS DSL", block = staticWebKotlinCss)
        slide(title = "Code Reuse", block = staticWebKotlinReuse)
        //      slide("Custom DSL: Vision") {}
        //    slide("Custom DSL: Before DSL") {}
        slide(title = "Custom DSL: Backing objects", block = backingObjects)
        //  slide("Custom DSL: Top Level Functions") {}
        //  slide("Custom DSL: HTML interpreter") {}
        //  slide("Custom DSL: Usage") {}
        //  slide("Custom DSL: Final output") {}
        slide(title = "Go forth and DSL All the Things", block = theEnd)
    }
}


val staticWebTitleSlide = slide {
    titleFooter {
        slideshowTitleFooterLine { +"John Burns" }
        slideshowTitleFooterLine { +"wakingrufus@gmail.com" }
        slideshowTitleFooterLine {
            a(href = "http://wakingrufus.neocities.org") { +"http://wakingrufus.neocities.org" }
        }
    }
}

val fromOopToDsl = slide {
    slideContent {
        slideList {
            li { +"Domain consists of structured data and interfaces" }
            li { +"Extract common code and higher-order functions into 'interpreter'" }
            li { +"All implementation-specific code and data is now input data to interpreter" }
            li { +"Build backing objects to hold these inputs" }
            li { +"DSLs allow you to build these data in a typesafe way." }
            li { +"Kotlin allows us to build DSLs" }
        }
    }
}

val staticWebNotApps = slide {
    slideContent {
        slideList {
            li { +"Web 2.0 is a hack" }
            li { +"When all you have is a hammer..." }
            li { +"Many websites have no business being webapps" }
        }
    }
}


val staticWebAdvantages = slide {
    slideContent {
        slideList {
            li { +"Security" }
            li { +"Reliability" }
            li { +"Speed/Scalability: Low download sizes, Fast loading times and rendering" }
            li { +"Portability: Can be served by any host" }
            li { +"Accessibility: Screen readers and printing" }
            li { +"Simplicity/Maintenance" }
        }
    }
}

val staticWebDisadvantages = slide {
    slideContent {
        slideList {
            li { +"Verbose" }
            li { +"Duplication" }
            li { +"Extensions (RSS, etc)" }
        }
    }
}

val staticWebGenerators = slide {
    slideContent {
        slideList {
            li { +"Jekyll (GH pages)" }
            li { +"Hugo" }
            li { +"Concise and reusable elements" }
            li { +"Limited features" }
        }
    }
}

val infixFunction = slide {
    slideContent {
        slideList {
            li { +"For simple binary operators" }
            li { +"Eliminate '.()' ceremony for more natural language" }
        }
        slideCode {
            kotlin {
                declareClass(name = "Pair", propsOnSeparateLines = false) {
                    genericTypes = listOf("A", "B")
                    property(modifier = "val", name = "first", type = "A")
                    property(modifier = "val", name = "second", type = "B")
                }
                declareFunction(name = "to", extentionOf = "A", returnType = "Pair<A, B>") {
                    isInfix = true
                    genericType = "A, B"
                    parameter("that", "B")
                    expression {
                        call("Pair") {
                            argument { keyword("this") }
                            argument { +"that" }
                        }
                    }
                }
                statement {
                    declareProperty(modifier = "val", name = "pair", type = "Pair<String, String>") {
                        on({ string("") }) {
                            call("to") {
                                extensionFunction()
                                infix()
                                argument { string("") }
                            }
                        }
                    }
                }
            }
        }
    }
}

val lambdaAsFinalParameter = slide {
    slideContent {
        splitSlide(leftBlock = {
            slideList {
                li { +"Lift final lambda parameter out of ()" }
                li { +"Make function calls look like built-in language blocks" }
            }
        }) {
            slideCode {
                kotlin {
                    declareFunction("f") {
                        parameter("lambda", type = "(Int) -> String")
                    }
                    statement {
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
                    statement {
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
    }
}

val lambdaWithReceiver = slide {
    slideContent {
        splitSlide({
            slideList {
                li { +"lambda that is invoked on an subject" }
                li { +"like 'apply'" }
            }
        }) {
            slideCode {
                declareFunction("builder", "String", false) {
                    body {
                        returns {
                            on({
                                call("StringBuilder")
                            }) {
                                call(name = "append") {
                                    argument { string("content") }
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
                                                argument { string("content") }
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
}

val dslMarker = slide {
    slideContent {
        splitSlide(leftBlock = {
            slideList {
                li { +"Restricts scope" }
                this@splitSlide.slideCode {
                    kotlin {
                        annotationClass("MyDsl") {
                            annotation("DslMarker")
                        }
                    }
                }
            }
        }) {
            slideCode {
                kotlin {
                    declareFunction(name = "c1", returnType = "Class1") {
                        parameter(name = "a1", type = "Class1.() -> Unit")
                        body {
                            returns {
                                inline {
                                    on({ +"Class1" }) {
                                        callInvoke().call(name = "apply") {
                                            extensionFunction()
                                            argument(value = { +"a1" })
                                        }
                                    }
                                }
                            }
                        }
                    }
                    declareClass(name = "Class1") {
                        annotation("MyDsl")
                        property(modifier = "var", name = "a2", type = "Class2?") {
                            keyword("null")
                        }
                        property(modifier = "var", name = "prop1", type = "String?") {
                            keyword("null")
                        }
                        function(name = "c2", paramsOnSeparateLines = false) {
                            parameter(name = "c2", type = "Class2.() -> Unit")
                            body {
                                assignment(name = "a2", format = CODE::variablePropertyName) {
                                    expression {
                                        on({ +"Class2" }) {
                                            callInvoke().call(name = "apply") {
                                                extensionFunction()
                                                argument(value = { +"a2" })
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    declareClass(name = "Class2", propsOnSeparateLines = false) {
                        annotation("MyDsl")
                        property(modifier = "var", name = "prop2", type = "String?") {
                            keyword("null")
                        }
                    }
                    statement {
                        call(name = "c1") {
                            lambda {
                                call(name = "c2") {
                                    lambda {
                                        assignment(name = "prop2", format = CODE::variablePropertyName) {
                                            inlineExpression { string("value") }
                                        }
                                        //TODO: refactor
                                        statement {
                                            on({
                                                keyword("this")
                                                scope("@c1")
                                            }) {
                                                property("prop1")
                                            }
                                            inlineExpression {
                                                +" = "
                                                string("value")
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


@DslMarker
annotation class MyDsl

fun c1(a1: Class1.() -> Unit): Class1 {
    return Class1().apply(a1)
}

@MyDsl
class Class1(var prop1: String? = null) {
    private var a2: Class2? = null
    fun c2(c2: Class2.() -> Unit) {
        a2 = Class2().apply(c2)
    }
}

@MyDsl
class Class2(var prop2: String? = null)

val a = c1 {
    c2 {
        prop2 = "value"
        this@c1.prop1 = "value"
    }
}


val staticWebKotlinHtml = slide {
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

val staticWebKotlinCss = slide {
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

val staticWebKotlinReuse = slide {
    slideContent {
        splitSlide(leftBlock = {
            slideCode {
                kotlin {
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
            }
        }) {
            slideCode {
                kotlin {
                    declareFunction(name = "sideNav", extentionOf = "BODY") {
                        body {
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
}


val backingObjects = slide {
    splitSlide({
        slideList {
            li { +"For defining discrete objects of the domain" }
        }
    }) {
        slideCode {
            kotlin {
                declareClass(name = "Entry") {
                    annotation("RssDsl")
                    superClass = "SyndEntryImpl"
                    function(name = "content", paramsOnSeparateLines = false) {
                        parameter(name = "content", type = "String")
                        body {
                            assignment(name = "description", format = CODE::variablePropertyName) {
                                constructor(className = "SyndContentImpl")
                                        .call(name = "apply") {
                                            extensionFunction()
                                            lambda {
                                                assignment("value") {
                                                    inlineExpression { +"content" }
                                                }
                                                assignment("type") {
                                                    inlineExpression { string("text/string") }
                                                }
                                            }
                                        }
                            }
                        }
                    }
                    function(name = "content", paramsOnSeparateLines = false) {
                        parameter(name = "block", type = "HTML.() -> Unit")
                        body {
                            assignment(name = "description", format = CODE::variablePropertyName) {
                                constructor(className = "SyndContentImpl")
                                        .call(name = "apply") {
                                            extensionFunction()
                                            lambda {
                                                assignment("value") {
                                                    constructor(className = "StringWriter")
                                                            .breakAndCall(name = "appendHTML", argsOnDifferentLines = false) {
                                                                extensionFunction()
                                                                argument("prettyPrint") {
                                                                    keyword("false")
                                                                }
                                                            }
                                                            .breakAndCall("html") {
                                                                extensionFunction()
                                                                lambda {
                                                                    call("apply") {
                                                                        extensionFunction()
                                                                        argument { +"block" }
                                                                    }
                                                                }
                                                            }
                                                            .call("toString")
                                                }
                                                assignment("type") {
                                                    inlineExpression { string("text/html") }
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


val theEnd = slide {
    slideContent {
        splitSlide(leftBlock = {
            slidePicture("x-all-the-things.jpg", alt = "All the things!") {
            }
        }) {
            slideList {
                li { +"HTML" }
                li { +"CSS" }
                li { +"Android Views" }
                li { +"JavaFx (TornadoFx)" }
                li { +"Recipes" }
                li { +"Slideshows" }
                li { +"Blog" }
                li { +"What ideas can you think of?" }
            }
        }
    }
}
