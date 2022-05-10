package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.domain.models.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("price") val price: Int,
    @SerializedName("sold_quantity") val soldQuantity: Int,
    @SerializedName("seller_address") val sellerAddress: SellerAddressResponse,
)

fun ProductResponse.mapToDomain(): Product {
    return with(this) {
        Product(
            id = id,
            title = title,
            thumbnail = thumbnail,
            price = price,
            soldQuantity = soldQuantity,
            sellerAddress = sellerAddress.mapToDomain()
        )
    }
}
