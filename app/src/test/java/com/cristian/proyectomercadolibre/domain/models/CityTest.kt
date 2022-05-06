package com.cristian.proyectomercadolibre.domain.models

import com.cristian.proyectomercadolibre.data.builder.CityResponseBuilder
import org.junit.Assert
import org.junit.Test

class CityTest {
    @Test
    fun `when data is not null`() {
        // given
        val city = CityResponseBuilder().mapToDomain()

        // Then
        Assert.assertNotNull(city)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val id = "1"
        val name = "Data1"
        val city = CityResponseBuilder().mapToDomain()

        // Then
        Assert.assertEquals(id, city.id)
        Assert.assertEquals(name, city.name)
    }
}