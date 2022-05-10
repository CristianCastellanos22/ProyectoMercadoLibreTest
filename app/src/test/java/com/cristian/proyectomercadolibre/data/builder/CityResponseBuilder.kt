package com.cristian.proyectomercadolibre.data.builder

import com.cristian.proyectomercadolibre.data.remote.models.CityResponse
import com.cristian.proyectomercadolibre.domain.models.City

class CityResponseBuilder(
    private val id: String = "1",
    private val name: String = "Data1"
) {
    fun mapToDomain(): City = City(
        id = id,
        name = name
    )

    fun mapToRemote(): CityResponse = CityResponse(
        id = id,
        name = name
    )
}