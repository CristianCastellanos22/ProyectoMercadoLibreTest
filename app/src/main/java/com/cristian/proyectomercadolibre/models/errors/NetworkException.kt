package com.cristian.proyectomercadolibre.models.errors

class NetworkException: Exception {
    constructor(): super()
    constructor(message: String) : super(message)
    constructor(code: Int, message: String) : super()
}