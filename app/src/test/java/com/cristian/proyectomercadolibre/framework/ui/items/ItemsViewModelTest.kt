package com.cristian.proyectomercadolibre.framework.ui.items

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
class ItemsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    @MockK
    private lateinit var itemsUseCase: ItemsUseCase
    private lateinit var itemsViewModel: ItemsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        itemsViewModel = ItemsViewModel(itemsUseCase)
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlockingTest {
        // given
        coEvery {
            itemsUseCase.getItem("")
        } returns HandlerResponse.Success(ProductDataResponseBuilder().mapToDomain())

        // when
        itemsViewModel.getItems("")

        // then
        assertNull(itemsViewModel.errors.value)
        assertFalse(itemsViewModel.items.getOrAwaitValue().products.isEmpty())
    }

    @Test
    fun `when response is error with Exception`() = runBlockingTest {
        // given
        coEvery {
            itemsUseCase.getItem("")
        } returns HandlerResponse.Failure(Exception("Error"))

        // when
        itemsViewModel.getItems("")

        // then
        assertNull(itemsViewModel.items.value)
        assertEquals("Error", itemsViewModel.errors.getOrAwaitValue().message)
    }
}