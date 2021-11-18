package com.example.marvelproject.data.entity.characters

import com.example.marvelproject.data.entity.characters.CharacterData
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data")
    var data: CharacterData
)