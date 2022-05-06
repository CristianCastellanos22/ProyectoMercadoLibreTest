package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.domain.models.Country
import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)

fun CountryResponse.mapToDomain(): Country {
    return with(this) {
        Country(
            id = id,
            name = name
        )
    }
}
