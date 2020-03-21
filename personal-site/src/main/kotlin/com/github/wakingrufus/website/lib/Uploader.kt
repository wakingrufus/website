package com.github.wakingrufus.website.lib

import com.github.kittinunf.fuel.httpUpload
import com.github.kittinunf.result.Result
import com.github.wakingrufus.website.WebsiteDsl
import mu.KLogging
import java.io.File

@WebsiteDsl
interface Uploader {
    fun upload(baseDir: File, file: File)
}

class NeocitiesUploader(val username: String, val password: String) : Uploader {
    companion object : KLogging()

    override fun upload(baseDir: File, file: File) {
        logger.info("Uploading file: ${file.toRelativeString(baseDir)}")
        "https://neocities.org/api/upload"
                .httpUpload()
                .name({ file.toRelativeString(baseDir) })
                .authenticate(username, password)
                .source { _, _ -> file }
                .responseString().let { (request, response, result) ->
                    when (result) {
                        is Result.Failure -> {
                            logger.error("error uploading: ${String(result.getException().errorData)}",
                                    result.getException().exception)
                        }
                        is Result.Success -> {
                            logger.info { result.get() }
                        }
                    }

                }
    }
}

fun neocities(username: String, password: String): Uploader {
    return NeocitiesUploader(username = username, password = password)
}

fun neocities(apiKey: String): Uploader {
    return NeocitiesAPiKeyUploader(apiKey = apiKey)
}

class NeocitiesAPiKeyUploader(val apiKey: String) : Uploader {
    companion object : KLogging()

    override fun upload(baseDir: File, file: File) {
        logger.info("Uploading file: ${file.toRelativeString(baseDir)}")
        "https://neocities.org/api/upload"
                .httpUpload()
                .name({ file.toRelativeString(baseDir) })
                .header("Authorization" to "Bearer $apiKey")
                .source { _, _ -> file }
                .responseString().let { (request, response, result) ->
                    when (result) {
                        is Result.Failure -> {
                            logger.error("error uploading: ${String(result.getException().errorData)}",
                                    result.getException().exception)
                        }
                        is Result.Success -> {
                            logger.info { result.get() }
                        }
                    }

                }
    }
}