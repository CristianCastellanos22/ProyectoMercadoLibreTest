package com.cristian.proyectomercadolibre.domain.models

import com.cristian.proyectomercadolibre.data.builder.CategoriesResponseBuilder
import org.junit.Assert
import org.junit.Test

class CategoriesTest {
    @Test
    fun `when data is not null`() {
        // given
        val categories = CategoriesResponseBuilder().mapToDomain()

        // Then
        Assert.assertNotNull(categories)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val id = "1"
        val name = "Data1"
        val categories = CategoriesResponseBuilder().mapToDomain()

        // Then
        Assert.assertEquals(id, categories.id)
        Assert.assertEquals(name, categories.name)
    }
}