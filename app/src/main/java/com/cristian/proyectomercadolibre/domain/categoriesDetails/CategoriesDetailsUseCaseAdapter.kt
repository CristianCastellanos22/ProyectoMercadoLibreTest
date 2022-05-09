package com.cristian.proyectomercadolibre.domain.categoriesDetails

import com.cristian.proyectomercadolibre.domain.models.ProductData
import com.cristian.proyectomercadolibre.framework.ui.categoriesDetails.CategoriesDetailsUseCase

class CategoriesDetailsUseCaseAdapter(private val categoriesDetailsRepository: CategoriesDetailsRepository): CategoriesDetailsUseCase {
    override suspend fun getCategoriesDetails(details: String): ProductData = categoriesDetailsRepository.getCategoriesDetails(details)
}
