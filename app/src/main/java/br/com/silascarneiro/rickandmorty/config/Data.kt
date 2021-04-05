package br.com.silascarneiro.rickandmorty.config

import com.google.gson.annotations.SerializedName

class Data<T> {
    @SerializedName("info")
    var info: Info? = null

    @SerializedName("results")
    var results: T? = null
}

class Info {
    @SerializedName("count")
    var count: Int? = null
    @SerializedName("pages")
    var pages: Int? = null
    @SerializedName("next")
    var next: String? = null
    @SerializedName("prev")
    var prev: String? = null
}