package br.com.silascarneiro.rickandmorty.utils

import br.com.silascarneiro.rickandmorty.utils.Constants.ConstantStringUtil.DATA_PADRAO_API
import br.com.silascarneiro.rickandmorty.utils.Constants.ConstantStringUtil.DATA_PADRAO_BRASILEIRO
import java.text.SimpleDateFormat

fun String.formatDatePadraoBrasileiro(): String = try {
    val d = SimpleDateFormat(DATA_PADRAO_API).parse(this)
    SimpleDateFormat(DATA_PADRAO_BRASILEIRO).format(d)
} catch (e: Throwable) {
    ""
}