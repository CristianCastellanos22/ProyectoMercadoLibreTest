package com.cristian.proyectomercadolibre.domain.items

import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.ProductData

interface ItemsRepository {
    suspend fun getItems(item: String): HandlerResponse<ProductData>
}
