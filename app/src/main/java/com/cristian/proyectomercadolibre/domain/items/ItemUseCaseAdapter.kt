package com.cristian.proyectomercadolibre.domain.items

import com.cristian.proyectomercadolibre.domain.models.ProductData
import com.cristian.proyectomercadolibre.framework.ui.items.ItemsUseCase

class ItemUseCaseAdapter(private val itemsRepository: ItemsRepository): ItemsUseCase {
    override suspend fun getItem(item: String): ProductData = itemsRepository.getItems(item)
}
