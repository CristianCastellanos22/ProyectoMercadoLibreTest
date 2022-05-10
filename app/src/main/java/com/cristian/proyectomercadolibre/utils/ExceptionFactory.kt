package com.cristian.proyectomercadolibre.utils

import com.cristian.proyectomercadolibre.domain.models.errors.NetworkException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ExceptionFactory {
    companion object {
        fun resolveError(e: Exception): Exception {
            var error = e
            when (e) {
                is SocketTimeoutException -> {
                    error = NetworkException("connection error!")
                }
                is ConnectException -> {
                    error = NetworkException("no internet access!")
                }
                is UnknownHostException -> {
                    error = NetworkException("no internet access!")
                }
            }

            if (e is HttpException) {
                error = NetworkException(e.code(), "http error!")
            }

            return error
        }
    }
}
