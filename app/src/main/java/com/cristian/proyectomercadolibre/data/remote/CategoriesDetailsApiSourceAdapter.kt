package com.cristian.proyectomercadolibre.data.remote

import com.cristian.proyectomercadolibre.data.di.ServiceFactory
import com.cristian.proyectomercadolibre.data.remote.models.mapToDomain
import com.cristian.proyectomercadolibre.data.service.CategoriesDetailsServices
import com.cristian.proyectomercadolibre.domain.models.ProductData
import com.cristian.proyectomercadolibre.domain.models.errors.NetworkException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesDetailsApiSourceAdapter(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val categoriesDetailsServices: CategoriesDetailsServices by lazy {
        ServiceFactory.createRepositoryApi(CategoriesDetailsServices::class.java)
    }

    suspend fun getCategoriesDetails(details: String): ProductData {
        runCatching {
            withContext(dispatcher) {
                categoriesDetailsServices.getCategoriesDetails(details)
            }
        }.fold(
            onSuccess = { it ->
                return it.body()?.mapToDomain() ?: ProductData(emptyList())
            },
            onFailure = {
                throw NetworkException("${it.message}")
            }
        )
    }
}