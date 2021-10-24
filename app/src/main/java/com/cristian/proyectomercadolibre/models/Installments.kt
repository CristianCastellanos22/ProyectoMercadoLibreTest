package com.cristian.proyectomercadolibre.models

data class Installments(
    var quantity: Int,
    var amount: Double,
    var rate: Int,
    var currency_id: String,
)