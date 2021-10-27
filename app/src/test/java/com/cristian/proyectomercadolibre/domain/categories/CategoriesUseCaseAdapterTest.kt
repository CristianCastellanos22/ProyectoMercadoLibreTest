package com.cristian.proyectomercadolibre.domain.categories

import com.cristian.proyectomercadolibre.models.Categories
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoriesUseCaseAdapterTest : TestCase() {

    @Mock
    private lateinit var categoriesRepository: CategoriesRepository

    @InjectMocks
    private lateinit var categoriesUseCaseAdapter: CategoriesUseCaseAdapter

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

    @Test
    fun `testGetCategories`() {
        runBlocking {
            val expect = false
            val returnResponse = createCategories()
            Mockito.`when`(categoriesRepository.getCategories()).thenReturn(returnResponse)
            val result = categoriesUseCaseAdapter.getCategories()
            assertEquals(expect, result.isEmpty())
        }
    }

    @Test
    fun `testGetCategoriesEmpty`() {
        runBlocking {
            val expect = true
            val returnResponse = createCategories()
            Mockito.`when`(categoriesRepository.getCategories()).thenReturn(returnResponse)
            val result = categoriesUseCaseAdapter.getCategories()
            assertEquals(expect, result.isNotEmpty())
        }
    }
}