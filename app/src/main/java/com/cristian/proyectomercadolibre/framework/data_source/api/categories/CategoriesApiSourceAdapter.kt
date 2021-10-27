package com.cristian.proyectomercadolibre.framework.data_source.api.categories

import com.cristian.proyectomercadolibre.data.categories.CategoriesApiSource
import com.cristian.proyectomercadolibre.models.Categories
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import com.cristian.proyectomercadolibre.utils.ExceptionFactory

class CategoriesApiSourceAdapter(private val categoriesServices: CategoriesServices): CategoriesApiSource {
    override suspend fun getCategories(): List<Categories> {
        try {
            val response = categoriesServices.getCategories()
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