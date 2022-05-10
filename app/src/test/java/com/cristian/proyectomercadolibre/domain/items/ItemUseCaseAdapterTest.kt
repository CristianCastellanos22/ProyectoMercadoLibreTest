package com.cristian.proyectomercadolibre.domain.items

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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemUseCaseAdapterTest {
    @MockK
    private lateinit var itemsRepository: ItemsRepository

    private lateinit var itemsUseCaseAdapter: ItemUseCaseAdapter

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        itemsUseCaseAdapter = ItemUseCaseAdapter(itemsRepository)
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlockingTest {
        // given
        coEvery {
            itemsRepository.getItems("")
        } returns HandlerResponse.Success(ProductDataResponseBuilder().mapToDomain())

        // when
        val response = itemsRepository.getItems("")

        // then
        assertEquals(HandlerResponse.Success(ProductDataResponseBuilder().mapToDomain()), response)
    }

    @Test
    fun `when response is error with Exception`() = runBlockingTest {
        // given
        coEvery {
            itemsRepository.getItems("")
        } returns HandlerResponse.Failure(Exception("Error"))

        // when
        val response = itemsRepository.getItems("")

        // then
        assertEquals("Error", (response as HandlerResponse.Failure).exception.message)
    }
}