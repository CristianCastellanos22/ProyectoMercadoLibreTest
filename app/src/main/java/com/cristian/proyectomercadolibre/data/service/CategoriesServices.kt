package com.cristian.proyectomercadolibre.data.service

import com.cristian.proyectomercadolibre.data.remote.models.CategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesServices {
    @GET("categories")
    suspend fun getCategories(): Response<List<CategoriesResponse>>
}
