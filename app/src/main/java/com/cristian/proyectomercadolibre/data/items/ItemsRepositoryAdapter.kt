package com.cristian.proyectomercadolibre.data.items

import com.cristian.proyectomercadolibre.domain.items.ItemsRepository
import com.cristian.proyectomercadolibre.models.ResponseData

class ItemsRepositoryAdapter(private val itemsApiSource: ItemsApiSource): ItemsRepository {
    override suspend fun getItems(item: String): ResponseData {
        return itemsApiSource.getItems(item)
    }
    /*override suspend fun getItems(): List<Result> {
        return itemsApiSource.getItems()
    }*/
}