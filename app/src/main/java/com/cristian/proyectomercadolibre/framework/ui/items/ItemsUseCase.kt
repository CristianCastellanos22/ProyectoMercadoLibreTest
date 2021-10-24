package com.cristian.proyectomercadolibre.framework.ui

import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.Result

interface ItemsUseCase {
    suspend fun getItem(item: String): ResponseData
    //suspend fun getItem(): List<Result>
}