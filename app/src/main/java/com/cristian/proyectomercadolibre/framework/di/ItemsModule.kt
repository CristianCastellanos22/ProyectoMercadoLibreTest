package com.cristian.proyectomercadolibre.framework.di

import android.content.Context
import com.cristian.proyectomercadolibre.data.categories.CategoriesRepositoryAdapter
import com.cristian.proyectomercadolibre.data.categoriesDetails.CategoriesDetailsRepositoryAdapter
import com.cristian.proyectomercadolibre.data.items.ItemsRepositoryAdapter
import com.cristian.proyectomercadolibre.domain.categories.CategoriesUseCaseAdapter
import com.cristian.proyectomercadolibre.domain.categoriesDetails.CategoriesDetailsUseCaseAdapter
import com.cristian.proyectomercadolibre.domain.items.ItemUseCaseAdapter
import com.cristian.proyectomercadolibre.framework.data_source.api.categories.CategoriesApiSourceAdapter
import com.cristian.proyectomercadolibre.framework.data_source.api.categoriesDetails.CategoriesDetailsApiSourceAdapter
import com.cristian.proyectomercadolibre.framework.data_source.api.items.ItemsApiSourceAdapter
import com.cristian.proyectomercadolibre.framework.ui.categories.CategoriesViewModelFactory
import com.cristian.proyectomercadolibre.framework.ui.categoriesDetails.CategoriesDetailsViewModelFactory
import com.cristian.proyectomercadolibre.framework.ui.items.ItemsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ItemsModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideItems() = ItemsViewModelFactory(provideUseCase())

    @Provides
    @Singleton
    fun provideUseCase() = ItemUseCaseAdapter(
        ItemsRepositoryAdapter(
            ItemsApiSourceAdapter(ServicesModule.getItemsServices())
        )
    )

    @Provides
    @Singleton
    fun provideCategories() = CategoriesViewModelFactory(categoriesProvide())

    @Provides
    @Singleton
    fun categoriesProvide() = CategoriesUseCaseAdapter(
        CategoriesRepositoryAdapter(
            CategoriesApiSourceAdapter(ServicesModule.getCategories())
        )
    )

    @Provides
    @Singleton
    fun provideCategoriesDetails() = CategoriesDetailsViewModelFactory(categoriesDetailsProvide())

    @Provides
    @Singleton
    fun categoriesDetailsProvide() = CategoriesDetailsUseCaseAdapter(
        CategoriesDetailsRepositoryAdapter(
            CategoriesDetailsApiSourceAdapter(ServicesModule.getCategoriesDetails())
        )
    )

}