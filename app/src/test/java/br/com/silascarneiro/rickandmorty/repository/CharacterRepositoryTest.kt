package br.com.silascarneiro.rickandmorty.repository

import br.com.silascarneiro.rickandmorty.api.Api
import br.com.silascarneiro.rickandmorty.mock.getSucessCallApiRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    private lateinit var api: Api

    private lateinit var repository: CharacterRepository

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)
        repository = CharacterRepository(api)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `testa se o retorno da api foi com sucesso`() {
        coEvery { api.getCharacters() } returns getSucessCallApiRepository()
        runBlocking {
            val files = repository.getListCharacter()
            Assert.assertTrue(
                files.isSucess()
            )
        }
    }
}