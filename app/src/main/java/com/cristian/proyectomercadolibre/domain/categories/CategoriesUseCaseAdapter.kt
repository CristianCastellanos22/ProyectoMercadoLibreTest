package com.cristian.proyectomercadolibre.domain.categories

import com.cristian.proyectomercadolibre.framework.ui.categories.CategoriesUseCase
import com.cristian.proyectomercadolibre.models.Categories

class CategoriesUseCaseAdapter(private val categoriesRepository: CategoriesRepository): CategoriesUseCase {
    override suspend fun getCategories(): List<Categories> = categoriesRepository.getCategories()
}