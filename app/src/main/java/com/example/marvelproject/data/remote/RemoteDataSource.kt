package com.example.marvelproject.data.remote

import com.example.marvelproject.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: APIService
) : BaseDataSource() {
    suspend fun getCharacters(offset: Int, limit: Int) = getResult {
        apiService.getCharacters(offset, limit)
    }

    suspend fun getComics(id: Int, startYear: Int, limit: Int, orderBy: String) = getResult {
        apiService.getComics(id, startYear, limit, orderBy)
    }
}