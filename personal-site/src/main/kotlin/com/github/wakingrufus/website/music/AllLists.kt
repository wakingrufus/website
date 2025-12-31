package com.github.wakingrufus.website.music

val allYearEndLists = listOf(
    bestMusic2019, bestMusic2020, bestMusic2021, bestMusic2022, bestMusic2023, bestMusic2024, bestMusic2025
)
val allAlbums = allYearEndLists.mapNotNull { it.albumOfTheYear } + allYearEndLists.flatMap { it.albums }