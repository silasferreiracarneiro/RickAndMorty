package br.com.silascarneiro.rickandmorty.model.dto

import br.com.silascarneiro.rickandmorty.config.read
import br.com.silascarneiro.rickandmorty.model.entries.Character
import org.junit.Assert
import org.junit.Test

class CharacterDTOTest {

    @Test
    fun dadoQueEnvioAhEntryCharacter_devoRetornarUmaDto() {
        val entry = read<Character>("mock_lista_character.json")
        Assert.assertEquals(
            entry.parseCharacterToDto().name,
            "Rick Sanchez"
        )

        Assert.assertEquals(
            entry.parseCharacterToDto().id,
            1
        )

        Assert.assertEquals(
            entry.parseCharacterToDto().species,
            "Human"
        )
    }
}