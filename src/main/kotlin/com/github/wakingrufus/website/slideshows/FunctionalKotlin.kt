package com.github.wakingrufus.website.slideshows

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.CssStringPage
import com.github.wakingrufus.website.lib.Website
import com.github.wakingrufus.website.lib.code.*
import com.github.wakingrufus.website.lib.slides.*
import kotlinx.html.*

/**
 * Abstract:
 * A brief introduction to functional programming constructs in the Kotlin language.
 * We will explore some basic functional programming principles and look at Kotlin code samples which demonstrate these
 * principles. We will learn about the Kotlin standard functions, and what they can be used for.
 * We will also look at how to test functional Kotlin. The audience will walk away with an idea of how they can using
 * functional programming ideas in their code today to make their code and their tests cleaner
 * and easier to understand. This talk is designed for novices to functional programming, Kotlin, or both,
 * although even seasoned veterans may discover a few tricks to hone their technique.
 */

fun functionalKotlinSlideshow(): Website.() -> Unit = {
    slideshow(
            name = Paths.FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME,
            rootCss = CssStringPage(Paths.SLIDESHOW_CSS_PATH, MyStyles().slideShowStyles())) {
        titleSlide(title = "Functional Kotlin", subTitle = "And How to Test it", block = functionalKotlinTitleSlide())
        slide(title = "Functional Programming", block = functionalKotlinFunctionalProgramming())
        slide(title = "Immutable Data", block = functionalKotlinImmutableDataStory())
        slide(title = "Immutable Data", block = functionalKotlinImmutableData())
        slide(title = "Pure Functions - Deterministic", block = functionalKotlinDeterministicStory())
        slide(title = "Pure Functions - Non-Deterministic", block = functionalKotlinNonDeterministic())
        slide(title = "Pure Functions - Deterministic", block = functionalKotlinDeterministic())
        slide(title = "Pure Functions - Side Effects", block = functionalKotlinSideEffectsStory())
        slide(title = "Pure Functions - Side Effects", block = functionalKotlinSideEffectsCode())
        slide(title = "First Class Functions", block = firstClassFunctions())
        slide(title = "Functions are Non-Imperative", subTitle = "Imperative == order dependant", block = functionsAreNonImperative())
        slide(title = "Higher Order Functions", block = higherOrderFunctions())
        slide(title = "Kotlin Standard Scoping Functions", block = kotlinStandardFunctions())
        slide(title = "let", block = let())
        slide(title = "apply", block = apply())
        slide(title = "also", block = also())
        slide(title = "run", block = run())
        slide(title = "run (non-extension)", block = runNonExtension())
        slide(title = "Function Literals with Receivers", block = functionLiteralsWithReceivers())
        slide(title = "Kotlin is Multi-Paradigm", block = kotlinIsMultiParadigm())
        slide(title = "Functional Testing", block = functionalTesting())
        slide(title = "Recap", block = recap())
        slide(title = "More Resources", block = moreResources())
    }
}

fun functionalKotlinTitleSlide(): DIV.() -> Unit = {
    slideshowTitleFooter {
        slideshowTitleFooterLine { +"John Burns" }
        slideshowTitleFooterLine { +"wakingrufus@gmail.com" }
        slideshowTitleFooterLine {
            a(href = "http://wakingrufus.neocities.org") { +"http://wakingrufus.neocities.org" }
        }
    }

}

fun functionalKotlinFunctionalProgramming(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Data is immutable" }
            li {
                +"Functions are pure"
                ul {
                    li { +"Deterministic" }
                    li { +"No side effects" }
                }
            }
            li {
                +"Functions are \"first class\""
                ul {
                    li { +"Can be set as a variable" }
                }
            }
            li {
                +"Functions can be \"higher order\""
                ul {
                    li { +"Functions can take functions as arguments, or return a function" }
                }
            }
        }
        slidePicture("function.png", alt = "Diagram of a function")
    }
}

fun functionalKotlinImmutableDataStory(): DIV.() -> Unit = {
    slideContent {
        slidePicture(name = "groceries.png", alt = "Grocery List") {
            caption { +"Groceries" }
        }
    }
}

