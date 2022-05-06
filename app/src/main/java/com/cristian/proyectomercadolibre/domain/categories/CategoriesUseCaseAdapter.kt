package com.cristian.proyectomercadolibre.domain.categories

import com.cristian.proyectomercadolibre.domain.models.Categories
import com.cristian.proyectomercadolibre.framework.ui.categories.CategoriesUseCase


class CategoriesUseCaseAdapter(private val categoriesRepository: CategoriesRepository): CategoriesUseCase {
    override suspend fun getCategories(): List<Categories> = categoriesRepository.getCategories()
}
