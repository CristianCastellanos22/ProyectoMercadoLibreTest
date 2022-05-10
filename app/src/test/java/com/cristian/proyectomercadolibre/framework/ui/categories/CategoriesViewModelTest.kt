package com.cristian.proyectomercadolibre.framework.ui.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cristian.proyectomercadolibre.commons.getOrAwaitValue
import com.cristian.proyectomercadolibre.data.builder.CategoriesResponseBuilder
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoriesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    @MockK
    private lateinit var categoriesUseCase: CategoriesUseCase
    private lateinit var categoriesViewModel: CategoriesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        categoriesViewModel = CategoriesViewModel(categoriesUseCase)
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlockingTest {
        // given
        coEvery {
            categoriesUseCase.getCategories()
        } returns HandlerResponse.Success(listOf(CategoriesResponseBuilder().mapToDomain()))

        // when
        categoriesViewModel.getCategories()

        // then
        Assert.assertNull(categoriesViewModel.errors.value)
        Assert.assertFalse(categoriesViewModel.categories.getOrAwaitValue().isEmpty())
    }

    @Test
    fun `when response is error with Exception`() = runBlockingTest {
        // given
        coEvery {
            categoriesUseCase.getCategories()
        } returns HandlerResponse.Failure(Exception("Error"))

        // when
        categoriesViewModel.getCategories()

        // then
        Assert.assertNull(categoriesViewModel.categories.value)
        Assert.assertEquals("Error", categoriesViewModel.errors.getOrAwaitValue().message)
    }
}