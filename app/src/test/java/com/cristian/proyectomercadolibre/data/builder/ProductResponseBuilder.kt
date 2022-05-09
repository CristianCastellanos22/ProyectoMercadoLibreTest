package com.cristian.proyectomercadolibre.data.builder

import com.cristian.proyectomercadolibre.data.remote.models.ProductResponse
import com.cristian.proyectomercadolibre.data.remote.models.SellerAddressResponse
import com.cristian.proyectomercadolibre.data.remote.models.mapToDomain
import com.cristian.proyectomercadolibre.domain.models.Product

class ProductResponseBuilder(
    private val id: String = "1",
    private val title: String = "title",
    private val thumbnail: String = "https:123",
    private val price: Int = 123,
    private val soldQuantity: Int = 15,
    private val sellerAddress: SellerAddressResponse = SellerAddressResponseBuilder().mapToRemote(),
) {

    fun mapToDomain(): Product = Product(
        id = id,
        title = title,
        thumbnail = thumbnail,
        price = price,
        soldQuantity = soldQuantity,
        sellerAddress = sellerAddress.mapToDomain()
    )

    fun mapToRemote(): ProductResponse = ProductResponse(
        id = id,
        title = title,
        thumbnail = thumbnail,
        price = price,
        soldQuantity = soldQuantity,
        sellerAddress = sellerAddress
    )

}