package com.cristian.proyectomercadolibre.data.remote.models

sealed class HandlerResponse<out T> {
    data class Success<out R>(val value: R): HandlerResponse<R>()
    data class Failure(val exception: Exception): HandlerResponse<Nothing>()
}
