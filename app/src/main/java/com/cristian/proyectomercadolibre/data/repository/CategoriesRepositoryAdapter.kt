package com.cristian.proyectomercadolibre.data.repository

import com.cristian.proyectomercadolibre.data.remote.CategoriesApiSourceAdapter
import com.cristian.proyectomercadolibre.domain.categories.CategoriesRepository
import com.cristian.proyectomercadolibre.domain.models.Categories

class CategoriesRepositoryAdapter(private val categoriesApiSource: CategoriesApiSourceAdapter): CategoriesRepository {
    override suspend fun getCategories(): List<Categories> = categoriesApiSource.getCategories()
}
