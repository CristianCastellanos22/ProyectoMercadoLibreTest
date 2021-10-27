package com.cristian.proyectomercadolibre.data.categoriesDetails

import com.cristian.proyectomercadolibre.models.ResponseData

interface CategoriesDetailsApiSource {
    suspend fun getCategoriesDetails(details: String): ResponseData
}