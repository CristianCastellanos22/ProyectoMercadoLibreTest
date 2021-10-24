package com.cristian.proyectomercadolibre.domain

import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.Result

interface ItemsRepository {
    suspend fun getItems(item: String): ResponseData
    //suspend fun getItems(): List<Result>
}