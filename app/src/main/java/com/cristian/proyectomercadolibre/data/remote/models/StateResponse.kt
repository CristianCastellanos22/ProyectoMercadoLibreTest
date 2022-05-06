package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.domain.models.State
import com.google.gson.annotations.SerializedName

data class StateResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)

fun StateResponse.mapToDomain(): State {
    return with(this) {
        State(
            id = id,
            name = name
        )
    }
}