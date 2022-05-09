package com.cristian.proyectomercadolibre.domain.categoriesDetails

import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.ProductData

interface CategoriesDetailsRepository {
    suspend fun getCategoriesDetails(details: String): HandlerResponse<ProductData>
}
