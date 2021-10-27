package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristian.proyectomercadolibre.MainCoroutineRule
import com.cristian.proyectomercadolibre.models.*
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
import java.util.*

@RunWith(MockitoJUnitRunner.Silent::class)
class CategoriesDetailsViewModelTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var categoriesDetailsUseCase: CategoriesDetailsUseCase

    @Mock
    private lateinit var observer: Observer<ResponseData>

    private lateinit var categoriesDetailsViewModel: CategoriesDetailsViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private fun createResults(): List<Result> = mutableListOf(
        Result(
            "1",
            "result1",
            "1234.jpg",
            1,
            2,
            SellerAddress(
                "address1",
                "comment1",
                "line1",
                "zip123",
                Country("1","country1"),
                State("1", "state1"),
                City("1", "city1"),
                "123",
                "123"
            )
        )
    )

    private fun createCategoriesDetails(): ResponseData = ResponseData(
        "1",
        "zon1",
        "data",
        createResults()
    )

    @Before
    fun setUpData() {
        categoriesDetailsViewModel = CategoriesDetailsViewModel(categoriesDetailsUseCase)
    }

    @Test
    fun `testGetCategories`() = mainCoroutineRule.dispatcher.runBlockingTest {
        val details = "123"
        categoriesDetailsViewModel.categories.observeForever(observer)
        Mockito.`when`(categoriesDetailsUseCase.getCategoriesDetails(details)).thenReturn(createCategoriesDetails())
        categoriesDetailsViewModel.getCategoriesDetails(details)
        Mockito.verify(observer).onChanged(createCategoriesDetails())
    }
}