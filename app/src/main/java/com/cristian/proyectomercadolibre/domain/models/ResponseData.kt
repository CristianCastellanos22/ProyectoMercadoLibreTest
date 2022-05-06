package com.cristian.proyectomercadolibre.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseData(
    val products: List<Product>
): Parcelable