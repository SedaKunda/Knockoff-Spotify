package com.example.knockoffspotify.domain.usecase

import app.cash.turbine.test
import com.example.knockoffspotify.domain.model.TopAlbum
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class GetTopAlbumsUseCaseTest {
    private val mockRepository = mockk<AlbumsRepository>()
    private val stubTopAlbums = listOf(topAlbum)

    private val testSubject = GetTopAlbumsUseCase(mockRepository)

    @Test
    fun `can get albums from api`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getTopAlbums() } returns stubTopAlbums
        }

        // when
        val result = testSubject()

        // then
        result.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(stubTopAlbums), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can propagate error when IOException thrown`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getTopAlbums() } throws IOException("Network Error occurred")
        }

        // when
        val result = testSubject()

        // then
        result.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can propagate error when HttpException thrown`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getTopAlbums() } throws HttpException(Response.error<Any>(404, "".toResponseBody()))
        }

        // when
        val result = testSubject()

        // then
        result.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can propagate error when Exception thrown`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getTopAlbums() } throws Exception("Unknown Error occurred")
        }

        // when
        val result = testSubject()

        // then
        result.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
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