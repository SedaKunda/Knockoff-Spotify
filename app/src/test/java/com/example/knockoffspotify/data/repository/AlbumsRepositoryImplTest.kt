package com.example.knockoffspotify.data.repository

import com.example.knockoffspotify.data.api.AlbumsApiService
import com.example.knockoffspotify.data.model.Album
import com.example.knockoffspotify.data.model.AlbumImage
import com.example.knockoffspotify.data.model.AlbumName
import com.example.knockoffspotify.data.model.Artist
import com.example.knockoffspotify.data.model.Attributes
import com.example.knockoffspotify.data.model.Category
import com.example.knockoffspotify.data.model.CategoryAttributes
import com.example.knockoffspotify.data.model.Feed
import com.example.knockoffspotify.data.model.Id
import com.example.knockoffspotify.data.model.Id2
import com.example.knockoffspotify.data.model.IdAttributes
import com.example.knockoffspotify.data.model.ReleaseDate
import com.example.knockoffspotify.data.model.Song
import com.example.knockoffspotify.data.model.TopAlbums
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.example.knockoffspotify.data.model.TopAlbum as TopAlbumApi


class AlbumsRepositoryImplTest {

    private val albumsApiServiceMock: AlbumsApiService = mockk(relaxed = true)
    private val id = "123"

    private val testSubject = AlbumsRepositoryImpl(albumsApiServiceMock)

    @Test
    fun `getTopAlbums should call getTopAlbums from api service`() = runTest {
        // when
        testSubject.getTopAlbums()

        // then
        coVerify { albumsApiServiceMock.getTopAlbums() }
    }

    @Test
    fun `getTopAlbums can map the response to domain model`() = runTest {
        // given
        coEvery { albumsApiServiceMock.getTopAlbums() } returns TopAlbums(
            feed = Feed(
                topAlbums = listOf(topAlbum1, topAlbum2),
                id = Id2("123")
            )
        )

        // when
        val result = testSubject.getTopAlbums()

        // then
        assertEquals(2, result.size)
        assertEquals("1989 (Taylor's Version) [Deluxe]", result[0].name)
        assertEquals("Taylor Swift", result[0].artist)
        assertEquals("20.10.2023", result[0].releaseDate)
        assertEquals("https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/170x170bb.png", result[0].imageUrl)
        assertEquals("1713845538", result[0].id)
    }

    @Test
    fun `getAlbumDetails should call getAlbumDetails from api service`() = runTest {
        // when
        testSubject.getAlbumDetails(id)

        // then
        coVerify { albumsApiServiceMock.getAlbumDetails(id) }
    }

    @Test
    fun `getAlbumDetails can map the response to domain model`() = runTest {
        // given
        coEvery { albumsApiServiceMock.getAlbumDetails(id) } returns Album(
            results = listOf(song1Stubbed, song2Stubbed)
        )

        // when
        val result = testSubject.getAlbumDetails(id)

        // then
        assertEquals("Album", result?.albumName)
        assertEquals("Album Test Artist", result?.artistName)
        assertEquals("Test Genre", result?.genre)
        assertEquals("Album Test Explicitness", result?.explicitness)
        assertEquals("https://example.com/image1.jpg", result?.artwork)
        assertEquals(1, result?.songs?.size)
        assertEquals("Song Test Track", result?.songs?.get(0)?.trackName)
    }

    @Test
    fun `getAlbumDetails can return null when list contains no collectionType albums`() = runTest {
        // given
        coEvery { albumsApiServiceMock.getAlbumDetails(id) } returns Album(
            results = listOf(song2Stubbed)
        )

        // when
        val result = testSubject.getAlbumDetails(id)

        // then
        assertEquals(null, result)
    }

