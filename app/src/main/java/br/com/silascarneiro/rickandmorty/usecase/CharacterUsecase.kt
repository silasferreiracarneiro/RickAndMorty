package br.com.silascarneiro.rickandmorty.usecase

import br.com.silascarneiro.rickandmorty.repository.CharacterRepository
import javax.inject.Inject

class CharacterUsecase @Inject constructor(val repository: CharacterRepository) {

    suspend fun getListCharacter() = repository.getListCharacter()
}