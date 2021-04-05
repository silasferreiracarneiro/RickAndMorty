package br.com.silascarneiro.rickandmorty.config

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

private const val ASSET_BASE_PATH = "../app/src/test/java/br/com/silascarneiro/rickandmorty/mock/"

inline fun <reified T> read(path: String): T {
    return Gson().fromJson(readJsonFile(path = path), object: TypeToken<T>(){}.type)
}

fun readJsonFile(path: String): String {
    val buffer = BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + path)))
    val sb = StringBuilder()
    buffer.lines().forEach { sb.append(it) }
    return sb.toString()
}