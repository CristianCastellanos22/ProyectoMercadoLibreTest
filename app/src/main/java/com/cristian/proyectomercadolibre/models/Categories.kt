package com.cristian.proyectomercadolibre.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Categories(
    val id: String,
    val name: String,
): Parcelable