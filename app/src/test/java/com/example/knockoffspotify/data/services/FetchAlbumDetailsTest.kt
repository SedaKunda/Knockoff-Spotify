package com.example.knockoffspotify.data.services

import com.example.knockoffspotify.data.TestDatasource
import com.example.knockoffspotify.data.model.Album
import com.example.knockoffspotify.data.model.Song
import com.example.knockoffspotify.data.model.TopAlbums
import com.example.knockoffspotify.data.remote.AlbumsApiService
import com.example.knockoffspotify.utils.ViewState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchAlbumDetailsTest {
    // use stubbing instead of mocking for this test
    private val stubbedAlbumsApiService = object : AlbumsApiService {
        override suspend fun getAlbumDetails(id: String, entity: String): Album {
            return Album(results = listOf(song))
        }

        override suspend fun getTopAlbums(): TopAlbums {
            return TopAlbums(feed = TestDatasource().getFeed()
            )
        }
    }

    private val stubbedFailedAlbumsApiService = object : AlbumsApiService {
        override suspend fun getAlbumDetails(id: String, entity: String): Album {
            throw Exception("Network Error occurred")
        }

        override suspend fun getTopAlbums(): TopAlbums {
            return TopAlbums(feed = TestDatasource().getFeed()
            )
        }
    }

    @Test
    fun `can get album details from api`() = runTest {
        // given
        val fetchAlbumDetails = FetchAlbumDetails(stubbedAlbumsApiService)

        // when
        val testSubject = fetchAlbumDetails.fetchAlbumDetails("123").toList()

        // then
        assertEquals(ViewState.Loading, testSubject[0])
        assertEquals(ViewState.Success(Album(listOf( song))), testSubject[1])
    }

    @Test
    fun `can propagate exception when IOException thrown`() = runTest {
        val fetchAlbumDetails = FetchAlbumDetails(stubbedFailedAlbumsApiService)

        val testSubject = fetchAlbumDetails.fetchAlbumDetails("123").toList()

        assertEquals(ViewState.Loading, testSubject[0])
        assertEquals(ViewState.Error, testSubject[1])
    }

    companion object {
        private val song = Song(
            wrapperType = "Test Wrapper Type",
            kind = "Test Kind",
            artistName = "Test Artist",
            collectionName = "Test Album",
            artworkUrl100 = "https://example.com/image.jpg",
            artworkUrl60 = "https://example.com/image.jpg",
            artworkUrl30 = "https://example.com/image.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Track",
            collectionExplicitness = "explicit",
            collectionType = "Test Collection Type",
        )
    }
}