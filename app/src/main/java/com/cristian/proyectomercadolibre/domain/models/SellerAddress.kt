package com.cristian.proyectomercadolibre.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellerAddress(
    val id: String,
    val comment: String,
    val addressLine: String,
    val zipCode: String,
    val country: Country,
    val state: State,
    val city: City,
    val latitude: String,
    val longitude: String,
): Parcelable