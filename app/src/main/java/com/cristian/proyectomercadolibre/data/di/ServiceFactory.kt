package com.cristian.proyectomercadolibre.data.di

import com.cristian.proyectomercadolibre.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ServiceFactory {
    private const val TIMEOUT = 30_000L
    var baseUrl = BuildConfig.URL_BASE

    fun <T> createRepositoryApi(
        repositoryApiClass: Class<T>,
        timeOut: Long = TIMEOUT,
        timeUnit: TimeUnit = TimeUnit.MILLISECONDS,
        url: String = baseUrl
    ): T {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val myClient = OkHttpClient().newBuilder()
            .connectTimeout(timeOut, timeUnit)
            .readTimeout(timeOut, timeUnit)
            .build()

        return Retrofit.Builder()
            .client(myClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(repositoryApiClass)
    }
}
