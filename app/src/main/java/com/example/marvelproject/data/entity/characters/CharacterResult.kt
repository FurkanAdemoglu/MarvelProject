package com.example.marvelproject.data.entity

import com.example.marvelproject.data.entity.characters.CharacterThumbnail
import com.google.gson.annotations.SerializedName


data class CharacterResults(
    @SerializedName("id")
    var characterId: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("thumbnail")
    var thumbnail: CharacterThumbnail,


)