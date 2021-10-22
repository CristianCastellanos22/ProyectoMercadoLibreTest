package com.cristian.proyectomercadolibre.framework.di

import com.cristian.proyectomercadolibre.framework.data_source.api.ItemsServices
import com.cristian.proyectomercadolibre.utils.ConstantsServices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServicesModule {
    private val headerInterceptor = Interceptor { chain ->
        var request = chain.request()

        request = request.newBuilder().addHeader("Authorization", "Bearer '$'ACCESS_TOKEN").build()
        val response = chain.proceed(request)
        response
    }

    private val okHttp = OkHttpClient.Builder().addInterceptor(headerInterceptor)

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(ConstantsServices.URL_BASE).addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getItemsServices(): ItemsServices {
        return retrofit.create(ItemsServices::class.java)
    }
}