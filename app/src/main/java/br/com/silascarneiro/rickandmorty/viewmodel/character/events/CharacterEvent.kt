package br.com.silascarneiro.rickandmorty.viewmodel.character.events

sealed class CharacterEvent {
    data class ShowOrHideLoading(val visible: Int) : CharacterEvent()
}