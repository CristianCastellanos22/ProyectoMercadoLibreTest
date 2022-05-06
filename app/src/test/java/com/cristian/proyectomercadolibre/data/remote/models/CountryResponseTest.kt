package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.data.builder.CountryResponseBuilder
import org.junit.Assert
import org.junit.Test

class CountryResponseTest {
    @Test
    fun `when data is not null`() {
        // given
        val country = CountryResponseBuilder().mapToRemote()

        // Then
        Assert.assertNotNull(country)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val id = "1"
        val name = "Data1"
        val country = CountryResponseBuilder().mapToRemote()

        // Then
        Assert.assertEquals(id, country.id)
        Assert.assertEquals(name, country.name)
    }
}