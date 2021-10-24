package com.cristian.proyectomercadolibre.data.categories

import com.cristian.proyectomercadolibre.models.Categories

interface CategoriesApiSource {
    suspend fun getCategories(): List<Categories>
}