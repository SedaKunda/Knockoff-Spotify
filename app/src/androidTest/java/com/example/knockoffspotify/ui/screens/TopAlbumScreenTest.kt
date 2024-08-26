package com.example.knockoffspotify.ui.screens

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import com.example.knockoffspotify.data.local.Datasource
import com.example.knockoffspotify.ui.BaseUiTest
import com.example.knockoffspotify.ui.top_albums.TopAlbumsScreen
import com.example.knockoffspotify.ui.top_albums.TopAlbumsViewModel
import com.example.knockoffspotify.utils.ViewState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class TopAlbumScreenTest : BaseUiTest() {

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
    fun checkErrorIsDisplayed() {
        val topAlbumsViewModel = mockk<TopAlbumsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Error
            )
        }
        composeTestRule.setContent {
            TopAlbumsScreen(topAlbumsViewModel) {}
        }
        composeTestRule.onNodeWithText("error").assertIsDisplayed()
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

        composeTestRule.onAllNodesWithTag("RowItem")
            .filter(hasClickAction())
            .apply {
                fetchSemanticsNodes().forEachIndexed { i, _ ->
                    get(i).performSemanticsAction(SemanticsActions.OnClick)
                }
            }
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

        composeTestRule.onNode(hasAnyChild(hasTestTag("ChangeViewIcon") and hasContentDescription("grid view")))
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("ChangeViewIcon").performClick()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardGrid"))).assertIsDisplayed()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardList"))).assertDoesNotExist()
        composeTestRule.onNodeWithTag("ChangeViewIcon").performClick()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardList"))).assertIsDisplayed()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardGrid"))).assertDoesNotExist()
    }

    @Test
    fun checkActivityRecreationSavesGridState() {
        val topAlbumsViewModel = mockk<TopAlbumsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = Datasource().loadEntries())
            )
        }
        val restorationTester = StateRestorationTester(composeTestRule)

        restorationTester.setContent { TopAlbumsScreen(topAlbumsViewModel) {} }

        composeTestRule.onNode(hasAnyChild(hasTestTag("ChangeViewIcon") and hasContentDescription("grid view")))
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("ChangeViewIcon").performClick()
        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardGrid"))).assertIsDisplayed()

        restorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNode(hasAnyChild(hasTestTag("AlbumCardGrid"))).assertIsDisplayed()
    }


}