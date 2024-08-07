package com.example.knockoffspotify.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTextExactly
import com.example.knockoffspotify.ui.BaseUiTest

class HomeAppBarTest: BaseUiTest() {

    fun checkIconsAreDisplayed() {
        composeTestRule.setContent {
//            TopAlbumsScreen()
        }
        composeTestRule.onNode(hasParent(hasTextExactly("Top 100 Albums"))).assertIsDisplayed()
    }
}