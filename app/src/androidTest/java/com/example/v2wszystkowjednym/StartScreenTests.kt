package com.example.v2wszystkowjednym

import BmiScreen
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StartScreenTests {

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
            }
        }
    }

    @Test
    fun isWelcomeTextVisible() {
        composeTestRule.onNodeWithTag("WelcomeText")
            .assertIsDisplayed()
    }

    @Test
    fun isChoiceTextVisible() {
        composeTestRule.onNodeWithTag("ChoiceInfoText")
            .assertIsDisplayed()
    }

    @Test
    fun isBmiButtonEnable() {
        composeTestRule.onNodeWithTag("CalculateBmiNavigationButton")
            .assertIsEnabled()
    }

    @Test
    fun isMeasurementButtonEnable() {
        composeTestRule.onNodeWithTag("SaveMeasurementsButton")
            .assertIsEnabled()
    }

    @Test
    fun isMeasurementsListButtonEnable() {
        composeTestRule.onNodeWithTag("MeasurementListButton")
            .assertIsEnabled()
    }
}


