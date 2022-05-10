package com.cristian.proyectomercadolibre.framework.ui.categories

import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.Categories

interface CategoriesUseCase {
    suspend fun getCategories(): HandlerResponse<List<Categories>>
}
