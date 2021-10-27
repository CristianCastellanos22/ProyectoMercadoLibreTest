package com.cristian.proyectomercadolibre.data.items

import com.cristian.proyectomercadolibre.models.ResponseData

interface ItemsApiSource {
    suspend fun getItems(item: String): ResponseData
}