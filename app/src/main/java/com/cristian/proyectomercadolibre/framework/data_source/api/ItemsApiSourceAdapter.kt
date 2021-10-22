package com.cristian.proyectomercadolibre.framework.data_source.api

import com.cristian.proyectomercadolibre.data.ItemsApiSource
import com.cristian.proyectomercadolibre.models.Item
import com.cristian.proyectomercadolibre.models.errors.NetworkException
import com.cristian.proyectomercadolibre.utils.ExceptionFactory
import java.lang.Exception

class ItemsApiSourceAdapter(private val itemsServices: ItemsServices): ItemsApiSource {
    override suspend fun getItems(item: String): List<Item> {
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