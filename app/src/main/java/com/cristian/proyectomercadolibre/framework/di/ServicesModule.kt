package com.cristian.proyectomercadolibre.framework.di

import com.cristian.proyectomercadolibre.framework.data_source.api.categories.CategoriesServices
import com.cristian.proyectomercadolibre.framework.data_source.api.categoriesDetails.CategoriesDetailsServices
import com.cristian.proyectomercadolibre.framework.data_source.api.items.ItemsServices
import com.cristian.proyectomercadolibre.utils.ConstantsServices
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServicesModule {
    private val headerInterceptor = Interceptor { chain ->
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("Authorization", "Bearer \$ACCESS_TOKEN")
            .build()

        println("Request: ${request.headers()}")

        val response = chain.proceed(request)
        print("url final $response")
        response
    }

    private val okHttp = OkHttpClient.Builder().addInterceptor(headerInterceptor)

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ConstantsServices.URL_BASE)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttp.build())
        .build()

    fun getItemsServices(): ItemsServices {
        return retrofit.create(ItemsServices::class.java)
    }

    fun getCategories(): CategoriesServices {
        return retrofit.create(CategoriesServices::class.java)
    }

    fun getCategoriesDetails(): CategoriesDetailsServices {
        return retrofit.create(CategoriesDetailsServices::class.java)
    }
}