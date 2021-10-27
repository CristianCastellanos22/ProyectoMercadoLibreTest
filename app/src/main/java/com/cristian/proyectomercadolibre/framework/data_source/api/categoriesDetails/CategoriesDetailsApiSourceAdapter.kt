package com.cristian.proyectomercadolibre.framework.data_source.api.categoriesDetails

import com.cristian.proyectomercadolibre.data.categoriesDetails.CategoriesDetailsApiSource
import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import com.cristian.proyectomercadolibre.utils.ExceptionFactory

class CategoriesDetailsApiSourceAdapter(private val categoriesDetailsServices: CategoriesDetailsServices): CategoriesDetailsApiSource {
    override suspend fun getCategoriesDetails(details: String): ResponseData {
        try {
            val response = categoriesDetailsServices.getCategoriesDetails(details)
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw NetworkException(response.code(), "${response.code()}")
            }
        } catch (e: Exception) {
            throw ExceptionFactory.resolveError(e)
        }
    }
}