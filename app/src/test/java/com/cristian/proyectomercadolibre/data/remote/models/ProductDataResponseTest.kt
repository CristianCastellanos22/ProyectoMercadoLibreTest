package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.data.builder.ProductDataResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.ProductResponseBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ProductDataResponseTest {
    @Test
    fun `when data is not null`() {
        // given
        val dataResponse = ProductDataResponseBuilder().mapToRemote()

        // Then
        assertNotNull(dataResponse)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val result =  listOf<ProductResponse>(ProductResponseBuilder().mapToRemote())
        val dataResponse = ProductDataResponseBuilder().mapToRemote()

        // Then
        assertEquals(result, dataResponse.products)
    }
}