package com.cristian.proyectomercadolibre.framework.ui.categoriesDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cristian.proyectomercadolibre.commons.getOrAwaitValue
import com.cristian.proyectomercadolibre.data.builder.ProductDataResponseBuilder
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoriesDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    @MockK
    private lateinit var categoriesDetailsUseCase: CategoriesDetailsUseCase
    private lateinit var categoriesDetailsViewModel: CategoriesDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        categoriesDetailsViewModel = CategoriesDetailsViewModel(categoriesDetailsUseCase)
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlockingTest {
        // given
        coEvery {
            categoriesDetailsUseCase.getCategoriesDetails("")
        } returns HandlerResponse.Success(ProductDataResponseBuilder().mapToDomain())

        // when
        categoriesDetailsViewModel.getCategoriesDetails("")

        // then
        assertNull(categoriesDetailsViewModel.errors.value)
        assertFalse(categoriesDetailsViewModel.categories.getOrAwaitValue().products.isEmpty())
    }

    @Test
    fun `when response is error with Exception`() = runBlockingTest {
        // given
        coEvery {
            categoriesDetailsUseCase.getCategoriesDetails("")
        } returns HandlerResponse.Failure(Exception("Error"))

        // when
        categoriesDetailsViewModel.getCategoriesDetails("")

        // then
        assertNull(categoriesDetailsViewModel.categories.value)
        assertEquals("Error", categoriesDetailsViewModel.errors.getOrAwaitValue().message)
    }
}