package net.composing.compose.sample.simpleui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class SimpleUIKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verify_initial_case() {
        composeTestRule.setContent {
            SimpleUI()
        }

        composeTestRule.onNodeWithTag("clicks").assertDoesNotExist()
    }

    @Test
    fun verify_last_case() {
        composeTestRule.setContent {
            SimpleUI()
        }

        composeTestRule.onNodeWithText("Click me".uppercase()).performClick()

        with(composeTestRule.onNodeWithTag("clicks")) {
            assertIsDisplayed()
            assertTextEquals("1")
        }
    }
}