package com.cristian.proyectomercadolibre.data

import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.Result

interface ItemsApiSource {
    suspend fun getItems(item: String): ResponseData
    //suspend fun getItems(): List<Result>
}