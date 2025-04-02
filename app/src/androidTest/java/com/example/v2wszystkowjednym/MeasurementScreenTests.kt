package com.example.v2wszystkowjednym

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MeasurementScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        navController.navigatorProvider.addNavigator(ComposeNavigator())

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = "measurement") {
                composable("measurement") { MeasurementScreen(navController = navController, dbHelper = DatabaseHelper(navController.context)) }
            }
        }
    }

    @Test
    fun isMeasurementScreenVisible() {
        composeTestRule.onNodeWithTag("MeasurementScreen").assertIsDisplayed()
    }

    @Test
    fun isEnterDataTextVisible() {
        composeTestRule.onNodeWithTag("EnterDataText").assertIsDisplayed()
    }

    @Test
    fun isMeasurementDateTextVisible() {
        composeTestRule.onNodeWithTag("MeasurementDate").assertIsDisplayed()
    }

    @Test
    fun isHeightFieldVisible() {
        composeTestRule.onNodeWithTag("HeightField").assertIsDisplayed()
    }

    @Test
    fun isWeightFieldVisible() {
        composeTestRule.onNodeWithTag("WeightField").assertIsDisplayed()
    }

    @Test
    fun isSaveButtonVisible() {
        composeTestRule.onNodeWithTag("SaveButton").assertIsDisplayed()
    }

    @Test
    fun shouldDisplayEnteredValueInDateField() {
        composeTestRule.onNodeWithTag("MeasurementDate").performTextInput("14.03.2025")
        composeTestRule.onNodeWithTag("MeasurementDate").assertTextContains("14.03.2025")
    }

    @Test
    fun shouldDisplayEnteredValueInHeightField() {
        composeTestRule.onNodeWithTag("HeightField").performTextInput("170")
        composeTestRule.onNodeWithTag("HeightField").assertTextContains("170")
    }

    @Test
    fun shouldDisplayEnteredValueInWeightField() {
        composeTestRule.onNodeWithTag("WeightField").performTextInput("60")
        composeTestRule.onNodeWithTag("WeightField").assertTextContains("60")
    }

    @Test
    fun shouldShowErrorWhenEmptyField() {
        composeTestRule.onNodeWithTag("SaveButton").performClick()
        composeTestRule.onNodeWithTag("EmptyFieldErrorText").assertIsDisplayed()
    }

    @Test
    fun shouldClearFieldsWhenSavedDataCorrectly() {
        composeTestRule.onNodeWithTag("MeasurementDate").performTextInput("14.03.2025")
        composeTestRule.onNodeWithTag("HeightField").performTextInput("170")
        composeTestRule.onNodeWithTag("WeightField").performTextInput("60")
        composeTestRule.onNodeWithTag("SaveButton").performClick()
        composeTestRule.onNodeWithTag("MeasurementDate").assertTextContains("")
        composeTestRule.onNodeWithTag("HeightField").assertTextContains("")
        composeTestRule.onNodeWithTag("WeightField").assertTextContains("")
        composeTestRule.onNodeWithTag("EmptyFieldErrorText").assertIsNotDisplayed()
    }
}