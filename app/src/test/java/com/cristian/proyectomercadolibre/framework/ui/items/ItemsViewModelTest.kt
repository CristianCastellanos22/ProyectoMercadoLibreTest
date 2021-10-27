package com.cristian.proyectomercadolibre.framework.ui.items

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristian.proyectomercadolibre.MainCoroutineRule
import com.cristian.proyectomercadolibre.models.*
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class ItemsViewModelTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var itemsUseCase: ItemsUseCase

    @Mock
    private lateinit var observer: Observer<ResponseData>

    private lateinit var itemsViewModel: ItemsViewModel

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

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUpData() {
        itemsViewModel = ItemsViewModel(itemsUseCase)
    }

    @Test
    fun `testGetItems`() = mainCoroutineRule.dispatcher.runBlockingTest {
        val item = "123"
        itemsViewModel.items.observeForever(observer)
        Mockito.`when`(itemsUseCase.getItem(item)).thenReturn(createItem())
        itemsViewModel.getItems(item)
        Mockito.verify(observer).onChanged(createItem())
    }
}