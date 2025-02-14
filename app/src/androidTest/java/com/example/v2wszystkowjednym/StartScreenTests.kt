package com.example.v2wszystkowjednym

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StartScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController
    val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        navController.navigatorProvider.addNavigator(ComposeNavigator())

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = "start") {
                composable("start") {
                    StartScreen(navController = navController)
                }
            }
        }
    }

            @Test
            fun isWelcomeTextVisible() {
                composeTestRule.onNodeWithText(context.getString(R.string.welcome_text))
                    .assertIsDisplayed()
            }

            @Test
            fun isChoiceTextVisible() {
                composeTestRule.onNodeWithText(context.getString(R.string.choice_text))
                    .assertIsDisplayed()
            }

            @Test
            fun isBmiButtonEnable() {
                composeTestRule.onNodeWithText(context.getString(R.string.bmi_button))
                    .assertIsEnabled()
            }

            @Test
            fun isMeasurementButtonEnable() {
                composeTestRule.onNodeWithText(context.getString(R.string.measurement_button))
                    .assertIsEnabled()
            }
        }


