package com.cristian.proyectomercadolibre.domain.categoriesDetails

import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.ProductData
import com.cristian.proyectomercadolibre.framework.ui.categoriesDetails.CategoriesDetailsUseCase

class CategoriesDetailsUseCaseAdapter(private val categoriesDetailsRepository: CategoriesDetailsRepository): CategoriesDetailsUseCase {
    override suspend fun getCategoriesDetails(details: String): HandlerResponse<ProductData> = categoriesDetailsRepository.getCategoriesDetails(details)
}
