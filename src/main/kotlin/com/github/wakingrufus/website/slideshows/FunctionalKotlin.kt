package com.github.wakingrufus.website.slideshows

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.CssStringPage
import com.github.wakingrufus.website.lib.Website
import com.github.wakingrufus.website.lib.code.*
import com.github.wakingrufus.website.lib.slides.*
import kotlinx.html.DIV
import kotlinx.html.a
import kotlinx.html.li
import kotlinx.html.ul

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
        titleSlide(title = "Functional Kotlin", block = functionalKotlinTitleSlide())
        slide(title = "Functional Programming", block = functionalKotlinFunctionalProgramming())
        slide(title = "Immutable Data", block = functionalKotlinImmutableDataStory())
        slide(title = "Immutable Data", block = functionalKotlinImmutableData())
        slide(title = "Pure Functions - Deterministic", block = functionalKotlinDeterministicStory())
        slide(title = "Pure Functions - Non-Deterministic", block = functionalKotlinNonDeterministic())
        slide(title = "Pure Functions - Deterministic", block = functionalKotlinDeterministic())
        slide(title = "Pure Functions - Side Effects", block = functionalKotlinSideEffectsStory())
        slide(title = "Pure Functions - Side Effects", block = functionalKotlinSideEffectsCode())
        slide(title = "First Class Functions", block = functionalKotlinJavaNoFirstClassFunctions())
        slide(title = "First Class Functions", block = firstClassFunctions())
        slide(title = "Functions are Non-Imperative", block = functionsAreNonImperative())
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
            li { +"Prevent null references" }
            li { +"Avoid Identity mutability" }
        }

        slideCode {
            declareClass(name = "ImmutableValue") {
                property(modifier = "val", name = "stringData", type = "String")
            }
            dataClass(name = "ImmutableDataValue") {
                property(modifier = "val", name = "stringData", type = "String")
            }
            declareFunction("function") {
                body {
                    line(indentation = 2) {
                        declareProperty(modifier = "val", name = "data", type = "ImmutableValue") {
                            +"ImmutableValue(stringData = \"a\")"
                        }
                    }
                    line(indentation = 2) {
                        +"data."
                        propertyName("stringData")
                    }
                    line(indentation = 2) {
                        declareProperty(modifier = "val", name = "dataClass") {
                            +"ImmutableDataValue(stringData = \"s\")"
                        }
                    }
                    line(indentation = 2) {
                        declareProperty(modifier = "val", name = "dataClass2") {
                            +"dataClass.copy(stringData = \"a\")"
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
                returnStatement {
                    call(name = "ChatMessage", baseIndentation = 1) {
                        argument("user") {
                            +"System.getProperty(\"user.name\")"
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
                returnStatement {
                    call(name = "ChatMessage", baseIndentation = 1) {
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

fun functionalKotlinSideEffectsStory(): DIV.() -> Unit = {
    slideContent {
        slidePicture("sideeffects.png", alt = "Medicine Bottle") {
        }
    }
}

fun functionalKotlinSideEffectsCode(): DIV.() -> Unit = {
    slideContent {
        slideCode {
            line {
                declareProperty(modifier = "val", name = "externalMessages", type = "ArrayList<ChatMessage>") {
                    +"ArrayList()"
                }
            }
            line {
                declareFunction(name = "addNewMessageSideEffect") {
                    parameter(name = "messages", type = "ArrayList<ChatMessage>")
                    parameter(name = "newMessage", type = "ChatMessage")
                    body {
                        line {
                            indent(2)
                            +("messages.add(newMessage) ")
                            comment("// Modifies input")
                        }
                    }
                }
            }
            line {
                declareFunction(name = "addNewMessageSideEffect2", argsOnSeparateLines = false) {
                    parameter(name = "newMessage", type = "ChatMessage")
                    body {
                        line {
                            indent(2)
                            propertyName("externalMessages")
                            +".add(newMessage)"
                            comment("// Modifies something outside of scope")
                        }
                    }
                }
            }
            line {
                declareFunction(name = "addNewMessage", returnType = "List<ChatMessage>") {
                    parameter(name = "messages", type = "List<ChatMessage>")
                    parameter(name = "newMessage", type = "ChatMessage")
                    returnStatement {
                        +"messages.plus(newMessage)"
                    }
                }
            }
        }
    }
}

fun functionalKotlinJavaNoFirstClassFunctions(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Pure functions don't rely on state" }
            li { +"Java does not have first-class functions" }
            li { +"In Java, static utility classes contain pure functions" }
        }
        slideCode {
            line {
                keyword("public class")
                +" "
                +"UtilityClass {"
            }
            line {
                indent(1)
                keyword("public")
                +" "
                +"UtilityClass() {"
            }
            line {
                indent(2)
                keyword("throw new")
                +" "
                +("UnsupportedOperationException();")
            }
            line {
                indent(1)
                +"}"
            }
            line {
                indent(1)
                keyword("public static boolean")
                +" "
                +"bigDecimalEquals("
            }
            line {
                indent(3)
                +"BigDecimal one, "
            }
            line {
                indent(3)
                +"BigDecimal two) {"
            }
            line {
                indent(2)
                declareReturn { +"one.compareTo(two) == 0;" }
            }
            line {
                indent(1)
                +"}"
            }
            line { +"}" }
        }
    }
}

fun firstClassFunctions(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Kotlin does not have static methods/fields" }
            li { +"Kotlin has companion objects instead" }
            li { +"There are also first class functions" }
        }
        slideCode {
            declareClass(name = "UtilityClass") {
                companionObject {
                    line {
                        +"{"
                    }
                    line {
                        indent(2)
                        declareProperty(modifier = "val", name = "staticValue", type = "String") {
                            +"\"STRING\""
                        }
                    }
                    declareFunction(name = "pureFunction", returnType = "String", indentation = 2) {
                        returnStatement {
                            +"\"MAGIC STRING\""
                        }
                    }
                    line {
                        indent(1)
                        +"}"
                    }
                }
            }
            declareFunction(name = "bigDecimalEquals", returnType = "Boolean") {
                parameter(name = "one", type = "BigDecimal")
                parameter(name = "two", type = "BigDecimal")
                returnStatement {
                    +"one.compareTo(two) == "
                    number(0)
                }
            }
            declareFunctionExpression(
                    name = "bigDecimalEquals",
                    returnType = "Boolean",
                    parameters = listOf(
                            PARAMETER(name = "one", type = "BigDecimal"),
                            PARAMETER(name = "two", type = "BigDecimal")
                    )) {
                +"one.compareTo(two) == "
                number(0)
            }
            line {
                declareProperty(modifier = "val", name = "bigDecimalEquals", type = "Boolean") {
                    +"one.compareTo(two) == "
                    number(0)
                }
            }
        }
    }
}

fun functionsAreNonImperative(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"Imperative == order dependant" }
        }
        slideCode {
            declareFunction(name = "fixChatMessageImperative", returnType = "List<String>", argsOnSeparateLines = false) {
                parameter(name = "messages", type = "List<String>")
                body {
                    line {
                        declareProperty(modifier = "val", name = "fixedMessages", type = "List<String>") {
                            call(name = "ArrayList") {}
                        }
                    }
                    block {
                        prefix {
                            keyword("for")
                            +" (m "
                            keyword("in")
                            +" messages)"
                        }

                        line {
                            declareProperty(modifier = "var", name = "fixedMessage", type = "String?") {
                                +"m."
                                propertyName("message")
                            }
                        }
                        block {
                            prefix {
                                keyword("if")
                                +" (m."
                                propertyName("message")
                                +"."
                                functionName("contains")
                                +"("
                                string("bad word")
                                +")"
                            }
                            line {
                                +"fixedMessage = "
                                +"m."
                                propertyName("message")
                                +"."
                                functionName("replace")
                                +"("
                                string("bad word")
                                +","
                                string("****")
                                +")"

                            }

                        }
                        block {
                            prefix {
                                keyword("if")
                                +" (m."
                                propertyName("user")
                                +" == "
                                string("SYSTEM")
                            }
                            line {
                                +"fixedMessage = "
                                keyword("null")
                            }

                        }
                    }
                }
            }
        }
    }
}