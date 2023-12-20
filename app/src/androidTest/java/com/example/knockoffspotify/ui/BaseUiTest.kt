package com.example.knockoffspotify.ui

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

abstract class BaseUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()
}