package com.cristian.proyectomercadolibre.domain.categoriesDetails

import com.cristian.proyectomercadolibre.framework.ui.categoriesDetails.CategoriesDetailsUseCase
import com.cristian.proyectomercadolibre.models.ResponseData

class CategoriesDetailsUseCaseAdapter(private val categoriesDetailsRepository: CategoriesDetailsRepository): CategoriesDetailsUseCase {
    override suspend fun getCategoriesDetails(details: String): ResponseData = categoriesDetailsRepository.getCategoriesDetails(details)
}