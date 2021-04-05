package br.com.silascarneiro.rickandmorty.model.entries

import com.google.gson.annotations.SerializedName

class Episode(
    val id: Long,
    val name: String,

    @SerializedName("air_date")
    val airDate: String,

    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)