    @Test
    fun `getAlbumDetails can return first album when list contains multiple collectionType albums`() =
        runTest {
            // given
            coEvery { albumsApiServiceMock.getAlbumDetails(id) } returns Album(
                results = listOf(
                    song1Stubbed,
                    song2Stubbed,
                    song1Stubbed.copy(artistName = "Another Artist")
                )
            )

            // when
            val result = testSubject.getAlbumDetails(id)

            // then
            assertEquals("Album", result?.albumName)
            assertEquals("Album Test Artist", result?.artistName)
            assertEquals("Test Genre", result?.genre)
            assertEquals("Album Test Explicitness", result?.explicitness)
            assertEquals("https://example.com/image1.jpg", result?.artwork)
            assertEquals(1, result?.songs?.size)
            assertEquals("Song Test Track", result?.songs?.get(0)?.trackName)
        }

    @Test
    fun `getAlbumDetails can return second image when first image is null`() = runTest {
        // given
        coEvery { albumsApiServiceMock.getAlbumDetails(id) } returns Album(
            results = listOf(
                song1Stubbed.copy(artworkUrl100 = null),
                song2Stubbed
            )
        )

        // when
        val result = testSubject.getAlbumDetails(id)

        // then
        assertEquals("https://example.com/image2.jpg", result?.artwork)
    }

    @Test
    fun `getAlbumDetails can return third image when first and second image is null`() = runTest {
        // given
        coEvery { albumsApiServiceMock.getAlbumDetails(id) } returns Album(
            results = listOf(
                song1Stubbed.copy(artworkUrl100 = null, artworkUrl60 = null),
                song2Stubbed
            )
        )

        // when
        val result = testSubject.getAlbumDetails(id)

        // then
        assertEquals("https://example.com/image3.jpg", result?.artwork)
    }

    companion object {
        private val song1Stubbed = Song(
            wrapperType = "Album Test Wrapper Type",
            kind = "Album Test Kind",
            artistName = "Album Test Artist",
            collectionName = "Album",
            artworkUrl100 = "https://example.com/image1.jpg",
            artworkUrl60 = "https://example.com/image2.jpg",
            artworkUrl30 = "https://example.com/image3.jpg",
            primaryGenreName = "Test Genre",
            trackName = "Test Track",
            collectionExplicitness = "Album Test Explicitness",
            collectionType = "Album",
        )
        private val song2Stubbed = Song(
            wrapperType = "Song Test Wrapper Type",
            kind = "Song Test Kind",
            artistName = "Song Test Artist",
            collectionName = "Song",
            artworkUrl100 = "https://example.com/image4.jpg",
            artworkUrl60 = "https://example.com/image5.jpg",
            artworkUrl30 = "https://example.com/image6.jpg",
            primaryGenreName = "Song Test Genre",
            trackName = "Song Test Track",
            collectionExplicitness = "explicit",
            collectionType = "song",
        )

        private val topAlbum1 = com.example.knockoffspotify.data.model.TopAlbum(
            name = AlbumName("1989 (Taylor's Version) [Deluxe]"),
            artist = Artist("Taylor Swift"),
            releaseDate = ReleaseDate("2023-10-20T00:00:00-07:00"),
            image = listOf(
                AlbumImage(
                    label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/55x55bb.png",
                    attributes = Attributes(height = "55")
                ),
                AlbumImage(
                    label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/60x60bb.png",
                    attributes = Attributes(height = "60")
                ),
                AlbumImage(
                    label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/170x170bb.png",
                    attributes = Attributes(height = "170")
                )
            ),
            id = Id(IdAttributes("1713845538")),
            category = Category(CategoryAttributes("", ""))
        )

        private val topAlbum2 = TopAlbumApi(
            name = AlbumName("Red (Taylor's Version) [Deluxe]"),
            artist = Artist("Taylor Swift"),
            releaseDate = ReleaseDate("2023-10-20T00:00:00-07:00"),
            image = listOf(
                AlbumImage(
                    label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/55x55bb.png",
                    attributes = Attributes(height = "55")
                ),
                AlbumImage(
                    label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/60x60bb.png",
                    attributes = Attributes(height = "60")
                ),
                AlbumImage(
                    label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/170x170bb.png",
                    attributes = Attributes(height = "170")
                )
            ),
            id = Id(IdAttributes("1713845538")),
            category = Category(CategoryAttributes("", "")),
        )

    }
}