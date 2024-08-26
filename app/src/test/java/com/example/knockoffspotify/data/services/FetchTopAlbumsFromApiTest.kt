package com.example.knockoffspotify.data.services

import app.cash.turbine.test
import com.example.knockoffspotify.data.TestDatasource
import com.example.knockoffspotify.data.remote.AlbumsApiService
import com.example.knockoffspotify.model.TopAlbums
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class FetchTopAlbumsFromApiTest {
    @Test
    fun `can get albums from api`() = runTest {
        // given
        val feed = TestDatasource().getFeed()
        val fetchAlbumsService = mockk<AlbumsApiService> {
            coEvery { getTopAlbums() } returns TopAlbums(feed = feed)
        }
        val testSubject = FetchTopAlbumsFromApi(fetchAlbumsService)

        // when // then
        testSubject.fetchAlbums().test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(feed.topAlbum), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can propagate exception when IOException thrown`() = runTest {
        val fetchAlbumsService = mockk<AlbumsApiService> {
            coEvery { getTopAlbums() } throws IOException("Network Error occurred")
        }
        val testSubject = FetchTopAlbumsFromApi(fetchAlbumsService)

        testSubject.fetchAlbums().test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}