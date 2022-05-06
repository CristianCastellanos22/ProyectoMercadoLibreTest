package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.data.builder.ResultResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.SellerAddressResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.StateResponseBuilder
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class StateResponseTest {
    @Test
    fun `when data is not null`() {
        // given
        val state = StateResponseBuilder().mapToRemote()

        // Then
        Assert.assertNotNull(state)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val id = "1"
        val name = "Data1"
        val state = StateResponseBuilder().mapToRemote()

        // Then
        Assert.assertEquals(id, state.id)
        Assert.assertEquals(name, state.name)
    }
}