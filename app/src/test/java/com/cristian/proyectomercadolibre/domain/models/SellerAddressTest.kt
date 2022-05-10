package com.cristian.proyectomercadolibre.domain.models

import com.cristian.proyectomercadolibre.data.builder.CityResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.CountryResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.SellerAddressResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.StateResponseBuilder
import org.junit.Assert
import org.junit.Test

class SellerAddressTest {
    @Test
    fun `when data is not null`() {
        // given
        val seller = SellerAddressResponseBuilder().mapToDomain()

        // Then
        Assert.assertNotNull(seller)
    }

    @Test
    fun `when data is equals to`() {
        // given
        val id = "1"
        val comment = "as"
        val addressLine = "cra"
        val zipCode = "1123"
        val country = CountryResponseBuilder().mapToDomain()
        val state = StateResponseBuilder().mapToDomain()
        val city = CityResponseBuilder().mapToDomain()
        val latitude = "178"
        val longitude = "-145"
        val seller = SellerAddressResponseBuilder().mapToDomain()

        // Then
        Assert.assertEquals(id, seller.id)
        Assert.assertEquals(comment, seller.comment)
        Assert.assertEquals(addressLine, seller.addressLine)
        Assert.assertEquals(zipCode, seller.zipCode)
        Assert.assertEquals(country, seller.country)
        Assert.assertEquals(state, seller.state)
        Assert.assertEquals(city, seller.city)
        Assert.assertEquals(latitude, seller.latitude)
        Assert.assertEquals(longitude, seller.longitude)
    }
}