package com.cristian.proyectomercadolibre.framework.data_source.api.items

import com.cristian.proyectomercadolibre.models.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsServices {
    @GET("search?")
    suspend fun getItems(@Query("q") item: String): Response<ResponseData>
}