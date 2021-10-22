package com.cristian.proyectomercadolibre.data

import com.cristian.proyectomercadolibre.models.Item

interface ItemsApiSource {
    suspend fun getItems(): List<Item>
}