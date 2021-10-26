package com.cristian.proyectomercadolibre.framework.data_source.api.items

import com.cristian.proyectomercadolibre.data.items.ItemsApiSource
import com.cristian.proyectomercadolibre.models.ResponseData
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import com.cristian.proyectomercadolibre.utils.ExceptionFactory
import java.lang.Exception

class ItemsApiSourceAdapter(private val itemsServices: ItemsServices): ItemsApiSource {
    override suspend fun getItems(item: String): ResponseData {
        try {
            val response = itemsServices.getItems(item)
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw NetworkException(response.code(), "${response.code()}")
            }
        } catch (e: Exception) {
            throw ExceptionFactory.resolveError(e)
        }
    }
}