package com.github.wakingrufus.website

import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import kotlin.experimental.and

fun File.sha1(): String? {
    val digest = MessageDigest.getInstance("SHA-1")
    val fis: InputStream = FileInputStream(this)
    var n = 0
    val buffer = ByteArray(8192)
    while (n != -1) {
        n = fis.read(buffer)
        if (n > 0) {
            digest.update(buffer, 0, n)
        }
    }
    return convertToHex(digest.digest())
}

fun convertToHex(data: ByteArray): String? {
    val buf = StringBuffer()
    for (i in data.indices) {
        var halfbyte: Int = data[i].toInt() ushr 4 and 0x0F
        var two_halfs = 0
        do {
            if (0 <= halfbyte && halfbyte <= 9) buf.append(('0'.toInt() + halfbyte).toChar()) else buf.append(('a'.toInt() + (halfbyte - 10)).toChar())
            halfbyte = data[i].toInt() and 0x0F
        } while (two_halfs++ < 1)
    }
    return buf.toString()
}