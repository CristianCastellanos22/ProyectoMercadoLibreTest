package com.cristian.proyectomercadolibre.data.builder

import com.cristian.proyectomercadolibre.data.remote.models.DataResponse
import com.cristian.proyectomercadolibre.data.remote.models.ResultResponse
import com.cristian.proyectomercadolibre.data.remote.models.mapToDomain
import com.cristian.proyectomercadolibre.domain.models.ResponseData

class DataResponseBuilder(
    private val result: List<ResultResponse> = listOf(ResultResponseBuilder().mapToRemote())
) {
    fun mapToDomain(): ResponseData = ResponseData(
        products = result.map {
            it.mapToDomain()
        }
    )

    fun mapToRemote(): DataResponse = DataResponse(
        results = result
    )
}