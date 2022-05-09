package com.cristian.proyectomercadolibre.domain.categories

import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.Categories

interface CategoriesRepository {
    suspend fun getCategories(): HandlerResponse<List<Categories>>
}
