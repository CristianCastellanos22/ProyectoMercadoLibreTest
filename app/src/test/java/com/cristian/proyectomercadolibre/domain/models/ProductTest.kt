package com.cristian.proyectomercadolibre.domain.models

import com.cristian.proyectomercadolibre.data.builder.ResultResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.SellerAddressResponseBuilder
import org.junit.Assert
import org.junit.Test

class ProductTest {
    @Test
    fun `when data is not null`() {
        // given
        val result = ResultResponseBuilder().mapToDomain()

        // Then
        Assert.assertNotNull(result)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val id = "1"
        val title = "title"
        val thumbnail = "https:123"
        val price = 123
        val soldQuantity = 15
        val sellerAddress = SellerAddressResponseBuilder().mapToDomain()
        val result = ResultResponseBuilder().mapToDomain()

        // Then
        Assert.assertEquals(id, result.id)
        Assert.assertEquals(title, result.title)
        Assert.assertEquals(thumbnail, result.thumbnail)
        Assert.assertEquals(price, result.price)
        Assert.assertEquals(soldQuantity, result.soldQuantity)
        Assert.assertEquals(sellerAddress, result.sellerAddress)
    }
}