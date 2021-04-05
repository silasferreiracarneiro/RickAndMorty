package br.com.silascarneiro.rickandmorty.utils

import org.junit.Assert
import org.junit.Test

class StringExtensionTest {

    @Test
    fun quandoObterAhDataDoPadraoDaApi_DevoConverterParaOhPadraoBrasileiro() {
        Assert.assertEquals(
            "04/11/2017",
            "2017-11-04T18:48:46.250Z".formatDatePadraoBrasileiro()
        )
    }

    @Test
    fun quandoObterUmaDataInvalida_DevoRetornarUmaStringVazia() {
        Assert.assertEquals(
            "",
            "rwerwe".formatDatePadraoBrasileiro()
        )
    }

    @Test
    fun quandoObterUmaDataVazia_DevoRetornarUmaStringVazia() {
        Assert.assertEquals(
            "",
            "".formatDatePadraoBrasileiro()
        )
    }
}