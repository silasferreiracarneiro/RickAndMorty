package br.com.silascarneiro.rickandmorty.model.dto

import android.os.Parcel
import android.os.Parcelable
import br.com.silascarneiro.rickandmorty.model.entries.Character

class CharacterDTO(
    val id: Long,
    val name: String?,
    val image: String?,
    val species: String?,
    val gender: String?,
    val qtEpisodes: Int = 0,
    val location: String?,
    val dateCreate: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(species)
        parcel.writeString(gender)
        parcel.writeValue(qtEpisodes)
        parcel.writeString(location)
        parcel.writeString(dateCreate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterDTO> {
        override fun createFromParcel(parcel: Parcel): CharacterDTO {
            return CharacterDTO(parcel)
        }

        override fun newArray(size: Int): Array<CharacterDTO?> {
            return arrayOfNulls(size)
        }
    }
}

fun Character.parseCharacterToDto() =
    CharacterDTO(
        this.id,
        this.name,
        this.image,
        this.species,
        this.gender,
        this.episode.size,
        this.location.name,
        this.created
    )