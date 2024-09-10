package com.example.knockoffspotify.ui.albums.album_details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.domain.model.Song
import com.example.knockoffspotify.ui.BaseUiTest
import com.example.knockoffspotify.utils.ViewState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class AlbumDetailsScreenTest : BaseUiTest() {

    @Test
    fun checkTitleIsDisplayed() {
        val albumDetailsViewModel = mockk<AlbumDetailsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = stubAlbum)
            )
        }
        composeTestRule.setContent {
            AlbumDetailsScreen(albumId = "1", albumDetailsViewModel = albumDetailsViewModel) {}
        }
        composeTestRule.onNodeWithText("Album Details").assertIsDisplayed()
    }

    @Test
    fun checkAlbumCoverIsDisplayed() {
        val albumDetailsViewModel = mockk<AlbumDetailsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = stubAlbum)
            )
        }
        composeTestRule.setContent {
            AlbumDetailsScreen(albumId = "1", albumDetailsViewModel = albumDetailsViewModel) {}
        }
        composeTestRule.onNodeWithTag("AlbumCardHeader").assertExists()
    }

    @Test
    fun checkSongCardsAreDisplayed() {
        val albumDetailsViewModel = mockk<AlbumDetailsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = stubAlbum)
            )
        }
        composeTestRule.setContent {
            AlbumDetailsScreen(albumId = "1", albumDetailsViewModel = albumDetailsViewModel) {}
        }
        composeTestRule.onNodeWithText("Test Song 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Song 2").assertIsDisplayed()
    }

    @Test
    fun checkErrorIsDisplayed() {
        val albumDetailsViewModel = mockk<AlbumDetailsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(ViewState.Error)
        }
        composeTestRule.setContent {
            AlbumDetailsScreen(albumId = "1", albumDetailsViewModel = albumDetailsViewModel) {}
        }
        composeTestRule.onNodeWithText("Failed to load selected album").assertIsDisplayed()
    }

    companion object {
        private val song1 = Song(
            artistName = "Test Artist",
            artwork = "https://example.com/image.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Song 1",
            collectionExplicitness = "explicit",
        )
        private val song2 = Song(
            artistName = "Test Artist",
            artwork = "https://example.com/image.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Song 2",
            collectionExplicitness = "explicit",
        )
        private val stubAlbum = Album(
            albumName = "Test Album",
            artistName = "Test Artist",
            genre = "Test Genre",
            explicitness = "explicit",
            artwork = "https://example.com/image.jpg",
            songs = listOf(song1, song2)
        )
    }
}