package com.example.knockoffspotify.AllAbums

import app.cash.turbine.test
import com.example.knockoffspotify.MainCoroutineRule
import com.example.knockoffspotify.data.TestDatasource
import com.example.knockoffspotify.data.remote.FeedApiService
import com.example.knockoffspotify.model.TopAlbums
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class AllAlbumsViewModelTest {

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Test
    fun `getAlbums can get albums`() = testCoroutineRule.runTest {
        // given
        val feed = TestDatasource().getFeed()
        val fetchAlbumsService = mockk<FeedApiService> {
            coEvery { getTopAlbums() } returns TopAlbums(feed = feed)
        }
        val fetchAlbumsFromApiTest = FetchAlbumsFromApi(fetchAlbumsService)
        val testSubject = AllAlbumsViewModel(fetchAlbumsFromApi = fetchAlbumsFromApiTest)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(entries = feed.entry), awaitItem())
        }
    }

    @Test
    fun `can return error state when getAlbums fails`() = testCoroutineRule.runTest {
        // given
        val fetchAlbumsService = mockk<FeedApiService>()
        val fetchAlbumsFromApiTest = FetchAlbumsFromApi(fetchAlbumsService)
        val testSubject = AllAlbumsViewModel(fetchAlbumsFromApi = fetchAlbumsFromApiTest)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }
}