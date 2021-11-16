package com.example.marvelproject.data.entity

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data")
    var data: CharacterData
)