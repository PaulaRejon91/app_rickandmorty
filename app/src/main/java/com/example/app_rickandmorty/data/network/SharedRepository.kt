package com.example.app_rickandmorty.data.network

import com.example.app_rickandmorty.data.Model.CharacterResponse
import com.example.app_rickandmorty.data.Model.CharactersPageResponse

class SharedRepository {
    suspend fun getCharacterById(characterId:Int): CharacterResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if(request.failed || !request.isSuccessful){
            return null
        }
        return request.body
    }

    suspend fun getCharactersList(pageIndex: Int): CharactersPageResponse {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if(request.failed || !request.isSuccessful){
            return CharactersPageResponse()
        }
        return request.body
    }
}