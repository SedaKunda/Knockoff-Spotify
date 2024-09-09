package com.example.knockoffspotify.ui.albums.top_albums

import app.cash.turbine.test
import com.example.knockoffspotify.data.TestDatasource
import com.example.knockoffspotify.domain.usecase.GetTopAlbumsUseCase
import com.example.knockoffspotify.helpers.MainCoroutineRule
import com.example.knockoffspotify.utils.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class TopAlbumViewModelTest {

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    private val stubTopAlbums = TestDatasource.topAlbumsStubbed.feed
    private val getTopAlbumsUseCaseMock = mockk<GetTopAlbumsUseCase>()

    private val testSubject = TopAlbumsViewModel(getTopAlbumsUseCaseMock)

    @Test
    fun `can get albums`() = runTest {
        // given
        coEvery { getTopAlbumsUseCaseMock() } returns flowOf(ViewState.Success(stubTopAlbums.topAlbums))

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(entries = stubTopAlbums.topAlbums), awaitItem())
        }
    }

    @Test
    fun `can return error state when GetTopAlbumsUseCase returns Error`() = runTest {
        // given
        coEvery { getTopAlbumsUseCaseMock() } returns flowOf(ViewState.Error)

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }

    @Test
    fun `can return error state when top albums returns empty list`() = runTest {
        // given
        val emptyTopAlbumsStub = stubTopAlbums.copy(topAlbums = listOf())
        coEvery { getTopAlbumsUseCaseMock() } returns flowOf(ViewState.Success(emptyTopAlbumsStub.topAlbums))

        // when
        testSubject.getAlbums()

        //then
        testSubject.state.test {
            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Error, awaitItem())
        }
    }
}