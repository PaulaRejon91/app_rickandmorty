package com.example.app_rickandmorty.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_rickandmorty.data.Model.CharacterResponse
import com.example.app_rickandmorty.data.network.SharedRepository
import kotlinx.coroutines.launch


class MainActivityViewModel : ViewModel() {
    private val repository = SharedRepository()

    private val _characterListLiveData = MutableLiveData <List<CharacterResponse>>()
    val characterListLiveData: LiveData<List<CharacterResponse>>? = _characterListLiveData

    @SuppressLint("SuspiciousIndentation")
    fun refreshList() {
        viewModelScope.launch {
        val response = repository.getCharactersList(2)

//            _characterListLiveData.postValue(response)
        }
    }
}

