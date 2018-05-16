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

fun functionalKotlinSlideshow(): Website.() -> Unit = {
    slideshow(
            name = Paths.FUNCTIONAL_KOTLIN_SLIDESHOW_BASE_NAME,
            rootCss = CssStringPage(Paths.SLIDESHOW_CSS_PATH, MyStyles().slideShowStyles())) {
        titleSlide(title = "Functional Kotlin", block = functionalKotlinTitleSlide())
        slide(title = "Functional Programming", block = functionalKotlinFunctionalProgramming())
        slide(title = "Immutable Data", block = functionalKotlinImmutableDataStory())
        slide(title = "Immutable Data", block = functionalKotlinImmutableData())
        slide(title = "Pure functions - Non-Deterministic", block = functionalKotlinNonDeterministic())
        slide(title = "Pure functions - Deterministic", block = functionalKotlinDeterministic())
        slide(title = "Pure Functions - Side Effects", block = functionalKotlinSideEffectsStory())
        slide(title = "Pure Functions - Side Effects", block = functionalKotlinSideEffectsCode())
        slide(title = "First Class Functions", block = functionalKotlinJavaNoFirstClassFunctions())
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
    splitSlide(leftBlock = {
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
    }) {

        slidePicture("function.png")
    }
}

fun functionalKotlinImmutableDataStory(): DIV.() -> Unit = {
    singleSlide {
        slidePicture("groceries.png") {
            center()
        }
    }
}

fun functionalKotlinImmutableData(): DIV.() -> Unit = {
    singleSlide {
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
                    indent(2)
                    declareProperty(modifier = "val", name = "data", type = "ImmutableValue") {
                        +"ImmutableValue(stringData = \"a\")"
                    }
                    indent(2)
                    +"data."
                    propertyName("stringData")
                    +"\n"
                    indent(2)
                    declareProperty(modifier = "val", name = "dataClass"){
                        +"ImmutableDataValue(stringData = \"s\")"
                    }
                    indent(2)
                    declareProperty(modifier = "val", name = "dataClass2"){
                        +"dataClass.copy(stringData = \"a\")"
                    }
                }
            }
        }
    }
}

fun functionalKotlinNonDeterministic(): DIV.() -> Unit = {
    singleSlide {
        slideCode {
            text("data class ChatMessage(val user: String, val timestamp: Instant, val message: String)\n\n" +
                    "fun newMessageNonDeterministic(message: String): ChatMessage {\n" +
                    "  return ChatMessage(\n" +
                    "    user = System.getProperty(\"user.name\"),\n" +
                    "    timestamp = Instant.now(),\n" +
                    "    message = message)\n" +
                    "}\n\n" +
                    "@Test\n" +
                    "fun `test newMessageNonDeterministic`() {\n" +
                    "  val firstCall = newMessageNonDeterministic(\"message\")\n" +
                    "  Thread.sleep(10L)\n" +
                    "  assertNotEquals(firstCall, newMessageNonDeterministic(\"message\"))\n" +
                    "}")
        }
    }
}

fun functionalKotlinDeterministic(): DIV.() -> Unit = {
    singleSlide {
        slideCode {
            text("data class ChatMessage(val user: String, val timestamp: Instant, val message: String)\n\n" +
                    "fun newMessageDeterministic(user: String, timestamp: Instant, message: String): ChatMessage {\n" +
                    "  return ChatMessage(\n" +
                    "    user = user,\n" +
                    "    timestamp = timestamp,\n" +
                    "    message = message)\n" +
                    "}\n\n" +
                    "@Test\n" +
                    "fun newMessageDeterministic() {\n" +
                    "  val time = Instant.now()\n" +
                    "  val user = System.getProperty(\"user.name\")\n" +
                    "  val firstCall = newMessageDeterministic( user, time,\"message\")\n" +
                    "  Thread.sleep(10L)\n" +
                    "  assertEquals(firstCall, newMessageDeterministic( user, time,\"message\"))\n" +
                    "}")
        }
    }
}

fun functionalKotlinSideEffectsStory(): DIV.() -> Unit = {
    singleSlide {
        slidePicture("sideeffects.png") {
            center()
        }
    }
}

fun functionalKotlinSideEffectsCode(): DIV.() -> Unit = {
    singleSlide {
        slideCode {
            declareProperty(modifier = "val", name = "externalMessages", type = "ArrayList<ChatMessage>") {
                +"ArrayList()"
            }
            +"\n"
            declareFunction(name = "addNewMessageSideEffect") {
                argument(name = "messages", type = "ArrayList<ChatMessage>")
                argument(name = "newMessage", type = "ChatMessage")
                body {
                    indent(2)
                    +("messages.add(newMessage) ")
                    comment("// Modifies input")
                }
            }

            declareFunction(name = "addNewMessageSideEffect2") {
                argument(name = "newMessage", type = "ChatMessage")
                body {
                    indent(2)
                    propertyName("externalMessages")
                    +".add(newMessage)"
                    comment("// Modifies something outside of scope")
                }
            }

            declareFunction(name = "addNewMessage", returnType = "List<ChatMessage>") {
                argument(name = "messages", type = "ArrayList<ChatMessage>")
                argument(name = "newMessage", type = "ChatMessage")
                body {
                    indent(2)
                    declareReturn {
                        +"messages.plus(newMessage)"
                    }
                }
            }
        }
    }
}

fun functionalKotlinJavaNoFirstClassFunctions(): DIV.() -> Unit = {
    singleSlide {
        slideCode {
            declareClass(modifiers = listOf("public"), name = "NoFirstClassFunctions") {
                function(name = "NoFirstClassFunctions") {
                    body {
                        indent(2)
                        keyword("throw")
                        +" "
                        keyword("new")
                        +(" UnsupportedOperationException();")
                    }
                }
            }
        }
    }
}