package com.example.marvelproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.marvelproject.data.ApiRepository
import com.example.marvelproject.data.entity.CharacterResponse
import com.example.marvelproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    var apiRepository: ApiRepository
) : ViewModel(){
    fun getCharacters(offset: Int, page: Int): LiveData<Resource<CharacterResponse>> {
        return apiRepository.getCharacters(offset, page)
    }
}