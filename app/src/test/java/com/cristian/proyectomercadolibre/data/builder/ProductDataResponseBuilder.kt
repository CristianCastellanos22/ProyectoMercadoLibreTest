package com.cristian.proyectomercadolibre.data.builder

import com.cristian.proyectomercadolibre.data.remote.models.ProductDataResponse
import com.cristian.proyectomercadolibre.data.remote.models.ProductResponse
import com.cristian.proyectomercadolibre.data.remote.models.mapToDomain
import com.cristian.proyectomercadolibre.domain.models.ProductData

class ProductDataResponseBuilder(
    private val product: List<ProductResponse> = listOf(ProductResponseBuilder().mapToRemote())
) {
    fun mapToDomain(): ProductData = ProductData(
        products = product.map {
            it.mapToDomain()
        }
    )

    fun mapToRemote(): ProductDataResponse = ProductDataResponse(
        products = product
    )
}