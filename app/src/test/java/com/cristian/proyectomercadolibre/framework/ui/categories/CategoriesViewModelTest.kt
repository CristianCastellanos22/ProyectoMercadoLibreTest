package com.cristian.proyectomercadolibre.framework.ui.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristian.proyectomercadolibre.MainCoroutineRule
import com.cristian.proyectomercadolibre.models.Categories
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class CategoriesViewModelTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var categoriesUseCase: CategoriesUseCase

    @Mock
    private lateinit var observer: Observer<List<Categories>>

    private lateinit var categoriesViewModel: CategoriesViewModel

    private fun createCategories(): List<Categories> = mutableListOf(
        Categories(
            "1",
            "fakeCategories1"
        ),
        Categories(
            "2",
            "fakeCategories2"
        )
    )

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUpData() {
        categoriesViewModel = CategoriesViewModel(categoriesUseCase)
    }

    @Test
    fun `testGetCategories`() = mainCoroutineRule.dispatcher.runBlockingTest {
        categoriesViewModel.categories.observeForever(observer)
        Mockito.`when`(categoriesUseCase.getCategories()).thenReturn(createCategories())
        categoriesViewModel.getCategories()
        Mockito.verify(observer).onChanged(createCategories())
    }
}