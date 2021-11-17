package com.example.marvelproject.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize


data class CharacterResults(
    @SerializedName("id")
    var characterId: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("thumbnail")
    var thumbnail: CharacterThumbnail,

    @SerializedName("comics")
    val comics: Comics,
)