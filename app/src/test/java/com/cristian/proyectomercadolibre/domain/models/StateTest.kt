package com.cristian.proyectomercadolibre.domain.models

import com.cristian.proyectomercadolibre.data.builder.StateResponseBuilder
import org.junit.Assert
import org.junit.Test

class StateTest {
    @Test
    fun `when data is not null`() {
        // given
        val state = StateResponseBuilder().mapToDomain()

        // Then
        Assert.assertNotNull(state)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val id = "1"
        val name = "Data1"
        val state = StateResponseBuilder().mapToDomain()

        // Then
        Assert.assertEquals(id, state.id)
        Assert.assertEquals(name, state.name)
    }
}