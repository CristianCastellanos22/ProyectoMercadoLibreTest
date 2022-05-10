package com.cristian.proyectomercadolibre.domain.categories

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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoriesUseCaseAdapterTest {
    @MockK
    private lateinit var categoriesRepository: CategoriesRepository

    private lateinit var categoriesUseCaseAdapter: CategoriesUseCaseAdapter

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        categoriesUseCaseAdapter = CategoriesUseCaseAdapter(categoriesRepository)
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlockingTest {
        // given
        coEvery {
            categoriesRepository.getCategories()
        } returns HandlerResponse.Success(listOf(CategoriesResponseBuilder().mapToDomain()))

        // when
        val response = categoriesRepository.getCategories()

        // then
        assertEquals(HandlerResponse.Success(listOf(CategoriesResponseBuilder().mapToDomain())), response)
    }

    @Test
    fun `when response is error with Exception`() = runBlockingTest {
        // given
        coEvery {
            categoriesRepository.getCategories()
        } returns HandlerResponse.Failure(Exception("Error"))

        // when
        val response = categoriesRepository.getCategories()

        // then
        assertEquals("Error", (response as HandlerResponse.Failure).exception.message)
    }
}