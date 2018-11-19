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
            conclusion()(this)
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
        a(href = "https://en.wikipedia.org/wiki/Surveillance_capitalism") { +"surveillance capitalism" }
        +". "
        +"""In addition to the privacy issues with this, it also makes these sites near unusable on slow connections
            such as mobile connections or connections in rural areas or developing countries.
            Dynamic webapps also degrade the idea of the
            """.trimIndent()
        +" "
        a(href = "https://en.wikipedia.org/wiki/Semantic_Web") { +"Semantic Web" }
        +", a concept critical to accessibility on the web, especially for people who rely on screen readers."
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
        +", a name which is an homage to the defunct Geocities, where many people are building static sites."
    }
    p {
        +"""
            I decided to build myself a personal website in order to avoid places like Medium, Twitter, or various slideshow services.
            Anyone who has built a site by hand in HTML knows that this is very tedious.
            There are some frameworks for generating static sites, such as Jekyll and Hugo, which are great for most people.
            But I am a programmer, and I'd like the control I get from programming, with the benefits of using a framework as well.
            In this example, instead of using a template engine such as Jekyll or Hugo, I will be using a "Domain-Specific Language", or DSL.
            A DSL is a simplified programming language designed for a specific use case, and typically have a declarative style.
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
                returns {
                    code {
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

fun dslMarker(): DIV.() -> Unit = {
    h2 { +"@DslMarker" }
    p {
        +"""DSL markers help in DSLs when you are nesting multiple receiver calls.
            |In our example, this would apply when we are within the receiver for an HTML body element,
            |and we don't want to see any methods from the enclosing html element.
            |It keeps in scope only the methods on the innermost receiver, and removes any methods on higher up receivers.
            |They can still be accessed through an explicit call such as this@HTML.meta """.trimMargin()
    }
    p { +"DslMarkers are declared like this:" }
    sampleCode {
        keyword("@DslMarker")
        +"\n"
        annotationClass("HtmlTagMarker")
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
                                    expression {
                                        +"+"
                                        string("Header")
                                    }
                                }
                            }
                            call("p") {
                                lambda(inline = true) {
                                    expression {
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
                returns {
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
    p {
        +"Using this function, I can build my navigation like this:"
    }
    sampleCode {
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
    }
    p { +"And use it in each page on my site:" }
    sampleCode {
        declareFunctionExpression(name = "mainPage", returnType = "HTML.() -> Unit") {
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

fun dslExtension(): DIV.() -> Unit = {
    p {
        +"""Next, instead of strict code reuse, I want to be able to easily replicate a pattern of usage of the DSL.
            |For this example, I want to create a page where I can document my travels, and act as a guide to others
            |who might be travelling in the same areas.
            |I want to be able to break areas down into sub areas, and detail specific locations.
            |To do this, I will extend the DSL itself in order to eliminate the boilerplate and repetition,
            |but still expose the flexibility needed to do all the things I am looking for.
            |DSLs are usually backed by objects, so to extend the DSL, we need to define a backing object for the thing our extension will describe.
            """.trimMargin()
    }
    sampleCode {
        keyword("@HtmlTagMarker")
        +"\n"
        declareClass(name = "PLACE") {
            value(name = "name", type = "String")
            value(name = "website", type = "String?") { keyword("null") }
            value(name = "map", type = "String?") { keyword("null") }
            value(name = "description", type = "P.() -> Unit") { block(inline = true) {} }
            value(name = "level", type = "NestedAreaLevel") {
                call("NestedAreaLevel") { argument { number(1) } }
            }
            property(modifier = "private var", name = "places", type = "List<PLACE>", inConstructor = false) { call(name = "ArrayList") }
            property(modifier = "private var", name = "subAreas", type = "List<PLACE>", inConstructor = false) { call(name = "ArrayList") }
            function(name = "place") {
                parameter(name = "name", type = "String")
                parameter(name = "website", type = "String?") { keyword("null") }
                parameter(name = "block", type = "PLACE.() -> Unit")
                body {
                    assignment(name = "places", operator = "+=") {
                        inlineExpression {
                            on({
                                call(name = "PLACE") {
                                    argument(name = "name") { +"name" }
                                    argument(name = "website") { +"website" }
                                    argument(name = "level") {
                                        call("NestedAreaLevel") {
                                            argument { number(6) }
                                        }
                                    }
                                }
                            }) {
                                call("apply") {
                                    extensionFunction()
                                    argument { +"block" }
                                }
                            }
                        }
                    }
                }
            }
            function(name = "subArea") {
                parameter(name = "name", type = "String")
                parameter(name = "block", type = "PLACE.() -> Unit")
                body {
                    assignment(name = "subAreas", operator = "+=") {
                        inlineExpression {
                            on({
                                call(name = "PLACE") {
                                    argument(name = "name") { +"name" }
                                    argument(name = "level") {
                                        on({ +"level" }) {
                                            call(name = "next")
                                        }
                                    }
                                }
                            }) {
                                call("apply") {
                                    extensionFunction()
                                    argument { +"block" }
                                }
                            }
                        }
                    }
                }
            }
            function(name = "description", paramsOnSeparateLines = false) {
                parameter(name = "block", type = "P.() -> Unit")
                body {
                    statement {
                        assignment(name = "description")
                        inlineExpression { +"block" }
                    }
                }
            }
            function(operator = true, name = "invoke", paramsOnSeparateLines = false) {
                parameter(name = "code", type = "DIV")
                body {
                    statement { inlineExpression { +"// TODO" } }
                }
            }
        }
    }

    p {
        +"""
                The class contains properties to hold information we will need when rendering this element.
                It also defines methods for modifying this information.
                These methods will be the ones available within the receiver block when using the DSL.
                finally, the invoke method will define how this element will be rendered.
                You might notice the use of a class called NestedAreaLevel.
                This is a class that I am using to represent the nesting level of the areas.
                It looks like this:
                """.trimIndent()
    }
    sampleCode {
        declareClass(modifiers = listOf("inline"), name = "NestedAreaLevel", propsOnSeparateLines = false) {
            value(name = "value", type = "Int", inConstructor = true)
            companionObject {
                assignment(modifier = "val", name = "default") {
                    call(name = "NestedAreaLevel") {
                        argument { number(2) }
                    }
                }
            }
            function(name = "next", returnType = "NestedAreaLevel") {
                body {
                    returns {
                        whenExpression(this@function.indentation) {
                            subject {
                                on({ keyword("this") }) {
                                    property("value")
                                }
                            }
                            pair({ number(1) }) {
                                call("NestedAreaLevel") {
                                    argument { number(2) }
                                }
                            }
                            pair({ number(2) }) {
                                call("NestedAreaLevel") {
                                    argument { number(3) }
                                }
                            }
                            pair({ number(3) }) {
                                call("NestedAreaLevel") {
                                    argument { number(4) }
                                }
                            }
                            pair({ number(4) }) {
                                call("NestedAreaLevel") {
                                    argument { number(5) }
                                }
                            }
                            pair({ number(5) }) {
                                call("NestedAreaLevel") {
                                    argument { number(6) }
                                }
                            }
                            pair({ number(6) }) {
                                call("NestedAreaLevel") {
                                    argument { number(6) }
                                }
                            }
                            pair({ keyword("else") }) {
                                on({ +"NestedAreaLevel" }) {
                                    property("default")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    p {
        +"""
                My implementation looks like this:
            """.trimIndent()
    }
    sampleCode {
        declareClass(name = "PLACE") {
            function(operator = true, name = "invoke", paramsOnSeparateLines = false) {
                parameter(name = "code", type = "DIV")
                body {
                    statement {
                        on({ +"code" }) {
                            call(name = "run") {
                                extensionFunction()
                                lambda {
                                    call(name = "a") {
                                        lambda {
                                            assignment(name = "id") {
                                                inlineExpression {
                                                    keyword("this")
                                                    scope("@PLACE")
                                                    +".name.toLowerCase()"
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


fun conclusion(): DIV.() -> Unit = {
    p {
        +"""
        Using a DSL lets me design my web page with minimal repetition and boilerplate.
        If this approach interests you, I encourage to check out the source code for my
    """.trimIndent()
        a(href = "https://neocities.org/wakingrufus") { +"Website" }
        +" on "
        a(href = "https://github.com/wakingrufus/website") { +"GitHub" }
        +", which is built entirely in Kotlin using DSLs."
    }
}

