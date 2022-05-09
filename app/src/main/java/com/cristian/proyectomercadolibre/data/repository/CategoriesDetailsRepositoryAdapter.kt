package com.cristian.proyectomercadolibre.data.repository

import com.cristian.proyectomercadolibre.data.remote.CategoriesDetailsApiSourceAdapter
import com.cristian.proyectomercadolibre.domain.categoriesDetails.CategoriesDetailsRepository
import com.cristian.proyectomercadolibre.domain.models.ProductData

class CategoriesDetailsRepositoryAdapter(private val categoriesDetailsApiSource: CategoriesDetailsApiSourceAdapter): CategoriesDetailsRepository {
    override suspend fun getCategoriesDetails(details: String): ProductData = categoriesDetailsApiSource.getCategoriesDetails(details)
}
