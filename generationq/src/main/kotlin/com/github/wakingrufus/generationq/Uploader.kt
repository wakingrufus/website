package com.github.wakingrufus.generationq

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpUpload
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.map
import com.github.wakingrufus.website.WebsiteDsl
import mu.KLogging
import java.io.File
import java.io.InputStream

@WebsiteDsl
interface Uploader {
    fun upload(baseDir: File, file: File)
    fun check(baseDir: File, file: File): Boolean
}

class NeocitiesUploader(val username: String, val password: String) : Uploader {
    companion object : KLogging()

    private val existingFiles: NeocitiesFileList by lazy {
        "https://neocities.org/api/list"
                .httpGet()
                .authenticate(username, password)
                .responseObject(KlaxonDeserializer()).third.map {
            NeocitiesFileList(
                    result = it.string("result") ?: "",
                    files = it.array<JsonObject>("files").orEmpty().map {
                        NeocitiesFile(path = it.string("path").orEmpty(),
                                size = it.long("size") ?: 0L,
                                sha1Hash = it.string("sha1_hash").orEmpty())
                    })
        }.get()
    }

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

    override fun check(baseDir: File, file: File): Boolean {
        return checkNeoCitiesFile(baseDir, existingFiles, file)
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

    private val existingFiles: NeocitiesFileList by lazy {
        "https://neocities.org/api/list"
                .httpGet()
                .header("Authorization" to "Bearer $apiKey")
                .responseObject(KlaxonDeserializer()).third.map {
            NeocitiesFileList(
                    result = it.string("result") ?: "",
                    files = it.array<JsonObject>("files").orEmpty().map {
                        NeocitiesFile(path = it.string("path").orEmpty(),
                                size = it.long("size") ?: 0L,
                                sha1Hash = it.string("sha1_hash").orEmpty())
                    })
        }.get()
    }

    override fun check(baseDir: File, file: File): Boolean {
        return checkNeoCitiesFile(baseDir, existingFiles, file)
    }

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

class NeocitiesTestUploader(val apiKey: String) : Uploader {
    companion object : KLogging()

    private val existingFiles: NeocitiesFileList by lazy {
        "https://neocities.org/api/list"
                .httpGet()
                .header("Authorization" to "Bearer $apiKey")
                .responseObject(KlaxonDeserializer()).third.map {
            NeocitiesFileList(
                    result = it.string("result") ?: "",
                    files = it.array<JsonObject>("files").orEmpty().map {
                        NeocitiesFile(path = it.string("path").orEmpty(),
                                size = it.long("size") ?: 0L,
                                sha1Hash = it.string("sha1_hash").orEmpty())
                    })
        }.get()
    }

    override fun check(baseDir: File, file: File): Boolean {
        return checkNeoCitiesFile(baseDir, existingFiles, file)
    }

    override fun upload(baseDir: File, file: File) {
        logger.info("NOT Uploading file: ${file.toRelativeString(baseDir)}")
    }
}

class KlaxonDeserializer : ResponseDeserializable<JsonObject> {
    override fun deserialize(inputStream: InputStream): JsonObject? {
        return Parser.default().parse(inputStream) as? JsonObject
    }
}

class KlaxonArrayDeserializer : ResponseDeserializable<JsonArray<JsonObject>> {
    override fun deserialize(inputStream: InputStream): JsonArray<JsonObject>? {
        return Parser.default().parse(inputStream) as? JsonArray<JsonObject>
    }
}
