package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.domain.models.ResponseData
import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("results") val results: List<ResultResponse>
)

fun DataResponse.mapToDomain(): ResponseData {
    return with(this) {
        ResponseData(
            products = results.map { it.mapToDomain() }
        )
    }
}
