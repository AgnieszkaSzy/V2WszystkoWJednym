package com.example.v2wszystkowjednym

import CalculateBmi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.v2wszystkowjednym.ui.theme.MeasurementListActivity

@Composable
fun AppNavHost(navController: NavHostController, dbHelper: DatabaseHelper) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartScreen(navController)}
        composable("bmi") { CalculateBmi(navController, dbHelper)}
        composable("measurement"){
            val dbHelper = DatabaseHelper(navController.context)
            MeasurementActivity(navController, dbHelper)}
        composable("measurements_list"){
            val dbHelper = DatabaseHelper(navController.context)
            MeasurementListActivity(navController, dbHelper)
        }
    }

}