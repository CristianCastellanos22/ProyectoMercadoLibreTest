package com.cristian.proyectomercadolibre.data.repository

import com.cristian.proyectomercadolibre.data.remote.ItemsApiSourceAdapter
import com.cristian.proyectomercadolibre.domain.items.ItemsRepository
import com.cristian.proyectomercadolibre.domain.models.ResponseData

class ItemsRepositoryAdapter(private val itemsApiSource: ItemsApiSourceAdapter): ItemsRepository {
    override suspend fun getItems(item: String): ResponseData = itemsApiSource.getItems(item)
}
