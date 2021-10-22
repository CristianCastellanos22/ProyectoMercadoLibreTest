package com.cristian.proyectomercadolibre.models

data class Shipping(
    var free_shipping: Boolean,
    var mode: String,
    var tags: List<String>,
    var logistic_type: String,
    var store_pick_up: Boolean,
)