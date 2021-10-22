package com.cristian.proyectomercadolibre.models

data class Seller(
    var id: Int,
    var permalink: Object,
    var registration_date: Object,
    var car_dealer: Boolean,
    var real_estate_agency: Boolean,
    var tags: Object,
)