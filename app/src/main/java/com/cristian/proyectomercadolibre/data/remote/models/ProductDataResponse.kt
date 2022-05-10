package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.domain.models.ProductData
import com.google.gson.annotations.SerializedName

data class ProductDataResponse(
    @SerializedName("results") val products: List<ProductResponse>
)

fun ProductDataResponse.mapToDomain(): ProductData {
    return with(this) {
        ProductData(
            products = products.map { it.mapToDomain() }
        )
    }
}
