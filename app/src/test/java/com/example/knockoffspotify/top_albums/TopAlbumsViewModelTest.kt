package com.example.knockoffspotify.top_albums

import app.cash.turbine.test
import com.example.knockoffspotify.data.TestDatasource
import com.example.knockoffspotify.data.remote.TopAlbumsApiService
import com.example.knockoffspotify.data.services.FetchAlbumsFromApi
import com.example.knockoffspotify.helpers.MainCoroutineRule
import com.example.knockoffspotify.model.TopAlbums
import com.example.knockoffspotify.ui.top_albums.TopAlbumsViewModel
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class TopAlbumsViewModelTest {

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Test
    fun `getAlbums can get albums`() = runTest {
        // given
        val feed = TestDatasource().getFeed()
        val fetchAlbumsService = mockk<TopAlbumsApiService> {
            coEvery { getTopAlbums() } returns TopAlbums(feed = feed)
        }
        val fetchAlbumsFromApiTest = FetchAlbumsFromApi(fetchAlbumsService)
        val testSubject = TopAlbumsViewModel(fetchAlbumsFromApi = fetchAlbumsFromApiTest)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(entries = feed.album), awaitItem())
        }
    }

    @Test
    fun `can return error state when getAlbums fails`() = runTest {
        // given
        val fetchAlbumsService = mockk<TopAlbumsApiService>()
        val fetchAlbumsFromApiTest = FetchAlbumsFromApi(fetchAlbumsService)
        val testSubject = TopAlbumsViewModel(fetchAlbumsFromApi = fetchAlbumsFromApiTest)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }

    @Test
    fun `can return error state when entry value is null`() = runTest {
        // given
        val feed = TestDatasource().getFeed().copy(album = listOf())
        val fetchAlbumsService = mockk<TopAlbumsApiService> {
            coEvery { getTopAlbums() } returns TopAlbums(feed = feed)
        }
        val fetchAlbumsFromApiTest = FetchAlbumsFromApi(fetchAlbumsService)
        val testSubject = TopAlbumsViewModel(fetchAlbumsFromApi = fetchAlbumsFromApiTest)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }
}