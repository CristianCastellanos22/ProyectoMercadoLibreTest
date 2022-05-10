package com.cristian.proyectomercadolibre.data.remote

import com.cristian.proyectomercadolibre.data.di.ServiceFactory
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.data.remote.models.mapToDomain
import com.cristian.proyectomercadolibre.data.service.CategoriesDetailsServices
import com.cristian.proyectomercadolibre.domain.models.ProductData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesDetailsApiSourceAdapter(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val categoriesDetailsServices: CategoriesDetailsServices by lazy {
        ServiceFactory.createRepositoryApi(CategoriesDetailsServices::class.java)
    }

    suspend fun getCategoriesDetails(details: String): HandlerResponse<ProductData> {
        runCatching {
            withContext(dispatcher) {
                categoriesDetailsServices.getCategoriesDetails(details)
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