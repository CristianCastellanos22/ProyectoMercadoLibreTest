package com.cristian.proyectomercadolibre.data.categories

import com.cristian.proyectomercadolibre.domain.categories.CategoriesRepository
import com.cristian.proyectomercadolibre.models.Categories

class CategoriesRepositoryAdapter(private val categoriesApiSource: CategoriesApiSource): CategoriesRepository {
    override suspend fun getCategories(): List<Categories> = categoriesApiSource.getCategories()
}