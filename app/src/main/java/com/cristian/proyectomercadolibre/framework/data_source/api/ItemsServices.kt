package com.cristian.proyectomercadolibre.framework.data_source.api

import com.cristian.proyectomercadolibre.models.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsServices {
    @GET("/search?q={item}")
    suspend fun getItems(@Query("item") item: String): Response<List<Item>>
}