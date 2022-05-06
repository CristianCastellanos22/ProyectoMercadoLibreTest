package com.cristian.proyectomercadolibre.data.builder

import com.cristian.proyectomercadolibre.data.remote.models.*
import com.cristian.proyectomercadolibre.domain.models.SellerAddress

class SellerAddressResponseBuilder(
    private val id: String = "1",
    private val comment: String = "as",
    private val addressLine: String = "cra",
    private val zipCode: String = "1123",
    private val country: CountryResponse = CountryResponseBuilder().mapToRemote(),
    private val state: StateResponse = StateResponseBuilder().mapToRemote(),
    private val city: CityResponse = CityResponseBuilder().mapToRemote(),
    private val latitude: String = "178",
    private val longitude: String = "-145",
) {
    fun mapToDomain(): SellerAddress = SellerAddress(
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

    fun mapToRemote(): SellerAddressResponse = SellerAddressResponse(
        id = id,
        comment = comment,
        addressLine = addressLine,
        zipCode = zipCode,
        country = country,
        state = state,
        city = city,
        latitude = latitude,
        longitude = longitude
    )
}