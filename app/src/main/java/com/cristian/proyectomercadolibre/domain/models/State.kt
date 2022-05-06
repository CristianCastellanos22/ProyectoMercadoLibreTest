package com.cristian.proyectomercadolibre.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    val id: String,
    val name: String,
): Parcelable