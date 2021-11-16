package com.example.marvelproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.marvelproject.data.ApiRepository
import com.example.marvelproject.data.entity.CharacterResponse
import com.example.marvelproject.data.entity.Comics
import com.example.marvelproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    var apiRepository: ApiRepository
):ViewModel() {
    fun getComics(id: Int,startYear:Int,limit: Int,orderBy:String): LiveData<Resource<Comics>> {
        return apiRepository.getComics(id,startYear,limit,orderBy)
    }
}