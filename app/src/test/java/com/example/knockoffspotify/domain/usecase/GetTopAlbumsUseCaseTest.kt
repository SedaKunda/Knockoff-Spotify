package com.example.knockoffspotify.domain.usecase

import app.cash.turbine.test
import com.example.knockoffspotify.data.TestDatasource.Companion.topAlbumsStubbed
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class GetTopAlbumsUseCaseTest {
    private val mockRepository = mockk<AlbumsRepository>()
    private val stubTopAlbums = topAlbumsStubbed.feed.topAlbums

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
    fun `can propagate exception when IOException thrown`() = runTest {
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
}