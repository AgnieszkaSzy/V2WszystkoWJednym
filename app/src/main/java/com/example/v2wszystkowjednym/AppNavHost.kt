package com.example.v2wszystkowjednym

import BmiScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.v2wszystkowjednym.ui.theme.MeasurementListScreen

@Composable
fun AppNavHost(navController: NavHostController, dbHelper: DatabaseHelper) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartScreen(navController)}
        composable("bmi") { BmiScreen(navController, dbHelper)}
        composable("measurement"){ MeasurementScreen(navController, dbHelper)}
        composable("measurements_list"){ MeasurementListScreen(navController, dbHelper)
        }
    }

}