package com.cristian.proyectomercadolibre.framework.ui.items

import com.cristian.proyectomercadolibre.domain.models.ResponseData

interface ItemsUseCase {
    suspend fun getItem(item: String): ResponseData
}
