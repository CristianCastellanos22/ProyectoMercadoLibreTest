package com.cristian.proyectomercadolibre.data.builder

import com.cristian.proyectomercadolibre.data.remote.models.CountryResponse
import com.cristian.proyectomercadolibre.domain.models.Country

class CountryResponseBuilder(
    private val id: String = "1",
    private val name: String = "Data1"
) {
    fun mapToDomain(): Country = Country(
        id = id,
        name = name
    )

    fun mapToRemote(): CountryResponse = CountryResponse(
        id = id,
        name = name
    )
}