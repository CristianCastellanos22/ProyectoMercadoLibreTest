package com.cristian.proyectomercadolibre.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Int,
    val soldQuantity: Int,
    val sellerAddress: SellerAddress,
): Parcelable