package com.cristian.proyectomercadolibre.data.remote.models

import com.cristian.proyectomercadolibre.domain.models.SellerAddress
import com.google.gson.annotations.SerializedName

data class SellerAddressResponse(
    @SerializedName("id") val id: String,
    @SerializedName("comment") val comment: String,
    @SerializedName("address_line") val addressLine: String,
    @SerializedName("zip_code") val zipCode: String,
    @SerializedName("country") val country: CountryResponse,
    @SerializedName("state") val state: StateResponse,
    @SerializedName("city") val city: CityResponse,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
)

fun SellerAddressResponse.mapToDomain(): SellerAddress {
    return with(this) {
        SellerAddress(
            id = id,
            comment = comment,
            addressLine = addressLine,
            zipCode = zipCode,
            country = country.mapToDomain(),
            state = state.mapToDomain(),
            city = city.mapToDomain(),
            latitude = latitude,
            longitude = longitude
        )
    }
}
