package com.cristian.proyectomercadolibre.framework.data_source.api.categories

import com.cristian.proyectomercadolibre.models.Categories
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesServices {
    @GET("categories")
    suspend fun getCategories(): Response<List<Categories>>
}