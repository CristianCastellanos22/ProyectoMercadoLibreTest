package com.cristian.proyectomercadolibre.data.repository

import com.cristian.proyectomercadolibre.data.builder.ProductDataResponseBuilder
import com.cristian.proyectomercadolibre.data.remote.CategoriesDetailsApiSourceAdapter
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
class CategoriesDetailsRepositoryAdapterTest {
    @MockK
    private lateinit var categoriesDetailsApiSourceAdapter: CategoriesDetailsApiSourceAdapter

    private lateinit var categoriesDetailsRepositoryAdapter: CategoriesDetailsRepositoryAdapter

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        categoriesDetailsRepositoryAdapter = CategoriesDetailsRepositoryAdapter(categoriesDetailsApiSourceAdapter)
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlockingTest {
        // given
        coEvery {
            categoriesDetailsApiSourceAdapter.getCategoriesDetails("")
        } returns HandlerResponse.Success(ProductDataResponseBuilder().mapToDomain())

        // when
        val response = categoriesDetailsRepositoryAdapter.getCategoriesDetails("")

        // then
        assertEquals(HandlerResponse.Success(ProductDataResponseBuilder().mapToDomain()), response)
    }

    @Test
    fun `when response is error with Exception`() = runBlockingTest {
        // given
        coEvery {
            categoriesDetailsApiSourceAdapter.getCategoriesDetails("")
        } returns HandlerResponse.Failure(Exception("Error"))

        // when
        val response = categoriesDetailsRepositoryAdapter.getCategoriesDetails("")

        // then
        assertEquals("Error", (response as HandlerResponse.Failure).exception.message)
    }

}