package com.cristian.proyectomercadolibre.domain.categoriesDetails

import com.cristian.proyectomercadolibre.domain.models.ResponseData

interface CategoriesDetailsRepository {
    suspend fun getCategoriesDetails(details: String): ResponseData
}
