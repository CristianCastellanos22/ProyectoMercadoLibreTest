package com.cristian.proyectomercadolibre.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Result(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Int,
    val sold_quantity: Int,
    val seller_address:@RawValue SellerAddress,
): Parcelable