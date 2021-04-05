package br.com.silascarneiro.rickandmorty.viewmodel.character.events

import br.com.silascarneiro.rickandmorty.model.dto.CharacterDTO

sealed class CharacterState {
    object ErrorCall : CharacterState()
    data class SucessCall(val list: List<CharacterDTO>) : CharacterState()
}