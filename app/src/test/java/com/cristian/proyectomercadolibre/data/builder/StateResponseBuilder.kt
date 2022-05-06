package com.cristian.proyectomercadolibre.data.builder

import com.cristian.proyectomercadolibre.data.remote.models.StateResponse
import com.cristian.proyectomercadolibre.domain.models.State

class StateResponseBuilder(
    private val id: String = "1",
    private val name: String = "Data1"
) {
    fun mapToDomain(): State = State(
        id = id,
        name = name
    )

    fun mapToRemote(): StateResponse = StateResponse(
        id = id,
        name = name
    )
}