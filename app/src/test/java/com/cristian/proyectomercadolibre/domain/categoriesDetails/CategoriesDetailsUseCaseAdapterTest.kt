package com.cristian.proyectomercadolibre.domain.categoriesDetails

import com.cristian.proyectomercadolibre.models.*
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoriesDetailsUseCaseAdapterTest : TestCase() {

    @Mock
    private lateinit var categoriesDetailsRepository: CategoriesDetailsRepository

    @InjectMocks
    private lateinit var categoriesDetailsUseCaseAdapter: CategoriesDetailsUseCaseAdapter

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

    @Test
    fun `testGetCategoriesDetails`() {
        runBlocking {
            val expect = createCategoriesDetails()
            val details = "123"
            val returnResponse = createCategoriesDetails()
            Mockito.`when`(categoriesDetailsRepository.getCategoriesDetails(details)).thenReturn(returnResponse)
            val result = categoriesDetailsUseCaseAdapter.getCategoriesDetails(details)
            assertEquals(expect, result)
        }
    }
}