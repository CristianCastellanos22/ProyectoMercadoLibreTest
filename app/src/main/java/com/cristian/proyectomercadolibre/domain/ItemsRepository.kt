package com.cristian.proyectomercadolibre.domain

import com.cristian.proyectomercadolibre.models.Item

interface ItemsRepository {
    suspend fun getItems(): List<Item>
}