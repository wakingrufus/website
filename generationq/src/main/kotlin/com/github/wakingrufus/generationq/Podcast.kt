package com.github.wakingrufus.generationq

@PodcastDsl
open class Podcast {
    val seasons = mutableListOf<Season>()
    val episodes = mutableListOf<Episode>()
    fun season(name: String, builder: Season.() -> Unit): Season{
        return Season(name).apply(builder).also{
            seasons.add(it)
            episodes.addAll(it.episodes)
        }
    }
    fun episode(title: String, url: String, builder: Episode.() -> Unit): Episode{
        return Episode(title, url).apply(builder).also {
            episodes.add(it)
        }
    }
}