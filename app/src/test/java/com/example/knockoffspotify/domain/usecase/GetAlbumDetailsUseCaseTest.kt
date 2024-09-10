package com.example.knockoffspotify.domain.usecase

import app.cash.turbine.test
import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.domain.model.Song
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class GetAlbumDetailsUseCaseTest {

    private val mockRepository = mockk<AlbumsRepository>()

    private val testSubject = GetAlbumDetailsUseCase(mockRepository)

    @Test
    fun `can get album details from api`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getAlbumDetails("123") } returns album
        }

        // when
        val result = testSubject("123")

        // then
        result.toList().apply {
            assertEquals(Result.Loading, this[0])
            assertEquals(Result.Success(album), this[1])
        }
    }

    @Test
    fun `can emit Error when getAlbumDetails returns null`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getAlbumDetails("123") } returns null
        }

        // when
        val result = testSubject("123")

        // then
        result.test {
            assertEquals(Result.Loading, awaitItem())
            val errorResult = awaitItem()
            assertTrue(errorResult is Result.Error)
            assertEquals("Album details is empty", (errorResult as Result.Error).exception.message)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can emit Error when IOException thrown`() = runTest {
        // given
        val exception = IOException("Network Error occurred")
        mockRepository.apply {
            coEvery { getAlbumDetails("123") } throws exception
        }

        // when
        val result = testSubject("123")

        // then
        result.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(exception), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can emit Error when Exception thrown`() = runTest {
        // given
        val exception = Exception("Unknown Error occurred")
        mockRepository.apply {
            coEvery { getAlbumDetails("123") } throws exception
        }

        // when
        val result = testSubject("123")

        // then
        result.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Error(exception), awaitItem())
            cancelAndIgnoreRemainingEvents()
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

        private val album = Album(
            albumName = "Test Album",
            artistName = "Test Artist",
            genre = "Test Genre",
            explicitness = "explicit",
            artwork = "https://example.com/image.jpg",
            songs = listOf(song1)
        )
    }
}