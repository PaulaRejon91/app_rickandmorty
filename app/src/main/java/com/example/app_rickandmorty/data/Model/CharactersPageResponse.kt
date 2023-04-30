package com.example.app_rickandmorty.data.Model

data class CharactersPageResponse(
    val info: Info = Info(),
    val results: List<CharacterResponse> = emptyList()
){
    data class Info(
        val count: Int = 0,
        val next: String? = null,
        val pages: Int = 0,
        val prev: String? = null
    )
}