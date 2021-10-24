package com.cristian.proyectomercadolibre.models

import java.util.*

data class Conditions(
    var context_restrictions: List<String>,
    var start_time: Date,
    var end_time: Date,
    var eligible: Boolean,
)