package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.domain.models.Categories
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

fun CategoriesResponse.mapToDomain(): Categories {
    return with(this) {
        Categories(
            id = id,
            name = name
        )
    }
}
