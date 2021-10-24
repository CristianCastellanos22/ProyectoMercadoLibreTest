package com.cristian.proyectomercadolibre.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class SellerAddress(
    var id: String,
    var comment: String,
    var address_line: String,
    var zip_code: String,
    var country:@RawValue Country,
    var state:@RawValue State,
    var city:@RawValue City,
    var latitude: String,
    var longitude: String,
): Parcelable