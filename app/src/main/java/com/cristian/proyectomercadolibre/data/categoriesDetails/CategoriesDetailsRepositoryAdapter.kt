package com.cristian.proyectomercadolibre.data.categoriesDetails

import com.cristian.proyectomercadolibre.domain.categoriesDetails.CategoriesDetailsRepository
import com.cristian.proyectomercadolibre.models.ResponseData

class CategoriesDetailsRepositoryAdapter(private val categoriesDetailsApiSource: CategoriesDetailsApiSource): CategoriesDetailsRepository {
    override suspend fun getCategoriesDetails(details: String): ResponseData = categoriesDetailsApiSource.getCategoriesDetails(details)
}