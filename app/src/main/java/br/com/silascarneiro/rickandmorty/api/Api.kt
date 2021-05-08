package br.com.silascarneiro.rickandmorty.api

import br.com.silascarneiro.rickandmorty.config.request.Data
import br.com.silascarneiro.rickandmorty.model.entries.Character
import retrofit2.http.GET

interface Api {

    @GET("character")
    suspend fun getCharacters(): Data<List<Character>>
}