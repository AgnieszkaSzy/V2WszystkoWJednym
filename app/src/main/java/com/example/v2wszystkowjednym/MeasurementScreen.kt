package com.example.v2wszystkowjednym

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp

@Composable
fun MeasurementScreen(navController: NavHostController, dbHelper: DatabaseHelper){
    var date by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("MeasurementScreen"),
        verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Enter your data:", fontSize = 26.sp)

        TextField(
            value = date,
            onValueChange = { date = it},
            label = {Text(text = "Date of measurement: ")},
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = height,
            onValueChange = { height = it},
            label = {Text(text = "Your height: ")},
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = weight,
            onValueChange = { weight = it},
            label = {Text(text = "Your weight: ")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            if (date.isNotEmpty() && height.isNotEmpty() && weight.isNotEmpty()) {
                dbHelper?.insertMeasurement(date, height.toFloat(), weight.toFloat())
            }
        }) {
            Text("Save")
        }
    }
}