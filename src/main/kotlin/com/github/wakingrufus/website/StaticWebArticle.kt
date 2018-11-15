package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.code.*
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import kotlinx.html.*


fun staticweb(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Static Web with Kotlin DSLs")
        sideNav()
        content {
            introToStaticWeb()(this)
            lambdaAsFinalParameter()(this)
            lambdaWithReceiver()(this)
            dslMarker()(this)
            htmlDsl()(this)
            codeReuse()(this)
            dslExtension()(this)
            usage()(this)
        }
    }
}

fun introToStaticWeb(): DIV.() -> Unit = {
    p {
        +"""The term "static web" refers to a style of web development that might seem out of place in today's
            |modern scene of Javascript frameworks.
            |Not only are social media (Facebook, Twitter, etc) and email (GMail, Yahoo, etc) applications delivered as
            |Javascript-based "Single Page Applications", but also document-based websites, such as news websites and blogs.
            |However, it is this proliferation of javascript-based
            |web applications that inspires a reaction to get back to some of the ideals of the web of yesteryear.
            |In this article, we will review the benefits of the static web and look at how Kotlin can bring us
            |some of the benefits of dynamic web programming to the static web world.
            """.trimMargin()
    }
    h2 { +"Documents, not Apps" }
    p {
        +"""
            The Web was designed to provide documents.
            HTML is a markup language based on XML which was designed to be the standard format for documents on the web.
            The addition of Javascript (JS) and AJAX to the standard set of web technologies allowed these documents to be dynamic.
            Javascript enables programming logic to run within the browser on a web page.
            AJAX allows JS to make requests to servers to send or receive additional information, within a single web page.
            This opened up a doorway to deliver cross-platform applications with rich user interfaces.
            The JS ecosystem developed around this idea of "Web Applications" in order to make it easier to develop these webapps
            and bring along the perks of a true development environment. Pretty soon, this JS ecosystem
            became so ubiquitous, that nearly all websites on the internet are web applications.
            """.trimIndent()
    }
    p {
        +"""But this has had some downsides. The amount of data transferred when loading a website has exploded.
            |Sites are built on JS libraries which are built on other libraries which are built on others...
            |Many memes have been written about the `node_modules` directory.
            |All of those files are compiled into the application that is delivered as part of a page load.
            |On top of this, sites have stuffed a myriad of trackers and advertisements into their sites
            |in order to monetize you via
            """.trimMargin()
        +" "
        a(href="https://en.wikipedia.org/wiki/Surveillance_capitalism"){+"surveillance capitalism"}
        +". "
        +"""In addition to the privacy issues with this, it also makes these sites near unusable on slow connections
            such as mobile connections or connections in rural areas or developing countries.
            """.trimIndent()
    }
    p {
        +"""
            Some sites should be webapps, such as eCommerce sites, or web interfaces to applications (think gmail, etc).
            But most websites are just serving up static content, such as news websites and blogs.
            There is no reason these types of sites need to carry all the baggage that comes with a webapp.
            With todays technologies, static websites can be extremely fast and reliable.
            There is a new static webhost called
            """.trimIndent()
        +" "
        a(href = "http://neocites.org") { +"Neocities" }
        +" a name which is an homage to the defunct Geocities, where many people are building static sites."
    }
    p {
        +"""
            I decided to build myself a personal website in order to avoid places like Medium, Twitter, or various slideshow services.
            Anyone who has built a site by hand in HTML knows that this is very tedious.
            There are some frameworks for generating static sites, such as Jekyll and Hugo, which are great for most people.
            But I am a programmer, and I'd like the control I get from programming, with the benefits of using a framework as well.
            In this example, instead of using a template engine such as Jekyll or Hugo, I will be using a "Domain-Specific Language", or DSL.
            A DSL is a simplified programming language designed for a specific use case.
            This is where Kotlin comes in.
            """.trimIndent()
    }
    p {
        +"""Kotlin's functional programming capabilities allows it to be used to create DSLs.
            The Kotlin team has created HTML and CSS DSLs already.
            Instead of building a DSL from scratch, I decided to extend these DSLs. I will walk you thought how I did this.
            Along the way, we will see how DSLs are built, using the HTML DSL as a model example,
            then how to write our own DSLs by extending the HTML DSL.
            """.trimIndent()
    }
    p { +"First, let's briefly review some Kotlin features used in DSLs." }
}

