package com.example.marvelproject.data.remote

import com.example.marvelproject.data.entity.CharacterResponse
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
    suspend fun getCharacters(@Query("offset") offset: Int,
                              @Query("limit") limit: Int): Response<CharacterResponse>


    companion object {
        fun getService(): APIService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", PUBLIC_API_KEY)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("hash", "$ts$PRIVATE_KEY$PUBLIC_API_KEY".md5())
                    .build()

                chain.proceed(original.newBuilder().url(url).build())
            }

            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

            return retrofit.create<APIService>(APIService::class.java)
        }
    }

 /*   @GET(value = "characters/{character_id}/comics")
    suspend fun getComics(@Path("character_id") character_id: Int,

                          @Query("startYear") startYear: Int,
                          @Query("limit") limit: Int,
                          @Query("orderBy") orderBy: String): Comics*/
}