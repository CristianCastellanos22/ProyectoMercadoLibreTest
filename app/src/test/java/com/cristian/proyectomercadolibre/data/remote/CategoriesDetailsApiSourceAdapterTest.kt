package com.cristian.proyectomercadolibre.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cristian.proyectomercadolibre.data.di.ServiceFactory
import com.cristian.proyectomercadolibre.domain.models.ResponseData
import com.cristian.proyectomercadolibre.utils.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Config.OLDEST_SDK])
@ExperimentalCoroutinesApi
class CategoriesDetailsApiSourceAdapterTest {
    private lateinit var categoriesDetailsApiSourceAdapter: CategoriesDetailsApiSourceAdapter

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockServer: MockWebServer

    @Before
    fun setUp() {
        System.setProperty("javax.net.ssl.trustStore", "NONE")
        mockServer = MockWebServer()
        mockServer.start()
        ServiceFactory.baseUrl = mockServer.url("/").toString()
        categoriesDetailsApiSourceAdapter = CategoriesDetailsApiSourceAdapter()
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlocking {
        // given
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(Utils().categoryDetailsMock)
        )

        // when
        val response = categoriesDetailsApiSourceAdapter.getCategoriesDetails("")

        // then
        assertNotNull(response)
        assertNotNull(ServiceFactory.baseUrl)
    }

    @Test
    fun `when call getCategories get error response`() = runBlocking {
        // given
        mockServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT)
        )

        // when
        val response = categoriesDetailsApiSourceAdapter.getCategoriesDetails("")

        // then
        assertEquals(ResponseData(listOf()), response)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }
}