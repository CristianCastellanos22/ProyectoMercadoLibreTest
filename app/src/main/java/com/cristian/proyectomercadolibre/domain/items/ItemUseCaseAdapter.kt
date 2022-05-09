package com.cristian.proyectomercadolibre.domain.items

import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.ProductData
import com.cristian.proyectomercadolibre.framework.ui.items.ItemsUseCase

class ItemUseCaseAdapter(private val itemsRepository: ItemsRepository): ItemsUseCase {
    override suspend fun getItem(item: String): HandlerResponse<ProductData> = itemsRepository.getItems(item)
}
