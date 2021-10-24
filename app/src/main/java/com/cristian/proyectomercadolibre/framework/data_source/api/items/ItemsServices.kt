package com.cristian.proyectomercadolibre.framework.data_source.api

import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsServices {
    @GET("search?")
    suspend fun getItems(@Query("q") item: String): Response<ResponseData>
    /*@GET("Result")
    suspend fun getItems(): ResponseData<List<Result>>*/
}