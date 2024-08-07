package com.example.v2wszystkowjednym

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class StartScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun isWelcomeTextVisible() {
        composeTestRule.setContent {
            StartScreen()
        }

        composeTestRule.onNodeWithText(context.getString(R.string.welcome_text)).assertIsDisplayed()
    }

    @Test
    fun isChoiceTextVisible() {
        composeTestRule.setContent {
            StartScreen()
        }

        composeTestRule.onNodeWithText(context.getString(R.string.choice_text)).assertIsDisplayed()
    }

    @Test
    fun isBmiButtonEnable() {
        composeTestRule.setContent {
            StartScreen()
        }
        composeTestRule.onNodeWithText(context.getString(R.string.bmi_button)).assertIsEnabled()
    }

    @Test
    fun isMeasurementButtonEnable() {
        composeTestRule.setContent {
            StartScreen()
        }
        composeTestRule.onNodeWithText(context.getString(R.string.measurement_button)).assertIsEnabled()
    }
}