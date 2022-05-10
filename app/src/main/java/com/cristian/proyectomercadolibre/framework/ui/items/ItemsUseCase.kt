package com.cristian.proyectomercadolibre.framework.ui.items

import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.ProductData

interface ItemsUseCase {
    suspend fun getItem(item: String): HandlerResponse<ProductData>
}
