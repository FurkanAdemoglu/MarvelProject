package com.example.marvelproject.data.entity.comics

data class ComicsResults(
    val description: String,
    val id: Int,
    val thumbnail: ComicsThumbnail,
    val title: String,
)
