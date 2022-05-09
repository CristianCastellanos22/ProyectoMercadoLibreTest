package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import com.cristian.proyectomercadolibre.domain.models.ProductData

interface CategoriesDetailsUseCase {
    suspend fun getCategoriesDetails(details: String): ProductData
}
