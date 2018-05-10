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
            text("class ImmutableValue(val stringData: String)\n\n" +
                    "data class ImmutableDataValue(val stringData: String)\n\n" +
                    "fun function(){\n" +
                    "  val data = ImmutableValue(stringData = \"a\")\n" +
                    "  data.stringData\n" +
                    "  val dataClass = ImmutableDataValue(stringData = \"s\")\n" +
                    "  val dataClass2 = dataClass.copy(stringData = \"a\")\n" +
                    "  dataClass2.stringData\n" +
                    "}")
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
            declareVal(name = "externalMessages", assignment = ": ArrayList<ChatMessage> = ArrayList()")
        }
        slideCode {
            declareFunction(name = "addNewMessageSideEffect") {
                +("(messages: ArrayList<ChatMessage>, newMessage: ChatMessage){\n" +
                        "  messages.add(newMessage)")
                comment("// Modifies input")
                +"\n} "
            }
        }
        slideCode {
            declareFunction(name = "addNewMessageSideEffect2") {
                +("(newMessage: ChatMessage) {\n")
                property("  externalMessages")
                +".add(newMessage)"
                comment("// Modifies something outside of scope")
                +"\n}"
            }
        }
        slideCode {
            declareFunction(
                    name = "addNewMessage",
                    args = "messages: ArrayList<ChatMessage>, newMessage: ChatMessage",
                    returnType = "List<ChatMessage>") {
                declareReturn {
                    +"messages.plus(newMessage)"
                }
            }
        }
    }
}