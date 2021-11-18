package com.example.marvelproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.marvelproject.data.ApiRepository
import com.example.marvelproject.data.entity.comics.ComicsModel

import com.example.marvelproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    var apiRepository: ApiRepository
) : ViewModel() {
    fun getComics(
        id: Int,
        startYear:Int
    ): LiveData<Resource<ComicsModel>> {
        return apiRepository.getComics(id,startYear)
    }
}