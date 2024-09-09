package com.example.knockoffspotify.ui.albums.top_albums

import app.cash.turbine.test
import com.example.knockoffspotify.data.TestDatasource
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.domain.usecase.GetTopAlbumsUseCase
import com.example.knockoffspotify.helpers.MainCoroutineRule
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class TopAlbumViewModelTest {

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    private val mockRepository = mockk<AlbumsRepository>()
    private val stubTopAlbums = TestDatasource.topAlbumsStubbed.feed
    private val getTopAlbumsUseCase = GetTopAlbumsUseCase(mockRepository)

    private val testSubject = TopAlbumsViewModel(getTopAlbumsUseCase)

    @Test
    fun `getAlbums can get albums`() = runTest {
        // given
        mockRepository.apply {
            coEvery { getTopAlbums() } returns stubTopAlbums.topAlbums
        }

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(entries = stubTopAlbums.topAlbums), awaitItem())
        }
    }

    @Test
    fun `can return error state when getAlbums fails`() = runTest {
        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }

    @Test
    fun `can return error state when entry value is null`() = runTest {
        // given
        val emptyTopAlbumsStub = stubTopAlbums.copy(topAlbums = listOf())
        mockRepository.apply {
            coEvery { getTopAlbums() } returns emptyTopAlbumsStub.topAlbums
        }

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }
}