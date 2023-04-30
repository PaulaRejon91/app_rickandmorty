package com.example.app_rickandmorty.data.network

import com.example.app_rickandmorty.data.Model.CharacterResponse
import com.example.app_rickandmorty.data.Model.CharactersPageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Response<CharacterResponse>

    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): Response<CharactersPageResponse>
}
