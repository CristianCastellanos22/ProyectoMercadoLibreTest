package com.cristian.proyectomercadolibre.data.service

import com.cristian.proyectomercadolibre.data.remote.models.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsServices {
    @GET("search?")
    suspend fun getItems(@Query("q") item: String): Response<DataResponse>
}