fun functionalKotlinImmutableData(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Thread safe" }
            li { +"Avoid temporal coupling" }
            li { +"No need for defensive copies" }
            li { +"Helps prevent side effects" }
            li { +"Easier to cache" }
        }

        slideCode {
            declareClass(name = "ImmutableValue", propsOnSeparateLines = false) {
                property(modifier = "val", name = "stringData", type = "String")
            }
            dataClass(name = "ImmutableDataValue", propsOnSeparateLines = false) {
                property(modifier = "val", name = "stringData", type = "String")
            }
            declareFunction("function") {
                body {
                    assignment(modifier = "val", name = "data", type = "ImmutableValue") {
                        inlineExpression {
                            call(name = "ImmutableValue", argsOnDifferentLines = false) {
                                argument(name = "stringData") {
                                    string("a")
                                }
                            }
                        }
                    }
                    statement {
                        inlineExpression {
                            +"data."
                            propertyName("stringData")
                        }
                    }
                    assignment(modifier = "val", name = "dataClass") {
                        inlineExpression {
                            call(name = "ImmutableDataValue", argsOnDifferentLines = false) {
                                argument(name = "stringData") {
                                    string("s")
                                }
                            }
                        }
                    }
                    assignment(modifier = "val", name = "dataClass2") {
                        inlineExpression {
                            +"dataClass."
                            call(name = "copy", argsOnDifferentLines = false) {
                                argument(name = "stringData") {
                                    string("a")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun functionalKotlinDeterministicStory(): DIV.() -> Unit = {
    slideContent {
        slidePicture("latte.jpg", alt = "Latte") {
            caption {
                +"© "
                a(href = "https://commons.wikimedia.org/wiki/User:Takeaway") { +"User:Takeaway" }
                +" / "
                a(href = "http://commons.wikimedia.org/") { +"Wikimedia Commons" }
                +" / "
                a(href = "http://creativecommons.org/licenses/by-sa/3.0/") { +"CC-BY-SA-3.0" }
            }
        }
    }
}

fun functionalKotlinNonDeterministic(): DIV.() -> Unit = {
    slideContent {
        slideCode {
            dataClass(name = "ChatMessage") {
                value(name = "user", type = "String")
                value(name = "timestamp", type = "Instant")
                value(name = "message", type = "String")
            }
            declareFunction(name = "newMessage",
                    returnType = "ChatMessage",
                    argsOnSeparateLines = false) {
                parameter(name = "message", type = "String")
                body {
                    expression {
                        keyword("return ")
                        constructor(name = "ChatMessage", baseIndentation = 1, argsOnDifferentLines = true) {
                            argument("user") {
                                +"System."
                                call(name = "getProperty", argsOnDifferentLines = false) {
                                    argument { string("user.name") }
                                }
                            }
                            argument("timestamp") {
                                +"Instant.now()"
                            }
                            argument("message") {
                                +"message"
                            }
                        }
                    }
                }
            }
        }
    }
}

fun functionalKotlinDeterministic(): DIV.() -> Unit = {
    slideContent {
        slideCode {
            dataClass(name = "ChatMessage") {
                value(name = "user", type = "String")
                value(name = "timestamp", type = "Instant")
                value(name = "message", type = "String")
            }
            declareFunction(name = "newMessage",
                    returnType = "ChatMessage") {
                parameter(name = "message", type = "String")
                parameter(name = "user", type = "String")
                parameter(name = "timestamp", type = "Instant")
                body {
                    expression {
                        keyword("return ")
                        constructor(name = "ChatMessage", baseIndentation = 1, argsOnDifferentLines = true) {
                            argument("user") {
                                +"user"
                            }
                            argument("timestamp") {
                                +"timestamp"
                            }
                            argument("message") {
                                +"message"
                            }
                        }
                    }
                }
            }
        }
    }
}

fun functionalKotlinSideEffectsStory(): DIV.() -> Unit = {
    slideContent {
        slidePicture("sideeffects.png", alt = "Medicine Bottle") {
        }
    }
}

fun functionalKotlinSideEffectsCode(): DIV.() -> Unit = {
    slideContent {
        slideCode {
            declareFunction(name = "addNewMessageSideEffect") {
                parameter(name = "messages", type = "ArrayList<ChatMessage>")
                parameter(name = "newMessage", type = "ChatMessage")
                body {
                    expression {
                        +("messages.")
                        call(name = "add", argsOnDifferentLines = false) {
                            argument { +"newMessage" }
                        }
                        comment("Modifies input")
                    }
                }
            }
            +"\n"
            declareProperty(modifier = "val", name = "externalMessages", type = "List<ChatMessage>") {
                constructor("ArrayList") {}
            }
            +"\n"
            declareFunction(name = "addNewMessageSideEffect2", argsOnSeparateLines = false) {
                parameter(name = "newMessage", type = "ChatMessage")
                body {
                    expression {
                        on({ propertyName("externalMessages") }) {
                            call(name = "add", argsOnDifferentLines = false) {
                                argument { +"newMessage" }
                            }
                        }
                        comment("Modifies something outside of scope")
                    }
                }
            }
            +"\n"
            declareFunction(name = "addNewMessage", returnType = "List<ChatMessage>") {
                parameter(name = "messages", type = "List<ChatMessage>")
                parameter(name = "newMessage", type = "ChatMessage")
                body {
                    statement {
                        returns {
                            on({ +"messages" }) {
                                call(name = "plus", argsOnDifferentLines = false) {
                                    argument { +"newMessage" }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun firstClassFunctions(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Classes are nouns" }
            li { +"Methods are verbs" }
            li { +"Functions are gerunds" }
            li { +"Kotlin has first class functions" }
            li { +"These replace the need for static methods" }
        }
        slideCode {
            declareFunction(name = "bigDecimalEquals", returnType = "Boolean", argsOnSeparateLines = true) {
                parameter(name = "one", type = "BigDecimal")
                parameter(name = "two", type = "BigDecimal")
                body {
                    statement {
                        returns {
                            on({ +"one" }) {
                                call(name = "compareTo") {
                                    argument { +"two" }
                                }
                                call("==") {
                                    infix()
                                    argument { number(0) }
                                }
                            }
                        }
                    }
                }
            }
            +"\n"
            declareFunction(
                    name = "bigDecimalEquals",
                    returnType = "Boolean",
                    argsOnSeparateLines = true
            ) {
                parameter(name = "one", type = "BigDecimal")
                parameter(name = "two", type = "BigDecimal")
                expression2 {
                    on({ +"one" }) {
                        call(name = "compareTo") {
                            argument { +"two" }
                        }
                        call("==") {
                            infix()
                            argument { number(0) }
                        }
                    }
                }
            }
            +"\n"
            declareProperty(modifier = "val", name = "bigDecimalEquals", type = "Boolean") {
                +"one.compareTo(two) == "
                number(0)
            }
        }
    }
}

fun functionsAreNonImperative(): DIV.() -> Unit = {
    slideContent {
        slideCode {
            declareFunction(
                    name = "fixChatMessageImperative",
                    argsOnSeparateLines = true,
                    returnType = "List<String>") {
                parameter(name = "messages", type = "List<ChatMessage>")
                body {
                    assignment(modifier = "var", name = "fixedMessages", type = "List<String>") {
                        inlineExpression {
                            +"ArrayList()"
                        }
                    }
                    statement {
                        inlineExpression {
                            keyword("for")
                            +" (m "
                            keyword("in")
                            +" messages)"
                        }
                        inlineExpression {
                            block(indentation = this@declareFunction.indentation + 1) {
                                assignment(modifier = "var", name = "fixedMessage", type = "String?") {
                                    inlineExpression {
                                        +"m."
                                        propertyName("message")
                                    }
                                }
                                expression {
                                    comment("What if you move the following block")
                                }
                                statement {
                                    inlineExpression {
                                        keyword("if")
                                        +" (m."
                                        propertyName("message")
                                        +"."
                                        functionName("contains")
                                        +"("
                                        string("bad word")
                                        +")"
                                    }
                                    inlineExpression {
                                        block(indentation = this@block.indentation + 1) {
                                            assignment(name = "fixedMessage") {
                                                inlineExpression {
                                                    +"m."
                                                    propertyName("message")

                                                }
                                                chain {
                                                    call(name = "replace") {
                                                        argument { string("bad word") }
                                                        argument { string("****") }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                statement {
                                    inlineExpression {
                                        keyword("if")
                                        +" (m."
                                        propertyName("user")
                                        +" == "
                                        string("SYSTEM")
                                        +")"
                                    }
                                    inlineExpression {
                                        block(indentation = this@block.indentation + 1) {
                                            assignment(name = "fixedMessage") {
                                                inlineExpression {
                                                    keyword("null")
                                                }
                                            }
                                        }
                                    }
                                }
                                statement {
                                    inlineExpression {
                                        keyword("if")
                                        +" (fixedMessage != "
                                        keyword("null")
                                        +")"
                                    }
                                    inlineExpression {
                                        block(indentation = this@block.indentation + 1) {
                                            assignment(name = "fixedMessage") {
                                                inlineExpression {
                                                    +"fixedMessage."
                                                    functionName("toLowerCase")
                                                    +"()"
                                                }
                                            }
                                        }
                                    }
                                }
                                expression { comment("to here?") }
                                statement {
                                    inlineExpression {
                                        keyword("if")
                                        +" (fixedMessage != "
                                        keyword("null")
                                        +")"
                                    }
                                    inlineExpression {
                                        block(indentation = this@block.indentation + 1) {
                                            assignment(name = "fixedMessages", operator = "+=") {
                                                inlineExpression { +"fixedMessage" }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    returns {
                        on({ +"fixedMessages" }) {}
                    }
                }
            }
        }

        slideCode {
            declareFunctionExpression(name = "fixChatMessagesNotImperative",
                    returnType = "List<String>",
                    parameters = listOf(PARAMETER(name = "messages", type = "List<ChatMessage>"))) {
                on({
                    +"\n"
                    indent(1)
                    +"messages"
                }) {
                    breakAndCall(name = "filter") {
                        lambda(inline = true) {
                            expression {
                                propertyName("user")
                                +" != "
                                string("SYSTEM")
                            }
                        }
                    }
                    breakAndCall(name = "map") {
                        lambda(inline = true) {
                            expression {
                                +"it."
                                propertyName("messages")
                                +"."
                                call(name = "replace", argsOnDifferentLines = false) {
                                    argument { string("bad word") }
                                    argument { string("****") }
                                }
                            }
                        }
                    }
                    breakAndCall(name = "map", argsOnDifferentLines = false) {
                        argument { +"String::toLowerCase" }
                    }
                }
            }
        }
    }
}

fun higherOrderFunctions(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Higher order functions can take function as a parameter" }
            li { +"Higher order functions can return a function" }
        }
        slideCode {
            declareFunctionExpression(
                    name = "twice",
                    argsOnSeparateLines = false,
                    returnType = "(T)->T",
                    parameters = listOf(PARAMETER(name = "f", type = "(T)->T"))) {
                +"f(f(it))"
            }
            declareFunctionExpression(
                    name = "f",
                    argsOnSeparateLines = false,
                    parameters = listOf(PARAMETER(name = "x", type = "Int"))) {
                +"x + "
                number(3)
            }
            block {
                expression {
                    +"println(twice(::f)("
                    number(7)
                    +"))"
                    comment("13")
                }
            }
        }
    }
}

fun kotlinStandardFunctions(): DIV.() -> Unit = {
    slideContent {
        slideTable {
            headers(listOf("Function", "extension", "`this` or `it`", "Block return"))
            tbody {
                row(listOf("let", "yes", "it", "yes"))
                row(listOf("run", "yes", "this", "yes"))
                row(listOf("run", "no", "--", "yes"))
                row(listOf("with", "no", "this", "yes"))
                row(listOf("apply", "yes", "this", "no"))
                row(listOf("also", "yes", "it", "no"))
            }
        }
    }
}

fun let(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"let" }
            ul {
                li { +"Apply a transform" }
                li { +"Restrict scope" }
                li { +"Idiomatic 'if not null'" }
                li { +"Map nullable value if not null" }
            }
        }
        slideCode {
            dataClass(name = "Data", propsOnSeparateLines = false) {
                property(modifier = "val", name = "value", type = "Int?")
            }
            declareFunction(name = "getFromDatabase", returnType = "Data?") {
                body {
                    expression { +"..." }
                }
            }
            +"\n"
            declareFunction(name = "getValue", returnType = "BigDecimal") {
                body {
                    returns {
                        on(subject = {
                            call(name = "getFromDatabase")
                        }) {
                            call("let", baseIndentation = 1, nullSafe = true) {
                                lambda {
                                    expression {
                                        on({ +"it" }) {
                                            call(name = "toBigDecimal")
                                        }
                                    }
                                }
                            }
                            elvis {
                                on({ +"BigDecimal" }) {
                                    property(name = "ZERO")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


fun apply(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"apply" }
            ul {
                li { +"Initialize an object without its own fluent builder api" }
                li { +"creating a builder interface on top of void/Unit methods/operations" }
            }
        }
        slideCode {
            declareFunction("buildObject", returnType = "Calendar") {
                body {
                    returns {
                        on({ +"Calendar" }) {
                            call(name = "getInstance") {}
                            call(name = "apply", argsOnDifferentLines = false, baseIndentation = 1) {
                                lambda {
                                    expression {
                                        call(name = "set", argsOnDifferentLines = false) {
                                            argument { number(2017) }
                                            argument { number(3) }
                                            argument { number(1) }
                                            argument { number(4) }
                                            argument { number(56) }
                                            argument { number(34) }
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


fun also(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"also" }
            ul {
                li { +"Perform side effects" }
            }
        }
        slideCode {
            declareFunction(name = "functionWithSideEffect", returnType = "String") {
                parameter(name = "input", type = "String")
                body {
                    returns {
                        call(name = "doStuff") {
                            argument { +"input" }
                        }
                        call(name = "also") {
                            extensionFunction()
                            lambda {
                                statement {
                                    expression {
                                        on({ propertyName("log") }) {
                                            call(name = "log") {
                                                argument {
                                                    string("doing stuff to ")
                                                    +" + input"
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


fun run(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"run" }
            ul {
                li { +"Initialize and transform" }
            }
        }
        slideCode {
            declareProperty(modifier = "val", name = "dayOfYear", type = "Int") {
                on({ +"Calendar" }) {
                    call(name = "getInstance") {}
                    call(name = "run") {
                        lambda {
                            expression {
                                call(name = "set") {
                                    argument {
                                        +"Calendar."
                                        propertyName("YEAR")
                                    }
                                    argument { number(2030) }
                                }
                            }
                            expression {
                                call(name = "get") {
                                    argument {
                                        +"Calendar."
                                        propertyName("DAY_OF_YEAR")
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

fun runNonExtension(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"run (no extension)" }
            ul {
                li { +"Restrict scope and transform" }
            }
        }
        slideCode {
            declareProperty(modifier = "val", name = "dayOfYear", type = "Int") {
                call(name = "run") {
                    lambda {
                        assignment(modifier = "val", name = "cal") {
                            on({ +"Calendar" }) {
                                call(name = "getInstance")
                            }
                        }
                        statement {
                            on({ propertyName("cal") }) {
                                call(name = "set") {
                                    argument {
                                        +"Calendar."
                                        propertyName("YEAR")
                                    }
                                    argument { number(2030) }
                                }
                            }
                        }
                        statement {
                            on({ propertyName("cal") }) {
                                call(name = "get") {
                                    argument {
                                        +"Calendar."
                                        propertyName("DAY_OF_YEAR")
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

fun functionLiteralsWithReceivers(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Can be used similar to apply" }
            li { +"Building block of DSLs" }
        }
        slideCode {
            declareFunction(extentionOf = "String", name = "build", returnType = "String") {
                parameter(name = "block", type = "String.() -> Unit")
                body {
                    expression {
                        keyword("return ")
                        on({
                            constructor(name = "StringBuilder") {
                                argument { keyword("this") }
                            }
                        }) {
                            call(name = "apply") {
                                argument { +"block" }
                            }
                            call(name = "toString") {}
                        }

                    }
                }
            }
            +"\n"
            declareFunction("use") {
                body {
                    expression {
                        on({ string("") }) {
                            call("build", baseIndentation = 1) {
                                lambda {
                                    expression {
                                        call("append") {
                                            argument { string("34") }
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

fun kotlinIsMultiParadigm(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"No program is 100% pure functional" }
            li { +"Push state out to the ‘edges’." }
            li { +"Place application’s core data and logic in immutable constructs (value types)." }
            li { +"Represent state as objects with mutable references to immutable core constructs." }
            li { +"Shell: OOP, state, IO, Side effects, GUI" }
            li { +"Core: Pure functions, stateless, logic" }
            li { +"Use functional parameters to inject functions with side effects" }
            li { +"Use functional parameters on the boundary of your shell/core" }
        }
    }
}

fun sayHello(printFn: (String) -> Unit = System.out::println,
             readFn: () -> String? = ::readLine) {
    printFn(readFn()?.let {
        "Hello, $it"
    }.orEmpty())
}

fun functionalTesting(): DIV.() -> Unit = {
    slideContent {
        slideCode {
            declareFunction(name = "sayHello") {
                body {
                    expression {
                        on({
                            +"System."
                            propertyName("out")
                        }) {
                            call(name = "println") {
                                argument {
                                    on({
                                        call(name = "readLine") {
                                            packageFunction()
                                        }
                                    }) {
                                        call(name = "let", baseIndentation = 1, nullSafe = true) {
                                            extensionFunction()
                                            lambda {
                                                expression {
                                                    string("Hello, ")
                                                    +" + it"
                                                }
                                            }
                                        }
                                        call("orElseEmpty") {
                                            extensionFunction()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            declareFunction(name = "sayHello", argsOnSeparateLines = true) {
                parameter(name = "printFn", type = "(String) -> Unit") {
                    +"System."
                    propertyName("out")
                    +"::println"
                }
                parameter(name = "readFn", type = "() -> String?") {
                    +"::readLine"
                }
                body {
                    expression {

                        call(name = "printFn") {
                            argument {
                                on({
                                    call(name = "readLine") {}
                                }) {
                                    call(name = "let", baseIndentation = 1, nullSafe = true) {
                                        extensionFunction()
                                        lambda {
                                            expression {
                                                string("Hello, ")
                                                +" + it"
                                            }
                                        }
                                    }
                                    call("orElseEmpty") {
                                        extensionFunction()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        slideCode {
            keyword("@Test")
            +"\n"
            declareFunction(name = "`test sayHello`") {
                body {
                    expression {
                        declareProperty(modifier = "val", name = "sb") {
                            constructor("StringBuilder") {}
                        }
                    }
                    expression {
                        call("sayHello", argsOnDifferentLines = true, baseIndentation = 1) {
                            packageFunction()
                            argument(name = "printFn") {
                                +"{ sb.append(it) }"
                            }
                            argument(name = "read") {
                                block(inline = true) { expression { string("input") } }
                            }
                        }
                    }
                    expression {
                        call("assertEquals") {
                            argument {
                                string("Hello, input")
                            }
                            argument {
                                on({ +"sb" }) {
                                    call("toString") {}
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun recap(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Principles of functional programming" }
            ul {
                li { +"Immutable data" }
                li { +"Pure functions" }
                li { +"First-class functions" }
                li { +"Higher-order functions" }
            }
            li { +"Kotlin features" }
            ul {
                li { +"Kotlin standard functions" }
                li { +"Function literals with receivers" }
            }

            li {
                +"Use functional parameters to “inject” the functions which have side effects into "
                +"our functions under test in order to allow a lambda “mock” to be injected for testing."
            }
        }
    }
}

fun moreResources(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li {
                a(href = "http://www.yegor256.com/2014/06/09/objects-should-be-immutable.html") {
                    +"Objects Should Be Immutable"
                }
                +" - "
                a(href = "http://www.yegor256.com") { +"Yegor Bugayenko" }
            }
            li {
                a(href = "https://www.infoq.com/presentations/Value-Values") { +"The Value of Values" }
                +" - "
                a(href = "https://twitter.com/richhickey") { +"Rich Hickey" }
            }
            li {
                a(href = "https://gist.github.com/kbilsted/abdc017858cad68c3e7926b03646554e") {
                    +"Functional Core, Imperative Shell"
                }
                +" - "
                a(href = "http://firstclassthoughts.co.uk/") { +"Kasper B. Graversen" }
            }
            li {
                a(href = "https://kotlinexpertise.com/coping-with-kotlins-scope-functions/") {
                    +"Coping with Kotlin's Scope Functions"
                }
                +" - "
                a(href = "https://simon-wirtz.de/") { +"Simon Wirtz" }
            }
        }
    }
}
