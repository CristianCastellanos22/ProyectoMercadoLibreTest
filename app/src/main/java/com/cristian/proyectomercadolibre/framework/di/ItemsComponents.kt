package com.cristian.proyectomercadolibre.framework.di

import com.cristian.proyectomercadolibre.framework.ui.categories.CategoriesActivity
import com.cristian.proyectomercadolibre.framework.ui.categoriesDetails.CategoriesDetailsActivity
import com.cristian.proyectomercadolibre.framework.ui.items.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ItemsModule::class])
interface ItemsComponents {
    fun inject(mainActivity: MainActivity)
    fun inject(categoriesActivity: CategoriesActivity)
    fun inject(categoriesDetailsActivity: CategoriesDetailsActivity)
}