package com.example.v2wszystkowjednym

import BmiScreen
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BmiScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        navController.navigatorProvider.addNavigator(ComposeNavigator())

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = "bmi") {
                composable("bmi") {BmiScreen(navController = navController, dbHelper = DatabaseHelper(navController.context))}
            }
        }
    }

    @Test
    fun isBmiScreenVisible(){
        composeTestRule.onNodeWithTag("BmiScreen").assertIsDisplayed()
        Thread.sleep(3000)
    }

    @Test
    fun isBmiHeaderVisible(){
        composeTestRule.onNodeWithTag("BmiCalculatorText").assertIsDisplayed()
    }

    @Test
    fun isGenderTextVisible(){
        composeTestRule.onNodeWithTag("GenderText").assertIsDisplayed()
    }

    @Test
    fun isGenderGroupVisible(){
        composeTestRule.onNodeWithTag("GenderGroup").assertIsDisplayed()
        composeTestRule.onNodeWithTag("FemaleButtonText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("FemaleButtonText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("MaleButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("MaleButtonText").assertIsDisplayed()
    }

    @Test
    fun isFemaleButtonCheck(){
        composeTestRule.onNodeWithTag("GenderGroup").assertIsDisplayed()
        composeTestRule.onNodeWithTag("FemaleButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("MaleButton").assertIsDisplayed()
    }

    @Test
    fun shouldSelectFemaleButtonWhenClicked(){
        composeTestRule.onNodeWithTag("FemaleButton").performClick()
        composeTestRule.onNodeWithTag("FemaleButton").assertIsSelected()
    }

    @Test
    fun shouldSelectMaleButtonWhenClicked(){
        composeTestRule.onNodeWithTag("MaleButton").performClick()
        composeTestRule.onNodeWithTag("MaleButton").assertIsSelected()
    }

    @Test
    fun isWeightTextVisible(){
        composeTestRule.onNodeWithTag("WeightText").assertIsDisplayed()
    }

    @Test
    fun shouldDisplayEnteredValueInWeightField(){
        composeTestRule.onNodeWithTag("WeightInput").performTextInput("50")
        composeTestRule.onNodeWithTag("WeightInput").assertTextEquals("50")
    }

    @Test
    fun isHeightTextVisible(){
        composeTestRule.onNodeWithTag("HeightText").assertIsDisplayed()
    }

    @Test
    fun shouldDisplayEnteredValueInHeightField(){
        composeTestRule.onNodeWithTag("HeightInput").performTextInput("160")
        composeTestRule.onNodeWithTag("HeightInput").assertTextEquals("160")
    }

    @Test
    fun isCalculateBmiButtonVisible(){
        composeTestRule.onNodeWithTag("CalculateButton").assertIsDisplayed()
    }

    @Test
    fun isClearDataButtonVisible(){
        composeTestRule.onNodeWithTag("ClearDataButton").assertIsDisplayed()
    }

    @Test
    fun shouldCalculateBmiAndDisplayResult(){
        composeTestRule.onNodeWithTag("FemaleButton").performClick()
        composeTestRule.onNodeWithTag("WeightInput").performTextInput("50")
        composeTestRule.onNodeWithTag("HeightInput").performTextInput("160")
        composeTestRule.onNodeWithTag("CalculateButton").performClick()
        composeTestRule.onNodeWithTag("BmiResult").assertTextEquals("Twoje BMI to: 19.53")
    }

    @Test
    fun shouldClearInputFieldsWhenClearButtonClicked(){
        composeTestRule.onNodeWithTag("FemaleButton").performClick()
        composeTestRule.onNodeWithTag("WeightInput").performTextInput("50")
        composeTestRule.onNodeWithTag("HeightInput").performTextInput("160")
        composeTestRule.onNodeWithTag("CalculateButton").performClick()
        composeTestRule.onNodeWithTag("ClearDataButton").performClick()
        composeTestRule.onNodeWithTag("FemaleButton").assertIsNotSelected()
        composeTestRule.onNodeWithTag("MaleButton").assertIsNotSelected()
        composeTestRule.onNodeWithTag("WeightInput").assertTextEquals("")
        composeTestRule.onNodeWithTag("HeightInput").assertTextEquals("")
        composeTestRule.onNodeWithTag("BmiResult").assertIsNotDisplayed()
    }

    @Test
    fun shouldShowErrorWhenNotAllFieldsAreFilled(){
        composeTestRule.onNodeWithTag("CalculateButton").performClick()
        composeTestRule.onNodeWithTag("ErrorText").assertIsDisplayed()
    }









}