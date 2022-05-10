package com.cristian.proyectomercadolibre.domain.models

import com.cristian.proyectomercadolibre.data.builder.ProductDataResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.ProductResponseBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ProductDataTest {
    @Test
    fun `when data is not null`() {
        // given
        val dataResponse = ProductDataResponseBuilder().mapToDomain()

        // Then
        assertNotNull(dataResponse)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val result =  listOf<Product>(ProductResponseBuilder().mapToDomain())
        val dataResponse = ProductDataResponseBuilder().mapToDomain()

        // Then
        assertEquals(result, dataResponse.products)
    }
}