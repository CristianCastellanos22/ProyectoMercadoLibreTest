package com.cristian.proyectomercadolibre.data.service

import com.cristian.proyectomercadolibre.data.remote.models.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesDetailsServices {
    @GET("search?")
    suspend fun getCategoriesDetails(@Query("category") details: String): Response<DataResponse>
}
