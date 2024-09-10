package com.example.knockoffspotify.ui.albums.top_albums

import app.cash.turbine.test
import com.example.knockoffspotify.domain.model.TopAlbum
import com.example.knockoffspotify.domain.usecase.GetTopAlbumsUseCase
import com.example.knockoffspotify.helpers.MainCoroutineRule
import com.example.knockoffspotify.utils.Result
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class TopAlbumViewModelTest {

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    private val stubTopAlbums = listOf(topAlbum1)
    private val getTopAlbumsUseCaseMock = mockk<GetTopAlbumsUseCase>()

    @Test
    fun `can get albums`() = runTest {
        // given
        coEvery { getTopAlbumsUseCaseMock() } returns flowOf(Result.Success(stubTopAlbums))
        val testSubject = TopAlbumsViewModel(getTopAlbumsUseCaseMock)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(stubTopAlbums), awaitItem())
        }
    }

    @Test
    fun `can return error state when GetTopAlbumsUseCase returns Error`() = runTest {
        // given
        coEvery { getTopAlbumsUseCaseMock() } returns flowOf(Result.Error(Exception()))
        val testSubject = TopAlbumsViewModel(getTopAlbumsUseCaseMock)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }

    @Test
    fun `can return success state when top albums returns empty list`() = runTest {
        // given
        coEvery { getTopAlbumsUseCaseMock() } returns flowOf(Result.Success(listOf()))
        val testSubject = TopAlbumsViewModel(getTopAlbumsUseCaseMock)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(listOf<TopAlbum>()), awaitItem())
        }
    }

    @Test
    fun `onSearchQueryChanged updates search query`() = runTest {
        // given
        coEvery { getTopAlbumsUseCaseMock() } returns flowOf(Result.Success(listOf(topAlbum1)))
        val testSubject = TopAlbumsViewModel(getTopAlbumsUseCaseMock)

        // when
        testSubject.onSearchQueryChanged("Test Query")

        // then
        testSubject.searchQuery.test {
            assertEquals("Test Query", awaitItem())
        }
    }

    @Test
    fun `filterAlbums filters albums based on search query`() = runTest {
        // given
        val albums = listOf(topAlbum1, topAlbum2, topAlbum3)
        coEvery { getTopAlbumsUseCaseMock() } returns flowOf(Result.Success(albums))
        val testSubject = TopAlbumsViewModel(getTopAlbumsUseCaseMock)

        // when
        testSubject.onSearchQueryChanged("Test Album")

        // then
        testSubject.state.test {
            assertEquals(ViewState.Success(listOf<TopAlbum>()), awaitItem())
            val result = awaitItem() as ViewState.Success
            assertEquals(2, result.entries.size)
            assertTrue(result.entries.all { it.name.contains("Test Album") })
        }
    }

    companion object {
        private val topAlbum1 = TopAlbum(
            name = "Test Album",
            artist = "Test Artist",
            releaseDate = "2023-10-20T00:00:00-07:00",
            imageUrl = "https://example.com/image.jpg",
            id = "1713845538",
        )

        private val topAlbum2 = TopAlbum(
            name = "Test Album 2",
            artist = "Test Artist",
            releaseDate = "2023-10-20T00:00:00-07:00",
            imageUrl = "https://example.com/image2.jpg",
            id = "1713845539"
        )

        private val topAlbum3 = TopAlbum(
            name = "Another Album",
            artist = "Test Artist",
            releaseDate = "2023-10-20T00:00:00-07:00",
            imageUrl = "https://example.com/image3.jpg",
            id = "1713845540"
        )
    }
}