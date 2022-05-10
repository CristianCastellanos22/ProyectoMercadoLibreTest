package com.cristian.proyectomercadolibre.data.repository

import com.cristian.proyectomercadolibre.data.builder.ProductDataResponseBuilder
import com.cristian.proyectomercadolibre.data.remote.ItemsApiSourceAdapter
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
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemsRepositoryAdapterTest {
    @MockK
    private lateinit var itemsApiSourceAdapter: ItemsApiSourceAdapter

    private lateinit var itemsRepositoryAdapter: ItemsRepositoryAdapter

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        itemsRepositoryAdapter = ItemsRepositoryAdapter(itemsApiSourceAdapter)
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlockingTest {
        // given
        coEvery {
            itemsApiSourceAdapter.getItems("")
        } returns HandlerResponse.Success(ProductDataResponseBuilder().mapToDomain())

        // when
        val response = itemsApiSourceAdapter.getItems("")

        // then
        Assert.assertEquals(HandlerResponse.Success(ProductDataResponseBuilder().mapToDomain()), response)
    }

    @Test
    fun `when response is error with Exception`() = runBlockingTest {
        // given
        coEvery {
            itemsApiSourceAdapter.getItems("")
        } returns HandlerResponse.Failure(Exception("Error"))

        // when
        val response = itemsApiSourceAdapter.getItems("")

        // then
        Assert.assertEquals("Error", (response as HandlerResponse.Failure).exception.message)
    }
}