package com.example.marvelproject.data.remote

import com.example.marvelproject.data.entity.characters.CharacterResponse
import com.example.marvelproject.data.entity.comics.ComicsModel
import com.example.marvelproject.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface APIService {

    @GET(value = "characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<CharacterResponse>


    @GET(value = "characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId") characterId: Int,
        @Query("startYear") startYear: Int,
        @Query("limit") limit: Int = Constants.COMICS_LIMIT,
        @Query("orderBy") orderBy: String = Constants.COMICS_ORDER_BY
    ): Response<ComicsModel>
}