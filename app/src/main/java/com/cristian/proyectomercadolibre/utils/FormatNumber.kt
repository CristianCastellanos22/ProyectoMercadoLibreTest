package com.cristian.proyectomercadolibre.utils

import java.text.NumberFormat
import java.util.*

object FormatNumber {
    fun formatNumber(number: Double): String {
        val valor: Double = number
        val region = Locale.getDefault()
        val formatoMoneda = NumberFormat.getCurrencyInstance(region)
        val convert = formatoMoneda.format(valor)
        val notDecimal = convert.substring(0, convert.length - 3)
        return (notDecimal)
    }
}