package com.cristian.proyectomercadolibre.domain

import com.cristian.proyectomercadolibre.framework.ui.items.ItemsUseCase
import com.cristian.proyectomercadolibre.models.ResponseData

class ItemUseCaseAdapter(private val itemsRepository: ItemsRepository): ItemsUseCase {
    override suspend fun getItem(item: String): ResponseData = itemsRepository.getItems(item)
    //override suspend fun getItem(): List<Result> = itemsRepository.getItems()
}