package br.com.silascarneiro.rickandmorty.viewmodel.character

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silascarneiro.rickandmorty.config.request.Data
import br.com.silascarneiro.rickandmorty.config.request.ResultApi
import br.com.silascarneiro.rickandmorty.model.dto.parseCharacterToDto
import br.com.silascarneiro.rickandmorty.model.entries.Character
import br.com.silascarneiro.rickandmorty.usecase.CharacterUsecase
import br.com.silascarneiro.rickandmorty.viewmodel.character.events.CharacterEvent
import br.com.silascarneiro.rickandmorty.viewmodel.character.events.CharacterState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterViewModel @Inject constructor(val usecase: CharacterUsecase) : ViewModel() {

    private val event = MutableLiveData<CharacterEvent>()
    private val state = MutableLiveData<CharacterState>()

    val viewEvent = event
    val viewState = state

    fun getListCharacter() {
        event.postValue(CharacterEvent.ShowOrHideLoading(View.VISIBLE))
        GlobalScope.launch {
            val result = usecase.getListCharacter()
            afterCall(result)
        }
    }

    private fun afterCall(result: ResultApi<Data<List<Character>>>) {
        event.postValue(CharacterEvent.ShowOrHideLoading(View.GONE))
        when (result.isSucess()) {
            true -> parseToDto(result.value)
            false -> state.postValue(CharacterState.ErrorCall)
        }
    }

    private fun parseToDto(value: Data<List<Character>>?) {
        value?.let { it ->
            val listDto = it.results?.map {
                it.parseCharacterToDto()
            } ?: arrayListOf()
            state.postValue(CharacterState.SucessCall(listDto))
        }
    }
}