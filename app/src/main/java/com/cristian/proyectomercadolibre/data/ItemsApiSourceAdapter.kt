package com.cristian.proyectomercadolibre.data

import com.cristian.proyectomercadolibre.domain.ItemsRepository
import com.cristian.proyectomercadolibre.models.Item

class ItemsApiSourceAdapter(private val itemsApiSource: ItemsApiSource): ItemsRepository {
    override suspend fun getItems(): List<Item> {
        return itemsApiSource.getItems()
    }
}