package com.example.knockoffspotify

import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FlowIntegrationTest {
    init {
        AccessibilityChecks.enable()
    }
}