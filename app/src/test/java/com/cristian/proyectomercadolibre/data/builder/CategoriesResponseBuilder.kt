package com.cristian.proyectomercadolibre.data.builder

import com.cristian.proyectomercadolibre.data.remote.models.CategoriesResponse
import com.cristian.proyectomercadolibre.domain.models.Categories

class CategoriesResponseBuilder(
    private val id: String = "1",
    private val name: String = "Data1"
) {
    fun mapToDomain(): Categories = Categories(
        id = id,
        name = name
    )

    fun mapToRemote(): CategoriesResponse = CategoriesResponse(
        id = id,
        name = name
    )
}