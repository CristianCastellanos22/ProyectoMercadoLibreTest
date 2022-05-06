package com.cristian.proyectomercadolibre.domain.items

import com.cristian.proyectomercadolibre.domain.models.ResponseData
import com.cristian.proyectomercadolibre.framework.ui.items.ItemsUseCase

class ItemUseCaseAdapter(private val itemsRepository: ItemsRepository): ItemsUseCase {
    override suspend fun getItem(item: String): ResponseData = itemsRepository.getItems(item)
}
