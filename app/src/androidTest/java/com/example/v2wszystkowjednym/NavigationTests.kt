package com.example.v2wszystkowjednym

import BmiScreen
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        navController.navigatorProvider.addNavigator(ComposeNavigator())

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = "start") {
                composable("start") { StartScreen(navController = navController) }
                composable("bmi") {BmiScreen(navController = navController, dbHelper = DatabaseHelper(navController.context))}
                composable("measurement") {MeasurementScreen(navController = navController, dbHelper = DatabaseHelper(navController.context))}
                composable("measurement_list") {MeasurementScreen(navController = navController, dbHelper = DatabaseHelper(navController.context))}
            }
        }
    }

    @Test
    fun BmiButtonOpenBmiScreen() {
        composeTestRule.onNodeWithTag("CalculateBmiNavigationButton").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("BmiScreen").assertIsDisplayed()
    }

    @Test
    fun MeasurementButtonOpenMeasurementScreen() {
        composeTestRule.onNodeWithTag("SaveMeasurementsButton").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("MeasurementScreen").assertIsDisplayed()
    }

    @Test
    fun MeasurementsListButtonOpenMeasurementListScreen() {
        composeTestRule.onNodeWithTag("MeasurementListButton").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("MeasurementListScreen").assertIsDisplayed()
    }

    @Test
    fun ArrowBackInBmiScreenNavigateToMainScreen() {
        composeTestRule.onNodeWithTag("CalculateBmiNavigationButton").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("BackArrow",useUnmergedTree = true).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("StartScreen").assertIsDisplayed()
    }

    @Test
    fun ArrowBackInMeasurementScreenNavigateToMainScreen() {
        composeTestRule.onNodeWithTag("SaveMeasurementsButton").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("BackArrow",useUnmergedTree = true).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("StartScreen").assertIsDisplayed()
    }

    @Test
    fun ArrowBackInMeasurementListScreenNavigateToMainScreen() {
        composeTestRule.onNodeWithTag("MeasurementListButton").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("BackArrow",useUnmergedTree = true).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("StartScreen").assertIsDisplayed()
    }
}