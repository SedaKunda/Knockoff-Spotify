package com.example.knockoffspotify.ui.album_details

import app.cash.turbine.test
import com.example.knockoffspotify.data.model.Song
import com.example.knockoffspotify.data.services.FetchAlbumDetails
import com.example.knockoffspotify.domain.model.Album
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
import com.example.knockoffspotify.data.model.Album as ApiAlbum

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumDetailsViewModelTest {
    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Test
    fun `can get album details`() = runTest {
        val mockedFetchAlbumDetails = mockk<FetchAlbumDetails> {
            coEvery { fetchAlbumDetails("123") } returns flowOf(ViewState.Success(apiAlbum))
        }
        val testSubject = AlbumDetailsViewModel(mockedFetchAlbumDetails)

        testSubject.getAlbumDetails("123")

        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(album), awaitItem())
        }
    }

    @Test
    fun `can return error state when getAlbumDetails fails`() = runTest {
        val mockedFetchAlbumDetails = mockk<FetchAlbumDetails> {
            coEvery { fetchAlbumDetails("123") } returns flowOf(ViewState.Error)
        }
        val testSubject = AlbumDetailsViewModel(mockedFetchAlbumDetails)

        testSubject.getAlbumDetails("123")

        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }

    // all these mapping tests should move to a separate class
    @Test
    fun `can return error state when mapping fails`() = runTest {
        val mockedFetchAlbumDetails = mockk<FetchAlbumDetails> {
            coEvery { fetchAlbumDetails("123") } returns flowOf(
                ViewState.Success(emptyApiAlbum)
            )
        }
        val testSubject = AlbumDetailsViewModel(mockedFetchAlbumDetails)

        testSubject.getAlbumDetails("123")

        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }

    @Test
    fun `can return error state when list has no RecordType albums`() = runTest {
        val mockedFetchAlbumDetails = mockk<FetchAlbumDetails> {
            coEvery { fetchAlbumDetails("123") } returns flowOf(
                ViewState.Success(ApiAlbum(listOf(song2)))
            )
        }
        val testSubject = AlbumDetailsViewModel(mockedFetchAlbumDetails)

        testSubject.getAlbumDetails("123")

        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }

    companion object {

        private val song1 = Song(
            wrapperType = "Wrapper Type",
            kind = "Test Kind",
            artistName = "Test Artist",
            collectionName = "Test Album",
            artworkUrl100 = "https://example.com/image.jpg",
            artworkUrl60 = "https://example.com/image.jpg",
            artworkUrl30 = "https://example.com/image.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Track",
            collectionExplicitness = "explicit",
            collectionType = "Album",
        )
        private val song2 = Song(
            wrapperType = "Wrapper Type",
            kind = "Test Kind",
            artistName = "Test Artist",
            collectionName = "Test Album",
            artworkUrl100 = "https://example.com/image.jpg",
            artworkUrl60 = "https://example.com/image.jpg",
            artworkUrl30 = "https://example.com/image.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Track",
            collectionExplicitness = "explicit",
            collectionType = "song",
        )

        private val emptyApiAlbum = ApiAlbum(
            results = listOf()
        )
        private val apiAlbum = ApiAlbum(
            results = listOf(song1, song2)
        )
        private val album = Album(
            albumName = "Test Album",
            artistName = "Test Artist",
            genre = "Test Genre",
            explicitness = "explicit",
            artwork = "https://example.com/image.jpg",
            songs = listOf(song2)
        )
    }
}