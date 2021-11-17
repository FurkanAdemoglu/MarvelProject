package com.example.marvelproject.data.remote

import com.example.marvelproject.data.entity.CharacterResponse
import com.example.marvelproject.data.entity.Comics
import com.example.marvelproject.utils.Constants.BASE_URL
import com.example.marvelproject.utils.Constants.PUBLIC_API_KEY
import com.example.marvelproject.utils.md5
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import javax.crypto.Cipher.PRIVATE_KEY

interface APIService {

    @GET(value = "characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<CharacterResponse>


    @GET(value = "characters/{character_id}/comics")
    suspend fun getComics(
        @Path("character_id") character_id: Int,
        @Query("startYear") startYear: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String
    ): Response<Comics>
}