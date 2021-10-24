package com.cristian.proyectomercadolibre.domain.categories

import com.cristian.proyectomercadolibre.models.Categories

interface CategoriesRepository {
    suspend fun getCategories(): List<Categories>
}