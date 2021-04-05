package br.com.silascarneiro.rickandmorty.repository

import br.com.silascarneiro.rickandmorty.api.Api
import br.com.silascarneiro.rickandmorty.config.doResquest
import javax.inject.Inject

class CharacterRepository @Inject constructor(val api: Api) {

    suspend fun getListCharacter() = doResquest {
        api.getCharacters()
    }
}