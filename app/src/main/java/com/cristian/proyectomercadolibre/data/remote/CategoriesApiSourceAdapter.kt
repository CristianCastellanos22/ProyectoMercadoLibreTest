package com.cristian.proyectomercadolibre.data.remote

import com.cristian.proyectomercadolibre.data.di.ServiceFactory
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.data.remote.models.mapToDomain
import com.cristian.proyectomercadolibre.data.service.CategoriesServices
import com.cristian.proyectomercadolibre.domain.models.Categories
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesApiSourceAdapter(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val categoriesServices: CategoriesServices by lazy {
        ServiceFactory.createRepositoryApi(CategoriesServices::class.java)
    }

    suspend fun getCategories(): HandlerResponse<List<Categories>>  {
        runCatching {
            withContext(dispatcher) {
                categoriesServices.getCategories()
            }
        }.fold(
            onSuccess = { it ->
                return HandlerResponse.Success(
                    it.body()?.map {
                        it.mapToDomain()
                    }.orEmpty()
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
