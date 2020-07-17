package com.github.wakingrufus.generationq

import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}
fun main(args: Array<String>) {
    val baseDir = File("build")
    logger.info { "upload" }
    MyWebsite().build(baseDir).apply {
        if (args.size == 1) {
            logger.info("uploading with apikey ${args[0]}")
            upload(neocities(args[0]))
            upload()
        } else if (args.size == 2) {
            logger.info("uploading with user ${args[0]} and password ${args[1]}")
            upload(neocities(args[0], args[1]))
            upload()
        } else {
            System.out.println("no uploaders configured")
        }
    }
}
