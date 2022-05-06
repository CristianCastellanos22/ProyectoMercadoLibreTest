package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.domain.models.City
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

fun CityResponse.mapToDomain(): City {
    return with(this) {
        City(
            id = id,
            name = name
        )
    }
}
