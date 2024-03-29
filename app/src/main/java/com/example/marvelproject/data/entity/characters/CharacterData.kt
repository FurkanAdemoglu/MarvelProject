package com.example.marvelproject.data.entity.characters

import com.example.marvelproject.data.entity.CharacterResults
import com.google.gson.annotations.SerializedName

data class CharacterData(
    @SerializedName("offset")
    var offset: Int,

    @SerializedName("limit")
    var limit: Int,

    @SerializedName("total")
    var total: Int,

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var results: List<CharacterResults>
)
