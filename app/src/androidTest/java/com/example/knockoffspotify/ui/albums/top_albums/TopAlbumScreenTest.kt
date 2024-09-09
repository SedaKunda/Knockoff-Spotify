package com.example.knockoffspotify.ui.albums.top_albums

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
import com.example.knockoffspotify.domain.model.TopAlbum
import com.example.knockoffspotify.ui.BaseUiTest
import com.example.knockoffspotify.utils.ViewState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class TopAlbumScreenTest : BaseUiTest() {

    private val stubDatasourceEntries = listOf(topAlbum, topAlbum, topAlbum)

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
                ViewState.Success(entries = stubDatasourceEntries)
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
        composeTestRule.onNodeWithText("Failed to load albums").assertIsDisplayed()
    }

    @Test
    fun checkListIsScrollable() {
        val topAlbumsViewModel = mockk<TopAlbumsViewModel>(relaxUnitFun = true) {
            every { state } returns MutableStateFlow(
                ViewState.Success(entries = stubDatasourceEntries)
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
                ViewState.Success(entries = stubDatasourceEntries)
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
                ViewState.Success(entries = stubDatasourceEntries)
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
                ViewState.Success(entries = stubDatasourceEntries)
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

    companion object {
        private val topAlbum = TopAlbum(
            name = "Test Album",
            artist = "Test Artist",
            releaseDate = "2023-10-20T00:00:00-07:00",
            imageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/8e/35/6c/8e356cc2-0be4-b83b-d29e-b578623df2ac/23UM1IM34052.rgb.jpg/55x55bb.png",
            id = "1713845538",
        )
    }
}