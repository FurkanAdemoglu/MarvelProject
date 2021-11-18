package com.example.marvelproject.data.entity.comics

data class ComicsData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicsResults>,
    val total: Int
)
