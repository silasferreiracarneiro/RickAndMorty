package br.com.silascarneiro.rickandmorty.mock

import br.com.silascarneiro.rickandmorty.config.request.Data
import br.com.silascarneiro.rickandmorty.config.request.ResultApi
import br.com.silascarneiro.rickandmorty.config.read
import br.com.silascarneiro.rickandmorty.model.dto.parseCharacterToDto
import br.com.silascarneiro.rickandmorty.model.entries.Character

fun getSucessCallApiRepository() = read<Data<List<Character>>>("list_character.json")

fun getSucessCallApiUsecase() = ResultApi.createSucess(getSucessCallApiRepository())

fun getErrorCallApiUsecase() =  ResultApi.createError<Data<List<Character>>>(Throwable("Erro de conexão"))

fun getDtoApi() = getSucessCallApiRepository().let { it ->
    it.results?.map {
        it.parseCharacterToDto()
    } ?: arrayListOf()
}