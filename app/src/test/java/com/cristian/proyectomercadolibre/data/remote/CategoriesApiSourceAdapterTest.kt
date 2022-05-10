package com.cristian.proyectomercadolibre.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cristian.proyectomercadolibre.data.di.ServiceFactory
import com.cristian.proyectomercadolibre.data.remote.models.HandlerResponse
import com.cristian.proyectomercadolibre.domain.models.Categories
import com.cristian.proyectomercadolibre.utils.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
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
class CategoriesApiSourceAdapterTest {
    private lateinit var categoriesApiSourceAdapter: CategoriesApiSourceAdapter

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockServer: MockWebServer

    @Before
    fun setUp() {
        System.setProperty("javax.net.ssl.trustStore", "NONE")
        mockServer = MockWebServer()
        mockServer.start()
        ServiceFactory.baseUrl = mockServer.url("/").toString()
        categoriesApiSourceAdapter = CategoriesApiSourceAdapter()
    }

    @Test
    fun `when call getCategories get successful response 200`() = runBlocking {
        // given
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(Utils().categoriesMock)
        )

        // when
        val response = categoriesApiSourceAdapter.getCategories()

        // then
        assertNotNull(response)
        assertNotNull(ServiceFactory.baseUrl)
    }

    @Test
    fun `when call getCategories get error response`() = runBlocking {
        // given
        mockServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT).setSocketPolicy(
                SocketPolicy.DISCONNECT_AT_START)
        )

        // when
        val response = categoriesApiSourceAdapter.getCategories()
        val responseFailure = response as HandlerResponse.Failure

        // then
        assertNotNull(responseFailure.exception)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }
}