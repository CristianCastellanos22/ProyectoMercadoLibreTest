package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.ProductData

interface CategoriesDetailsUseCase {
    suspend fun getCategoriesDetails(details: String): HandlerResponse<ProductData>
}
