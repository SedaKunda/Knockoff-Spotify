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
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class TopAlbumViewModelTest {

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    private val stubTopAlbums = listOf(topAlbum)
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

    companion object {
        private val topAlbum = TopAlbum(
            name = "Test Album",
            artist = "Test Artist",
            releaseDate = "2023-10-20T00:00:00-07:00",
            imageUrl = "https://example.com/image.jpg",
            id = "1713845538",
        )
    }
}