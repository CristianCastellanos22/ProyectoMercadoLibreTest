package com.cristian.proyectomercadolibre.domain.items

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
class ItemUseCaseAdapterTest : TestCase() {

    @Mock
    private lateinit var itemsRepository: ItemsRepository

    @InjectMocks
    private lateinit var itemUseCaseAdapter: ItemUseCaseAdapter

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

    private fun createItem(): ResponseData = ResponseData(
        "1",
        "zon1",
        "data",
        createResults()
    )

    @Test
    fun `testGetItem`() {
        runBlocking {
            val expect = createItem()
            val item = "1234"
            val returnResponse = createItem()
            Mockito.`when`(itemsRepository.getItems(item)).thenReturn(returnResponse)
            val result = itemUseCaseAdapter.getItem(item)
            assertEquals(expect, result)
        }
    }
}