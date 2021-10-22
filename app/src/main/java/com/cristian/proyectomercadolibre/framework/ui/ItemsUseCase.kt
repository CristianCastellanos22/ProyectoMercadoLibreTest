package com.cristian.proyectomercadolibre.framework.ui

import com.cristian.proyectomercadolibre.models.Item

interface ItemsUseCase {
    suspend fun getItem(item: String): List<Item>
}