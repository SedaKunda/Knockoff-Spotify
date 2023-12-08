package com.example.knockoffspotify.top_abums

import app.cash.turbine.test
import com.example.knockoffspotify.data.TestDatasource
import com.example.knockoffspotify.data.remote.TopAlbumsApiService
import com.example.knockoffspotify.model.TopAlbums
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class FetchAlbumsFromApiTest {
    @Test
    fun `can get albums from api`() = runTest {
        // given
        val feed = TestDatasource().getFeed()
        val fetchAlbumsService = mockk<TopAlbumsApiService> {
            coEvery { getTopAlbums() } returns TopAlbums(feed = feed)
        }
        val testSubject = FetchAlbumsFromApi(fetchAlbumsService)

        // when // then
        testSubject.fetchAlbums().test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(feed.entry), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can propagate exception when IOException thrown`() = runTest {
        val fetchAlbumsService = mockk<TopAlbumsApiService> {
            coEvery { getTopAlbums() } throws IOException("Network Error occurred")
        }
        val testSubject = FetchAlbumsFromApi(fetchAlbumsService)

        testSubject.fetchAlbums().test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}