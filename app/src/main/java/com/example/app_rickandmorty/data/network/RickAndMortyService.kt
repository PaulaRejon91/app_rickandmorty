package com.example.app_rickandmorty.data.network

import com.example.app_rickandmorty.data.Model.GetCharacterByIdResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

        @GET("character/{character-id}")
        fun getPersonajesById(
            @Path("character-id")characterId:Int
        ): Call<GetCharacterByIdResponse>
    }
