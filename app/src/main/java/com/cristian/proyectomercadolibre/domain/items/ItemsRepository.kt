package com.cristian.proyectomercadolibre.domain.items

import com.cristian.proyectomercadolibre.models.ResponseData

interface ItemsRepository {
    suspend fun getItems(item: String): ResponseData
}