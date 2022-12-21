package com.github.wakingrufus.website.music

import java.time.Year

@DslMarker
annotation class MusicListDsl

data class Album(
    val title: String,
    val artist: String,
    val year: Year,
    val link: String?,
    val review: String?,
    val tags: List<String> = listOf()
)

data class Track(
    val title: String,
    val artist: String,
    val album: String?,
    val link: String?,
    val tags: List<String> = listOf()
)

data class YearEndList(val year: Year, val albumOfTheYear: Album?, val albums: List<Album>, val tracks: List<Track>)

@MusicListDsl
class TrackBuilder(val title: String, private val artist: String, var link: String? = null) {
    private val tags: MutableList<String> = mutableListOf()
    var album: String? = null
    fun link(url: String) {
        link = url
    }

    fun tag(tag: String) {
        tags.add(tag)
    }

    fun tags(vararg tag: String) {
        tags.addAll(tag)
    }

    operator fun invoke(build: TrackBuilder.() -> Unit = {}): Track {
        apply(build)
        return Track(title, artist, album, link, tags)
    }
}

@MusicListDsl
class AlbumBuilder(
    val title: String,
    private val artist: String,
    private val year: Year,
    private var link: String? = null
) {
    private val tags: MutableList<String> = mutableListOf()
    private var review: String? = null
    fun review(review: String) {
        this.review = review
    }

    fun link(url: String) {
        link = url
    }

    fun tag(tag: String) {
        tags.add(tag)
    }

    fun tags(vararg tag: String) {
        tags.addAll(tag)
    }

    operator fun invoke(build: AlbumBuilder.() -> Unit = {}): Album {
        apply(build)
        return Album(title, artist, year, link, review, tags)
    }
}

@MusicListDsl
class YearEndListBuilder(private val year: Year) {
    private var albumOfTheYear: Album? = null
    private val albums: MutableList<Album> = mutableListOf()
    private val tracks: MutableList<Track> = mutableListOf()
    fun albumOfTheYear(title: String, artist: String, build: AlbumBuilder.() -> Unit): Album {
        return AlbumBuilder(title, artist, year)(build).also {
            albumOfTheYear = it
        }
    }

    fun album(title: String, artist: String, build: AlbumBuilder.() -> Unit = {}): Album {
        return AlbumBuilder(title, artist, year)(build).also {
            albums.add(it)
        }
    }

    fun track(title: String, artist: String, build: TrackBuilder.() -> Unit = {}): Track {
        return TrackBuilder(title, artist)(build).also {
            tracks.add(it)
        }
    }

    operator fun invoke(build: YearEndListBuilder.() -> Unit = {}): YearEndList {
        apply(build)
        return YearEndList(year, albumOfTheYear, albums, tracks)
    }
}

@MusicListDsl
fun bestMusicOf(year: Int, build: YearEndListBuilder.() -> Unit): YearEndList {
    return YearEndListBuilder(Year.of(year))(build)
}