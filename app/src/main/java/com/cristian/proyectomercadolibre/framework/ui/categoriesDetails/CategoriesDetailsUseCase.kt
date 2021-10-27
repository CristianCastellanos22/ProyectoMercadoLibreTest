package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import com.cristian.proyectomercadolibre.models.ResponseData

interface CategoriesDetailsUseCase {
    suspend fun getCategoriesDetails(details: String): ResponseData
}