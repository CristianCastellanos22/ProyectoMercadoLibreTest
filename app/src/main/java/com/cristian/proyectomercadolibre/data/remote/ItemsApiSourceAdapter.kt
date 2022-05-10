package com.cristian.proyectomercadolibre.data.remote

import com.cristian.proyectomercadolibre.data.di.ServiceFactory
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.data.remote.models.mapToDomain
import com.cristian.proyectomercadolibre.data.service.ItemsServices
import com.cristian.proyectomercadolibre.domain.models.ProductData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsApiSourceAdapter(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val itemsServices: ItemsServices by lazy {
        ServiceFactory.createRepositoryApi(ItemsServices::class.java)
    }

    suspend fun getItems(item: String): HandlerResponse<ProductData> {
        runCatching {
            withContext(dispatcher) {
                itemsServices.getItems(item)
            }
        }.fold(
            onSuccess = { it ->
                return HandlerResponse.Success(
                    it.body()?.mapToDomain() ?: ProductData(emptyList())
                )
            },
            onFailure = {
                return HandlerResponse.Failure(
                    it as Exception
                )
            }
        )
    }
}