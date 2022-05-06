package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.data.builder.ResultResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.SellerAddressResponseBuilder
import org.junit.Assert
import org.junit.Test

class ResultResponseTest {
    @Test
    fun `when data is not null`() {
        // given
        val result = ResultResponseBuilder().mapToRemote()

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
        val sellerAddress = SellerAddressResponseBuilder().mapToRemote()
        val result = ResultResponseBuilder().mapToRemote()

        // Then
        Assert.assertEquals(id, result.id)
        Assert.assertEquals(title, result.title)
        Assert.assertEquals(thumbnail, result.thumbnail)
        Assert.assertEquals(price, result.price)
        Assert.assertEquals(soldQuantity, result.soldQuantity)
        Assert.assertEquals(sellerAddress, result.sellerAddress)
    }
}