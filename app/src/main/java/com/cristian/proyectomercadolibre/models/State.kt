package com.cristian.proyectomercadolibre.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class State(
    var id: String,
    var name: String,
): Parcelable