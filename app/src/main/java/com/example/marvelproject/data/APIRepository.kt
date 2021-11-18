package com.example.marvelproject.data

import com.example.marvelproject.data.remote.RemoteDataSource
import com.example.marvelproject.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
) {
    fun getCharacters(query: Int, page: Int) = performNetworkOperation {
        remoteDataSource.getCharacters(query, page)
    }

    fun getComics(id: Int,startYear:Int) = performNetworkOperation {
        remoteDataSource.getComics(id,startYear)
    }

}