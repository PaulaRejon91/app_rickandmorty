package com.example.app_rickandmorty.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_rickandmorty.data.Model.CharacterResponse
import com.example.app_rickandmorty.data.network.SharedRepository
import kotlinx.coroutines.launch


class CharacterViewModel : ViewModel() {
    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<CharacterResponse>()
    val characterByIdLiveData: LiveData<CharacterResponse?> = _characterByIdLiveData

    @SuppressLint("SuspiciousIndentation")
    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
        val response = repository.getCharacterById(characterId)

            _characterByIdLiveData.postValue(response)
        }
    }
}

