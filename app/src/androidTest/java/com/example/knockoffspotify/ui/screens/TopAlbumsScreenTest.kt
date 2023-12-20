package com.example.knockoffspotify.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.knockoffspotify.data.local.Datasource
import com.example.knockoffspotify.ui.BaseUiTest
import com.example.knockoffspotify.ui.top_albums.TopAlbumsScreen
import com.example.knockoffspotify.ui.top_albums.TopAlbumsViewModel
import com.example.knockoffspotify.utils.ViewState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class TopAlbumsScreenTest : BaseUiTest() {

    @Test
    fun checkTitleIsDisplayed() {
        val topAlbumsViewModel = mockk<TopAlbumsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                mockk<ViewState.Loading>()
            )
        }
        composeTestRule.setContent {
            TopAlbumsScreen(topAlbumsViewModel) {}
        }
        composeTestRule.onNode(hasText("Top 100 Albums")).assertIsDisplayed()
    }

    @Test
    fun checkListIsDisplayed() {
        val topAlbumsViewModel = mockk<TopAlbumsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = Datasource().loadEntries())
            )
        }
        composeTestRule.setContent {
            TopAlbumsScreen(topAlbumsViewModel) {}
        }
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardList"))).assertIsDisplayed()
    }

    @Test
    fun checkListIsScrollable() {
        val topAlbumsViewModel = mockk<TopAlbumsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = Datasource().loadEntries())
            )
        }
        composeTestRule.setContent {
            TopAlbumsScreen(topAlbumsViewModel) {}
        }
        composeTestRule.onNode(hasTestTag("TopAlbumsSurface") and hasAnyChild(hasScrollAction()))
            .assertExists()
    }

    @Test
    fun checkListItemIsClickable() {
        val topAlbumsViewModel = mockk<TopAlbumsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = Datasource().loadEntries())
            )
        }
        composeTestRule.setContent {
            TopAlbumsScreen(topAlbumsViewModel) {}
        }
        composeTestRule.onNode(hasTestTag("TopAlbumsSurface") and hasAnyChild(hasScrollAction()))
            .assertExists()
    }

    @Test
    fun checkSwitchToGridViewAndBackToListView() {
        val topAlbumsViewModel = mockk<TopAlbumsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = Datasource().loadEntries())
            )
        }
        composeTestRule.setContent {
            TopAlbumsScreen(topAlbumsViewModel) {}
        }

        composeTestRule.onNode(hasAnyChild(hasTestTag("ChangeViewIcon") and hasContentDescription("grid view"))).assertIsDisplayed()
        composeTestRule.onNodeWithTag("ChangeViewIcon").performClick()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardGrid"))).assertIsDisplayed()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardList"))).assertDoesNotExist()
        composeTestRule.onNodeWithTag("ChangeViewIcon").performClick()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardList"))).assertIsDisplayed()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardGrid"))).assertDoesNotExist()
    }


}