package com.example.knockoffspotify.domain.usecase

import app.cash.turbine.test
import com.example.knockoffspotify.data.TestDatasource.Companion.songStubbed
import com.example.knockoffspotify.data.model.Album
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class GetAlbumDetailsUseCaseTest {

    private val mockRepository = mockk<AlbumsRepository>()
    private val stubSong = songStubbed

    private val testSubject = GetAlbumDetailsUseCase(mockRepository)

    @Test
    fun `can get album details from api`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getAlbumDetails("123") } returns Album(listOf(stubSong))
        }

        // when
        val result = testSubject("123")

        // then
        result.toList().apply {
            assertEquals(ViewState.Loading, this[0])
            assertEquals(ViewState.Success(Album(listOf(stubSong))), this[1])
        }
    }

    @Test
    fun `can propagate Error when IOException thrown`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getAlbumDetails("123") } throws IOException("Network Error occurred")
        }

        // when
        val result = testSubject("123")

        // then
        result.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `can propagate Error when Exception thrown`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getAlbumDetails("123") } throws Exception("Unknown Error occurred")
        }

        // when
        val result = testSubject("123")

        // then
        result.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}