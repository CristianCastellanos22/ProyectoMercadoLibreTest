package com.cristian.proyectomercadolibre.domain.models

import com.cristian.proyectomercadolibre.data.builder.CountryResponseBuilder
import org.junit.Assert
import org.junit.Test

class CountryTest {
    @Test
    fun `when data is not null`() {
        // given
        val country = CountryResponseBuilder().mapToDomain()

        // Then
        Assert.assertNotNull(country)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val id = "1"
        val name = "Data1"
        val country = CountryResponseBuilder().mapToDomain()

        // Then
        Assert.assertEquals(id, country.id)
        Assert.assertEquals(name, country.name)
    }
}