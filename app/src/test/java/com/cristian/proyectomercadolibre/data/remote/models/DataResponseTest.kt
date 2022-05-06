package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.data.builder.DataResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.ResultResponseBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class DataResponseTest {
    @Test
    fun `when data is not null`() {
        // given
        val dataResponse = DataResponseBuilder().mapToRemote()

        // Then
        assertNotNull(dataResponse)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val result =  listOf<ResultResponse>(ResultResponseBuilder().mapToRemote())
        val dataResponse = DataResponseBuilder().mapToRemote()

        // Then
        assertEquals(result, dataResponse.results)
    }
}