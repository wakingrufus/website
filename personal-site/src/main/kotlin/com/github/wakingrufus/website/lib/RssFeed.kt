package com.github.wakingrufus.website.lib

import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedOutput
import java.io.File
import java.io.Writer

sealed class RssFeed(val file: File) {
    abstract fun write(writer: Writer)
}

fun writeFeed(writer: Writer, contents: String) {
    writer.use {
        it.write(contents)
    }
}

class RssFeedBuilder(file: File, val builder: SyndFeed) : RssFeed(file) {
    override fun write(writer: Writer) = writeFeed(writer, SyndFeedOutput().outputString(builder))
}

class RssFeedString(file: File, val contents: String) : RssFeed(file) {
    override fun write(writer: Writer) = writeFeed(writer, contents)
}