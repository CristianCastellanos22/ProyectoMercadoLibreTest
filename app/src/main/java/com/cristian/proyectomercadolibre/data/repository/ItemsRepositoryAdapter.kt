package com.cristian.proyectomercadolibre.data.repository

import com.cristian.proyectomercadolibre.data.remote.ItemsApiSourceAdapter
import com.cristian.proyectomercadolibre.domain.items.ItemsRepository
import com.cristian.proyectomercadolibre.domain.models.ProductData

class ItemsRepositoryAdapter(private val itemsApiSource: ItemsApiSourceAdapter): ItemsRepository {
    override suspend fun getItems(item: String): ProductData = itemsApiSource.getItems(item)
}
