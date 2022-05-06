package com.cristian.proyectomercadolibre.domain.models

import com.cristian.proyectomercadolibre.data.builder.DataResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.ResultResponseBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ResponseDataTest {
    @Test
    fun `when data is not null`() {
        // given
        val dataResponse = DataResponseBuilder().mapToDomain()

        // Then
        assertNotNull(dataResponse)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val result =  listOf<Product>(ResultResponseBuilder().mapToDomain())
        val dataResponse = DataResponseBuilder().mapToDomain()

        // Then
        assertEquals(result, dataResponse.products)
    }
}