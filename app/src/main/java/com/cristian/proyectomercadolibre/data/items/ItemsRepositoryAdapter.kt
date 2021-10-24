package com.cristian.proyectomercadolibre.data

import com.cristian.proyectomercadolibre.domain.ItemsRepository
import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.Result

class ItemsRepositoryAdapter(private val itemsApiSource: ItemsApiSource): ItemsRepository {
    override suspend fun getItems(item: String): ResponseData {
        return itemsApiSource.getItems(item)
    }
    /*override suspend fun getItems(): List<Result> {
        return itemsApiSource.getItems()
    }*/
}