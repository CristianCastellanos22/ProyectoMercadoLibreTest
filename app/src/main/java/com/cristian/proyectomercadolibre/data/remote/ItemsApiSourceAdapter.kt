package com.cristian.proyectomercadolibre.data.remote

import com.cristian.proyectomercadolibre.data.di.ServiceFactory
import com.cristian.proyectomercadolibre.data.remote.models.mapToDomain
import com.cristian.proyectomercadolibre.data.service.ItemsServices
import com.cristian.proyectomercadolibre.domain.models.ResponseData
import com.cristian.proyectomercadolibre.domain.models.errors.NetworkException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsApiSourceAdapter(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val itemsServices: ItemsServices by lazy {
        ServiceFactory.createRepositoryApi(ItemsServices::class.java)
    }

    suspend fun getItems(item: String): ResponseData {
        runCatching {
            withContext(dispatcher) {
                itemsServices.getItems(item)
            }
        }.fold(
            onSuccess = { it ->
                return it.body()?.mapToDomain() ?: ResponseData(emptyList())
            },
            onFailure = {
                throw NetworkException("${it.message}")
            }
        )
    }
}