fun lambdaAsFinalParameter(): DIV.() -> Unit = {
    h2 { +"Lambda as final parameter" }
    p {
        +"""In Kotlin, if the last parameter of a function is a lambda,
            |you may "lift" the parameter out of the enclosing parentheses.
            |So, if your function signature looks like:
            """.trimMargin()
    }
    sampleCode {
        declareFunction("f") {
            parameter("lambda", type = "(Int) -> String")
        }
    }
    p { +"Then, instead of invoking it like this:" }
    sampleCode {
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
    p { +"you can invoke it like this:" }
    sampleCode {
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

fun lambdaWithReceiver(): DIV.() -> Unit = {
    h2 { +"Lambda with receiver" }
    p { +"A lambda parameter with receiver looks like this:" }
    sampleCode {
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
    }
    p {
        +"""What this means is that the lambda passed in as the parameter will run
            |as if it were the body of an apply() call on an object of the type specified.
            |this allows you to turn a Java-style fluent builder into a Kotlin-style DSL.
            |You can think of it as the extension function version of a lambda.
            """.trimMargin()
    }
    sampleCode {
        declareFunction("builder", "String", false) {
            body {
                expression {
                    declareReturn {
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
        }
    }
    p { +"becomes" }
    sampleCode {
        declareFunctionExpression("dsl", "String", false) {
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

fun dslMarker(): DIV.() -> Unit = {
    h2 { +"@DslMarker" }
    p {
        +"""
                |DSL markers help in DSLs when you are nesting multiple reciever calls.
                |In our example, this would apply when we are within the reciever for an HTML body element,
                |and we don't want to see any methods from the enclosing html element.
                |It keeps in scope only the methods on the innermost reciever, and removes any methods on higher up recievers.
                |They can still be accessed through an explicit call such as this@HTML.meta """.trimMargin()
    }
    p { +"DslMarkers are declared like this:" }
    sampleCode {
        +"""@DslMarker
                    |annotation class HtmlTagMarker""".trimMargin()
    }
    p {
        +"""The above example is declared within the kotlinx HTML DSL, which we will be extending, so we can re-use it.
                |But if you are creating your own DSL from scratch, you will want to declare your own.
            """.trimMargin()
    }
}

fun htmlDsl(): DIV.() -> Unit = {
    h2 { +"HTML DSL" }
    p {
        +"The Jetbrains team as created a DSL for HTML which serves as a prime example of how to write DSLs in Kotlin"
        +"It is available on "
        a(href = "https://github.com/Kotlin/kotlinx.html") { +"GitHub" }
        +". "
        +"Here is an example of using the HTML DSL:"
    }
    sampleCode {
        on({
            call("FileWriter") {
                argument {
                    string("path/to/file")
                }
            }
        }) {
            call("appendHTML")
            call("html") {
                lambda {
                    call("head") {
                        lambda { }
                    }
                    call("body") {
                        lambda {
                            call("h1") {
                                lambda(inline = true) {
                                    inlineExpression {
                                        +"+"
                                        string("Header")
                                    }
                                }
                            }
                            call("p") {
                                lambda(inline = true) {
                                    inlineExpression {
                                        +"+"
                                        string("paragraph text")
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

fun codeReuse(): DIV.() -> Unit = {
    h2 { +"Code reuse" }
    p {
        +"""One of the first benefits of using a programming language over raw HTML we want to leverage is code reuse.
            |I want every page on my site to contain a navigation element.
            |In order to avoid repeating myself and risk the navigation sections of each page becoming different from each other,
            |I extract the navigation bar of my site to a function.
            |I use an extension function to add a method that I can use within any BODY receiver block
            """.trimMargin()
    }
    sampleCode {
        declareFunction(name = "sideNavBar", extentionOf = "BODY") {
            parameter(name = "block", type = "UL.() -> Unit")
            body {
                expression {
                    keyword("return ")
                    call("div") {
                        lambda {
                            assignment(name = "classes", operator = "+=") {
                                inlineExpression { string("navBar") }
                            }
                            assignment(name = "style") {
                                inlineExpression {
                                    call("css") {
                                        lambda(indentation = 2) {
                                            assignment(name = "verticalAlign") {
                                                inlineExpression {
                                                    +"VerticalAlign.top"
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            call("ul") {
                                lambda {
                                    assignment(name = "style") {
                                        inlineExpression {
                                            call(name = "css") {
                                                lambda(indentation = 3) {
                                                    assignment(name = "listStyleType") {
                                                        inlineExpression {
                                                            +"ListStyleType.none"
                                                        }
                                                    }
                                                    assignment(name = "color") {
                                                        inlineExpression {
                                                            call(name = "Color") {
                                                                argument { string("#9999EE") }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }
                                    expression {
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
    p {
        +"Using this function, I can build my navigation like this:"
    }
    sampleCode {
        declareFunction(name = "sideNav", extentionOf = "BODY") {
            body {
                expression {
                    call(name = "apply") {
                        lambda {
                            call(name = "sideNavBar") {
                                lambda {
                                    call(name = "li") {
                                        lambda(inline = true) {
                                            inlineCall(name = "a") {
                                                argument(name = "href") { string("index.html") }
                                                lambda(inline = true) {
                                                    inlineExpression {
                                                        +"+"
                                                        string("Home")
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    call(name = "li") {
                                        lambda(inline = true) {
                                            inlineCall(name = "a") {
                                                argument(name = "href") { string("travel.html") }
                                                lambda(inline = true) {
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
    }
    p { +"And use it in each page on my site:" }
    sampleCode {
        declareFunctionExpression(name = "mainPage", returnType = "HTML.() -> Unit"){
            inlineExpression {
                block {
                    call(name = "head") {
                        lambda { }
                    }
                    call(name = "body") {
                        lambda {
                            expression {
                                call(name = "sideNav")
                            }
                        }
                    }
                }
            }
        }
    }
}

fun dslExtension(): DIV.() -> Unit = {
    p {
        +"""
                Now, if we want to actually extend the DSL itsself first we need to define a backing object for the thing our DSL will describe.
            """.trimIndent()
    }
    sampleCode {
        +"""
@HtmlTagMarker
class PLACE(val name: String,
            var website: String? = null,
            var map: String? = null,
            var description: P.() -> Unit = {},
            val level: NestedAreaLevel = NestedAreaLevel(1)) {
    private var places: List<PLACE> = ArrayList()
    private var subAreas: List<PLACE> = ArrayList()

    fun place(name: String, website: String? = null, block: PLACE.() -> Unit) {
        places += PLACE(name = name, website = website, level = NestedAreaLevel(6)).apply(block)
    }

    fun subArea(name: String, block: PLACE.() -> Unit) {
        subAreas += PLACE(name = name, level = level.next()).apply(block)
    }

    fun description(block: P.() -> Unit) {
        description = block
    }

    operator fun invoke(code: DIV) {
        // TODO
    }
            """.trimIndent()
    }

    p {
        +"""
                The class contains properties to hold information we will need when rendering this element.
                It also defines methods for modifying this information.
                These methods will be the ones available within the reciever block when using the DSL.
                finally, the invoke method will define how this element will be rendered.
                My implementation looks like this:
            """.trimIndent()
    }
    sampleCode {
        +"""
                operator fun invoke(code: DIV) {
        code.run {
            a { id = this@PLACE.name.toLowerCase() }
            headerLevel(this@PLACE.level)(this, null) {
                if (this@PLACE.level.value == 6) {
                    style = css {
                        fontSize = .67.em
                        marginBottom = 1.em
                    }
                }
                +this@PLACE.name
            }.apply { }
            this@PLACE.website?.let {
                div {
                    if (this@PLACE.level.value == 6) {
                        style = css {
                            fontSize = .67.em
                            marginBottom = 1.em
                        }
                    }
                    a(href = it) { +"Website" }
                    this@PLACE.map?.let { m ->
                        +" "
                        a(href = m) { +"Map" }
                    }
                }
            }
            p {
                if (this@PLACE.level.value == 6) {
                    style = css {
                        fontSize = .67.em
                    }
                }
                this@PLACE.description(this)
            }
            div {
                style = css {
                    paddingLeft = this@PLACE.level.value.em
                }
                this@PLACE.places.forEach { place ->
                    place(code)
                }
                this@PLACE.subAreas.forEach { subArea ->
                    subArea(code)
                }
            }
        }
    }
            """.trimIndent()
    }
}

fun usage(): DIV.() -> Unit = {
    h2 { +"Usage" }
    p {
        +"""
                We are ready to use our DSL extensions. My Travel page looks like this:
            """.trimIndent()
    }
    sampleCode {
        +"""
                fun travel(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Travel")
        sideNav()
        content {
            ul {
                li { a(href = "#colorado") { +"Colorado" } }
            }
            area(name = "Large Area") {
                subArea("Sub Area w/ places", subAreaWithPlaces())
                subArea("Sub Area", subArea())
                subArea("subAreaWithSubAreaWithPlaces", subAreaWithSubAreaWithPlaces())
            }
        }
    }
}

fun subArea(): PLACE.() -> Unit = {
    description { +" ... " }
}

fun subAreaWithSubAreaWithPlaces(): PLACE.() -> Unit = {
    subArea("nested subarea") {
        website = "https://google.com/"
        description { +" ... " }
        place(name = "Sample place within nested subarea",
                website = "http://google.com/") {
            map = "https://www.google.com/maps/..."
            description { +" ... " }
        }
    }

    place(name = "Sample place",
            website = "http://google.com/") {
        map = "https://www.google.com/maps/..."
        description { +" ... " }
    }
}

fun subAreaWithPlaces(): PLACE.() -> Unit = {
    description { +" ... " }
    place(name = "Sample place in a subarea",
                website = "http://google.com/") {
            map = "https://www.google.com/maps/..."
            description { +" ... " }
    }
}
            """.trimIndent()
    }
}

