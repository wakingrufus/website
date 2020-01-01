package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.code.*
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.myFooter
import com.github.wakingrufus.website.sideNav
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
            staticWebAdditionalResources(this)
        }
        footer { myFooter() }
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
        +"""Some sites should be webapps, such as eCommerce sites, or web interfaces to applications (think gmail, etc).
            But most websites are just serving up static content, such as news websites and blogs.
            """.trimIndent()
    }
    p { +"Sites which are delivered as static web pages can enjoy the following benefits:" }
    h3 {
        +"Speed"
    }
    p {
        +"""
        Because no javascript needs to be executed, and static content can be cached,
        loading times can be incredibly fast, even in bandwidth-restricted environments.
        These sites will also use far less memory in the browser.
    """.trimIndent()
    }
    h3 { +"Simplicity" }
    p {
        +"""Because there is no dynamic code, there are many fewer points of failure.
            |The code is a lot easier to check for errors and maintain.""".trimMargin()
    }
    h3 { +"Portabilty" }
    p {
        +"Any webhost can host a static site. Some sites, such as "
        a(href = "http://neocities.org") { +"Neocities" }
        +" (a play on the old Geocities) are specifically designed to do this very cheaply with generous free tiers."
    }
    h3 { +"Accessibility" }
    p { +"Semantically written static sites work on screen readers out of the box, and can be printed very nicely." }
    p { +"There are a few disadvantages to static sites:" }
    h3 { +"Limited features" }
    p { +"Hand-coding HTML and CSS is tedious enough, but hand-coding the XML for an RSS feed would be out of the question." }
    h3 { +"Code Maintenance" }
    p { +"Hand-coded HTML will require a lot of copy and pasting and code duplication" }
    p {
        +"""Static site generators such as Jekyll and Hugo help solve these two disadvantages, and are great for non-technical folks.
            However, they come with their own restrictions.
            Using Kotlin DSL's, we can get the same benefits as a static site generator, without the restrictions.
            """.trimIndent()
    }
    p {
        +"""
            I decided to build myself a personal website in order to avoid places like Medium, Twitter, or various slideshow services.
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
    p { +"becomes" }
    sampleCode {
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

fun dslMarker(): DIV.() -> Unit = {
    h2 { +"@DslMarker" }
    p {
        +"""DSL markers help in DSLs when you are nesting multiple receiver calls.
            |In our example, this would apply when we are within the receiver for an HTML body element,
            |and we don't want to see any methods from the enclosing html element.
            |It keeps in scope only the methods on the innermost receiver, and removes any methods on higher up receivers.
            |They can still be accessed through an explicit call such as this@HTML.meta.
            |""".trimMargin()
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
    p {
        +"Using this function, I can build my navigation like this:"
    }
    sampleCode {
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
    }
    p { +"And use it in each page on my site:" }
    sampleCode {
        declareFunction(name = "mainPage", returnType = "HTML.() -> Unit") {
            expression2 {
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

fun dslExtension(): DIV.() -> Unit = {
    p {
        +"""Next, instead of strict code reuse, I want to be able to easily replicate a pattern of usage of the DSL.
            |For this example, I will create a DSL for building a rolodex page.
            |I want to be able to display contact information on a page, organized alphabetically.
            |To do this, I will extend the DSL itself in order to eliminate the boilerplate and repetition,
            |but still expose the flexibility needed to do all the things I am looking for.
            |DSLs are usually backed by objects, so to extend the DSL, we need to define a backing object for the thing our extension will describe.
            """.trimMargin()
    }
    sampleCode {
        keyword("@HtmlTagMarker")
        +"\n"
        declareClass(name = "ROLODEX") {
            variable(inConstructor = false, name = "contactList", type = "List<CONTACT>") {
                call(name = "listOf")
            }
            function(name = "contact", paramsOnSeparateLines = false) {
                parameter(name = "contact", type = "CONTACT.() -> Unit")
                body {
                    assignment(format = CODE::variablePropertyName, name = "contactList", operator = "+=") {
                        on({ call(name = "CONTACT") }) {
                            call(name = "apply") {
                                extensionFunction()
                                argument {
                                    +"contact"
                                }
                            }
                        }
                    }
                }
            }
            function(operator = true, name = "invoke", paramsOnSeparateLines = false) {
                parameter(name = "body", type = "BODY")
                body {
                    statement { inlineExpression { +"// TODO" } }
                }
            }
        }
        keyword("@HtmlTagMarker")
        +"\n"
        declareClass(name = "CONTACT") {
            variable(inConstructor = false, name = "picture", type = "String") { string("") }
            variable(inConstructor = false, name = "name", type = "String") { string("") }
            variable(inConstructor = false, name = "email", type = "String") { string("") }
            variable(inConstructor = false, name = "phone", type = "String") { string("") }
            function(operator = true, name = "invoke", paramsOnSeparateLines = false) {
                parameter(name = "div", type = "DIV")
                body {
                    statement { inlineExpression { +"// TODO" } }
                }
            }
        }
    }

    p {
        +"""The class contains properties to hold information we will need when rendering this element.
                It also defines methods for modifying this information.
                These methods will be the ones available within the receiver block when using the DSL.
                """.trimIndent()
    }
    p {
        +"""Next, the invoke method will define how this element will be rendered.
            My implementation looks like this:
            """.trimIndent()
    }
    sampleCode {
        rolodexDslClass()(this)
        contactDslClass()(this)
        declareFunction(name = "field", extentionOf = "DIV") {
            parameter(name = "name", type = "String")
            parameter(name = "value", type = "SPAN.() -> Unit")
            body {
                call("div") {
                    lambda {
                        call("span") {
                            lambda {
                                assignment("style", format = CODE::variablePropertyName) {
                                    call("css") {
                                        lambda {
                                            cssDisplayInlineBlock()
                                            cssWidth(6)
                                        }
                                    }
                                }
                                expression { +"+name" }
                            }
                        }
                        call("span") {
                            lambda {
                                assignment("style", format = CODE::variablePropertyName) {
                                    call("css") {
                                        lambda {
                                            cssDisplayInlineBlock()
                                        }
                                    }
                                }
                                call("value") {
                                    argument { keyword("this") }
                                }
                            }
                        }
                    }
                }
            }
        }
        +"\n"
        declareFunction(name = "rolodex", extentionOf = "BODY") {
            parameter("rolodex", type = "ROLODEX.() -> Unit")
            body {
                expression {
                    on({ call("ROLODEX") }) {
                        call("apply") {
                            extensionFunction()
                            argument {
                                +"rolodex"
                            }
                        }
                        callInvoke {
                            argument { keyword("this") }
                        }
                    }
                }
            }
        }
    }
}

fun rolodexDslClass(): CODE.() -> Unit = {
    declareClass(name = "ROLODEX") {
        annotation("HtmlTagMarker")
        variable(inConstructor = false, name = "contactList", type = "List<CONTACT>") {
            call(name = "listOf")
        }
        function(name = "contact", paramsOnSeparateLines = false) {
            parameter(name = "contact", type = "CONTACT.() -> Unit")
            body {
                assignment(format = CODE::variablePropertyName, name = "contactList", operator = "+=") {
                    on({ call(name = "CONTACT") }) {
                        call(name = "apply") {
                            extensionFunction()
                            argument {
                                +"contact"
                            }
                        }
                    }
                }
            }
        }
        function(operator = true, name = "invoke", paramsOnSeparateLines = false) {
            parameter(name = "body", type = "BODY")
            body {
                statement {
                    on({ +"body" }) {
                        call(name = "apply") {
                            extensionFunction()
                            lambda {
                                call(name = "h1") {
                                    lambda(inline = true) {
                                        expression {
                                            +"+"
                                            string("Rolodex")
                                        }
                                    }
                                }
                                call(name = "div") {
                                    lambda {
                                        statement {
                                            on({
                                                keyword("this")
                                                scope("@ROLODEX")
                                            }) {
                                                property("contactList")
                                                breakAndCall(name = "groupBy") {
                                                    extensionFunction()
                                                    lambda(inline = true) {
                                                        expression {
                                                            on({ it() }) {
                                                                property(name = "name")
                                                                call("split") {
                                                                    extensionFunction()
                                                                    argument { string(" ") }
                                                                }
                                                                call("last") {
                                                                    extensionFunction()
                                                                }
                                                                index(0)
                                                            }
                                                        }
                                                    }
                                                }
                                                breakAndCall("toList") {
                                                    extensionFunction()
                                                }
                                                breakAndCall("sortedBy") {
                                                    extensionFunction()
                                                    lambda(inline = true) {
                                                        statement {
                                                            on({ it() }) {
                                                                property("first")
                                                            }
                                                        }
                                                    }
                                                }
                                                breakAndCall("forEach") {
                                                    extensionFunction()
                                                    lambda {
                                                        call("h2") {
                                                            lambda {
                                                                assignment(format = CODE::variablePropertyName,
                                                                        name = "style") {
                                                                    call("css") {
                                                                        lambda {
                                                                            assignment(format = CODE::variablePropertyName,
                                                                                    name = "display") {
                                                                                on({ +"Display" }) {
                                                                                    property("block")
                                                                                }
                                                                            }
                                                                            statement {
                                                                                call("borderBottom") {
                                                                                    extensionFunction()
                                                                                    argument("width") {
                                                                                        on({ number(1) }) {
                                                                                            property("px")
                                                                                        }
                                                                                    }
                                                                                    argument("style") {
                                                                                        on({ +"BorderStyle" }) {
                                                                                            property("solid")
                                                                                        }
                                                                                    }
                                                                                    argument("color") {
                                                                                        on({ +"Color" }) {
                                                                                            property("black")
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                expression {
                                                                    +"+"
                                                                    on({ it() }) {
                                                                        property("first")
                                                                        call("toString")
                                                                    }

                                                                }
                                                            }
                                                        }
                                                        statement {
                                                            on({ it() }) {
                                                                property("second")
                                                                call("forEach") {
                                                                    extensionFunction()
                                                                    lambda {
                                                                        call("it") {
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
                            }
                        }
                    }
                }
            }
        }
    }
}

fun contactDslClass(): CODE.() -> Unit = {
    declareClass(name = "CONTACT") {
        annotation("HtmlTagMarker")
        variable(inConstructor = false, name = "picture", type = "String") { string("") }
        variable(inConstructor = false, name = "name", type = "String") { string("") }
        variable(inConstructor = false, name = "email", type = "String") { string("") }
        variable(inConstructor = false, name = "phone", type = "String") { string("") }
        function(operator = true, name = "invoke", paramsOnSeparateLines = false) {
            parameter(name = "div", type = "DIV")
            body {
                statement {
                    on({ +"div" }) {
                        call("apply") {
                            extensionFunction()
                            lambda {
                                call("div") {
                                    lambda {
                                        call("div") {
                                            lambda {
                                                assignment(format = CODE::variablePropertyName,
                                                        name = "style") {
                                                    call("css") {
                                                        lambda {
                                                            cssHeight(3)
                                                            cssDisplayInlineBlock()
                                                        }
                                                    }
                                                }
                                                assignment(modifier = "val", name = "imageBase64") {
                                                    call("resourceAsBase64") {
                                                        argument {
                                                            on({
                                                                keyword("this")
                                                                scope("@CONTACT")
                                                            }) {
                                                                property("picture")
                                                            }
                                                        }
                                                    }
                                                }
                                                call("img") {
                                                    argument("src") { string("data:image/png;base64, \$imageBase64") }
                                                    lambda {
                                                        assignment(format = CODE::variablePropertyName,
                                                                name = "style") {
                                                            call("css") {
                                                                lambda {
                                                                    cssHeight(3)
                                                                    cssMaxHeight(3)
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        call("div") {
                                            lambda {
                                                assignment(format = CODE::variablePropertyName,
                                                        name = "style") {
                                                    call("css") {
                                                        lambda {
                                                            cssDisplayInlineBlock()
                                                        }
                                                    }
                                                }
                                                call("span") {
                                                    lambda {
                                                        assignment("style") {
                                                            call("css") {
                                                                lambda {
                                                                    cssDisplayBlock()
                                                                    assignment(format = CODE::variablePropertyName,
                                                                            name = "fontWeight") {
                                                                        on({ +"FontWeight" }) {
                                                                            property("bold")
                                                                        }
                                                                    }
                                                                    assignment(format = CODE::variablePropertyName,
                                                                            name = "fontSize") {
                                                                        on({ number(1.17) }) {
                                                                            property("em")
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        expression {
                                                            on({
                                                                +"+"
                                                                keyword("this")
                                                                scope("@CONTACT")
                                                            }) {
                                                                property("name")
                                                            }
                                                        }
                                                    }
                                                }
                                                call("field") {
                                                    extensionFunction()
                                                    argument {
                                                        string("Email")
                                                    }
                                                    lambda(inline = true) {
                                                        call("a") {
                                                            argument(name = "href") {
                                                                string("mailto://")
                                                                +" + "
                                                                on({
                                                                    keyword("this")
                                                                    scope("@CONTACT")
                                                                }) {
                                                                    property("email")
                                                                }
                                                            }
                                                            lambda(inline = true) {
                                                                expression {
                                                                    on({
                                                                        +"+"
                                                                        keyword("this")
                                                                        scope("@CONTACT")
                                                                    }) {
                                                                        property("email")
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                call("field") {
                                                    extensionFunction()
                                                    argument {
                                                        string("Phone")
                                                    }
                                                    lambda(inline = true) {
                                                        expression {
                                                            on({
                                                                +"+"
                                                                keyword("this")
                                                                scope("@CONTACT")
                                                            }) {
                                                                property("phone")
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
        }
    }
}

fun BLOCK.cssDisplayBlock() {
    assignment(format = CODE::variablePropertyName, name = "display") {
        on({ +"Display" }) {
            property("block")
        }
    }
}

fun BLOCK.cssDisplayInlineBlock() {
    assignment(format = CODE::variablePropertyName, name = "display") {
        on({ +"Display" }) {
            property("inlineBlock")
        }
    }
}

fun BLOCK.cssHeight(em: Number) {
    assignment(format = CODE::variablePropertyName,
            name = "height") {
        on({ number(em) }) {
            property("em")
        }
    }
}

fun BLOCK.cssWidth(em: Number) {
    assignment(format = CODE::variablePropertyName,
            name = "width") {
        on({ number(em) }) {
            property("em")
        }
    }
}

fun BLOCK.cssMaxHeight(em: Number) {
    assignment(format = CODE::variablePropertyName,
            name = "maxHeight") {
        on({ number(em) }) {
            property("em")
        }
    }
}

fun usage(): DIV.() -> Unit = {
    h2 { +"Usage" }
    p { +"We are ready to use our DSL extensions. An example usage could look like this:" }
    sampleCode {
        declareFunction(name = "rolodex", returnType = "HTML.() -> Unit") {
            expression2 {
                block {
                    statement {
                        call(name = "head") {
                            lambda {
                                call(name = "link") {
                                    argument(name = "href") {
                                        string("styles.css")
                                    }
                                    argument(name = "rel") { string("stylesheet") }
                                }
                            }
                        }
                    }
                    statement {
                        call(name = "body") {
                            lambda {
                                call("rolodex") {
                                    extensionFunction()
                                    lambda {
                                        call("contact") {
                                            lambda {
                                                assignment(name = "name", format = CODE::variablePropertyName) {
                                                    inlineExpression { string("Finn Mertens") }
                                                }
                                                assignment(name = "email", format = CODE::variablePropertyName) {
                                                    inlineExpression { string("finnthehuman@hero.org") }
                                                }
                                                assignment(name = "phone", format = CODE::variablePropertyName) {
                                                    inlineExpression { string("+11325554321") }
                                                }
                                                assignment(name = "picture", format = CODE::variablePropertyName) {
                                                    inlineExpression { string("finn.jpeg") }
                                                }
                                            }
                                        }
                                        call("contact") {
                                            lambda {
                                                assignment(name = "name", format = CODE::variablePropertyName) {
                                                    inlineExpression { string("Bonnibel Bubblegum") }
                                                }
                                                assignment(name = "email", format = CODE::variablePropertyName) {
                                                    inlineExpression { string("princessbubblegum@candykingdom.gov") }
                                                }
                                                assignment(name = "phone", format = CODE::variablePropertyName) {
                                                    inlineExpression { string("+11235551234") }
                                                }
                                                assignment(name = "picture", format = CODE::variablePropertyName) {
                                                    inlineExpression { string("bubblegum.jpeg") }
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
    p {
        +"You can see this code in action in this demo project on "
        a(href = "https://github.com/wakingrufus/dsl-example") {
            +"GitHub"
        }
    }
}


fun conclusion(): DIV.() -> Unit = {
    h2 { +"Conclusion" }
    p {
        +"""
        Using a DSL lets me design my web page with minimal repetition and boilerplate.
        If this approach interests you, I encourage to check out the source code for my
    """.trimIndent()
        +" "
        a(href = "https://wakingrufus.neocities.org/") { +"Website" }
        +" on "
        a(href = "https://github.com/wakingrufus/website") { +"GitHub" }
        +", which is built entirely in Kotlin using DSLs."
    }
}

val staticWebAdditionalResources: DIV.() -> Unit = {
    h2 { +"Additional Resources" }
    p {
        +"This article was first posted on "
        a(href = "https://codetown.com/group/kotlin/forum/topics/kotlin-thursdays-static-web-with-kotlin-dsls-with-john-burns") { +"Kotlin Town" }
        +" along with a video demo of this code in action"
    }
    p { +"If you are interested in learning more about Kotlin DSLs, check out the following links:" }
    p { a(href = "https://proandroiddev.com/auto-generate-kotlin-dsl-f63342434154") { +"AutoDSL" } }
    p { a(href = "https://zsmb.co/kotlin-dsl-design-with-village-dsl/") { +"Kotlin DSL Design" } }
    p { a(href = "https://kotlinexpertise.com/create-dsl-with-kotlin/") { +"Create a DSL in Kotlin" } }
    p { +"If you are interested in learning more about the static web, check out the following links:" }
    p { a(href = "https://www.coredna.com/blogs/the-web-is-broken") { +"The Web is Broken" } }
    p { a(href = "https://speedcurve.com/blog/javascript-growth/") { +"JavaScript growth and third parties" } }
    p { a(href = "https://chriswere.neocities.org/blog.html#blog-experiment") { +"Chris Were's static blog" } }
    p {
        +"Discuss this post on "
        a(href = "https://mastodon.technology/web/statuses/101241850991374660") { +"Mastodon" }
        +"."
    }
}
