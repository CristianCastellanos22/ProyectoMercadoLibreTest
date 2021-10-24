package com.cristian.proyectomercadolibre.framework.data_source.api.categoriesDetails

import com.cristian.proyectomercadolibre.models.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesDetailsServices {
    @GET("search?")
    suspend fun getCategoriesDetails(@Query("category") details: String): Response<ResponseData>
}