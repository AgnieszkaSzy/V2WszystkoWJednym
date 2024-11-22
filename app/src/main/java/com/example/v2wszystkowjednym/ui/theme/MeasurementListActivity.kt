package com.example.v2wszystkowjednym.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.v2wszystkowjednym.DatabaseHelper
import androidx.compose.foundation.lazy.items


@Composable
fun MeasurementListActivity(navController: NavHostController, dbHelper: DatabaseHelper) {
    val measurements = dbHelper.getAllMeasurements()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Your Measurements", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        if (measurements.isEmpty()) {
            Text("No measurements found.")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(measurements) { measurement ->
                    Text(
                        text = "Date: ${measurement.date}, Height: ${measurement.height} cm, Weight: ${measurement.weight} kg",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}