package com.cristian.proyectomercadolibre.domain.categories

import com.cristian.proyectomercadolibre.domain.models.Categories

interface CategoriesRepository {
    suspend fun getCategories(): List<Categories>
}
