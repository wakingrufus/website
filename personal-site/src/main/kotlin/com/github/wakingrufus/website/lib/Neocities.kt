package com.github.wakingrufus.website.lib

import com.github.wakingrufus.website.sha1
import mu.KotlinLogging
import java.io.File

data class NeocitiesFileList(val result: String, val files: List<NeocitiesFile>)
data class NeocitiesFile(val path: String, val size: Long, val sha1Hash: String)

private val neocitiesLogger = KotlinLogging.logger {}
fun checkNeoCitiesFile(baseDir: File, neocitiesFileList: NeocitiesFileList, file: File): Boolean {
    val fileName = file.toRelativeString(baseDir)
    val existingFile = neocitiesFileList.files.find { it.path == fileName }
    if (existingFile == null) {
        neocitiesLogger.info { "file=$fileName is new" }
        return true
    }

    if (existingFile.size != file.length()) {
        neocitiesLogger.info { "file=$fileName has different size old=${existingFile.size} new=${file.length()}" }
        return true
    }

    val newFileSha = file.sha1()
    if (existingFile.sha1Hash != newFileSha) {
        neocitiesLogger.info { "file=$fileName has different sha1 old=${existingFile.sha1Hash} new=$newFileSha" }
        return true
    }

    neocitiesLogger.info { "file=$fileName has no changes" }
    return false
}
