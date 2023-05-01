package com.github.wakingrufus.website

import kotlinx.html.FlowOrInteractiveOrPhrasingContent
import kotlinx.html.IMG
import kotlinx.html.img
import java.util.Base64

fun FlowOrInteractiveOrPhrasingContent.imgFromResources(
    fileName: String,
    altText: String,
    configuration: IMG.() -> Unit = {}
) {
    val imageResource = this::class.java.classLoader.getResourceAsStream(fileName)
    val imageBase64 = Base64.getEncoder().encodeToString(imageResource.readBytes())
    img(src = "data:image/png;base64, $imageBase64", alt = altText) {
        configuration()
    }
}