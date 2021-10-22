package com.cristian.proyectomercadolibre.domain

import com.cristian.proyectomercadolibre.framework.ui.ItemsUseCase
import com.cristian.proyectomercadolibre.models.Item

class ItemUseCaseAdapter(private val itemsRepository: ItemsRepository): ItemsUseCase {
    override suspend fun getItem(): List<Item> = itemsRepository.getItems()
}