package com.example.knockoffspotify.ui.albums.album_details

import app.cash.turbine.test
import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.domain.model.Song
import com.example.knockoffspotify.domain.usecase.GetAlbumDetailsUseCase
import com.example.knockoffspotify.helpers.MainCoroutineRule
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumDetailsViewModelTest {
    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    private val getAlbumDetailsUseCaseMock = mockk<GetAlbumDetailsUseCase>()

    private val testSubject = AlbumDetailsViewModel(getAlbumDetailsUseCaseMock)

    @Test
    fun `can get album details`() = runTest {
        // given
        coEvery { getAlbumDetailsUseCaseMock("123") } returns flowOf(ViewState.Success(album))

        // when
        testSubject.getAlbumDetails("123")

        // then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(album), awaitItem())
        }
    }

    @Test
    fun `can return error state when getAlbumDetails returns Error`() = runTest {
        // given
        coEvery { getAlbumDetailsUseCaseMock("123") } returns flowOf(ViewState.Error)

        // when
        testSubject.getAlbumDetails("123")

        // then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }

    companion object {
        private val song1 = Song(
            artistName = "Test Artist",
            artwork = "https://example.com/image.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Track",
            collectionExplicitness = "explicit",
        )
        private val song2 = Song(
            artistName = "Test Artist",
            artwork = "https://example.com/image.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Track",
            collectionExplicitness = "explicit",
        )
        private val album = Album(
            albumName = "Test Album",
            artistName = "Test Artist",
            genre = "Test Genre",
            explicitness = "explicit",
            artwork = "https://example.com/image.jpg",
            songs = listOf(song1, song2)
        )
    }
}