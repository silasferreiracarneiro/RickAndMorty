package br.com.silascarneiro.rickandmorty.ui.home.callback

import br.com.silascarneiro.rickandmorty.model.dto.CharacterDTO

interface CharacterCallback {
    fun selectedCharacter(dto: CharacterDTO)
}