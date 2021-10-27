package com.cristian.proyectomercadolibre.models

import java.util.*

data class Prices(
    var id: String,
    var type: String,
    var amount: Int,
    var regular_amount: Int,
    var currency_id: String,
    var last_updated: Date,
    var conditions: Conditions,
    var exchange_rate_context: String,
)