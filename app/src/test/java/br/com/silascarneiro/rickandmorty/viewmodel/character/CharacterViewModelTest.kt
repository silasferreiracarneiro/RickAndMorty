package br.com.silascarneiro.rickandmorty.viewmodel.character

import br.com.silascarneiro.rickandmorty.mock.getDtoApi
import br.com.silascarneiro.rickandmorty.mock.getErrorCallApiUsecase
import br.com.silascarneiro.rickandmorty.mock.getSucessCallApiUsecase
import br.com.silascarneiro.rickandmorty.usecase.CharacterUsecase
import br.com.silascarneiro.rickandmorty.viewmodel.character.events.CharacterState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var usecase: CharacterUsecase

    private lateinit var viewModel: CharacterViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = CharacterViewModel(usecase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `obtem a lista de personagens com sucesso`() {

        coEvery { usecase.getListCharacter() } returns getSucessCallApiUsecase()

        GlobalScope.launch {
            viewModel.getListCharacter()
            delay(3000)
            Assert.assertEquals(
                CharacterState.SucessCall(getDtoApi()),
                viewModel.viewState.value
            )
        }
    }

    @Test
    fun `obtem a lista de personagens com erro`() {

        coEvery { usecase.getListCharacter() } returns getErrorCallApiUsecase()

        GlobalScope.launch {
            viewModel.getListCharacter()
            delay(3000)
            Assert.assertEquals(
                CharacterState.ErrorCall,
                viewModel.viewState.value
            )
        }
    }
}