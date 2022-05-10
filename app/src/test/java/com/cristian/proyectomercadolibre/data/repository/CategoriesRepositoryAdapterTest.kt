package com.cristian.proyectomercadolibre.data.repository

import com.cristian.proyectomercadolibre.data.builder.CategoriesResponseBuilder
import com.cristian.proyectomercadolibre.data.builder.ProductDataResponseBuilder
import com.cristian.proyectomercadolibre.data.remote.CategoriesApiSourceAdapter
import com.cristian.proyectomercadolibre.data.remote.CategoriesDetailsApiSourceAdapter
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.categories.CategoriesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoriesRepositoryAdapterTest {

    @MockK
    private lateinit var categoriesApiSource: CategoriesApiSourceAdapter

    private lateinit var categoriesRepositoryAdapter: CategoriesRepositoryAdapter

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        categoriesRepositoryAdapter = CategoriesRepositoryAdapter(categoriesApiSource)
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlockingTest {
        // given
        coEvery {
            categoriesApiSource.getCategories()
        } returns HandlerResponse.Success(listOf(CategoriesResponseBuilder().mapToDomain()))

        // when
        val response = categoriesRepositoryAdapter.getCategories()

        // then
        assertEquals(HandlerResponse.Success(listOf(CategoriesResponseBuilder().mapToDomain())), response)
    }

    @Test
    fun `when response is error with Exception`() = runBlockingTest {
        // given
        coEvery {
            categoriesRepositoryAdapter.getCategories()
        } returns HandlerResponse.Failure(Exception("Error"))

        // when
        val response = categoriesRepositoryAdapter.getCategories()

        // then
        assertEquals("Error", (response as HandlerResponse.Failure).exception.message)
    }


}