package com.example.v2wszystkowjednym

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun MeasurementScreen(navController: NavHostController, dbHelper: DatabaseHelper) {
    var date by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background1),
            contentDescription = "blue background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag("MeasurementScreen")
        ) {
            IconButton(onClick = { navController.navigate("start") }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back arrow",
                    modifier = Modifier
                        .size(30.dp)
                        .offset(6.dp, 6.dp)
                        .testTag("BackArrow")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.enter_data_text), fontSize = 26.sp,
                modifier = Modifier
                    .offset(16.dp)
                    .testTag("EnterDataText")
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = date,
                onValueChange = { date = it },
                label = { Text(text = stringResource(R.string.measurement_date)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(16.dp)
                    .testTag("MeasurementDate")
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = height,
                onValueChange = { height = it },
                label = { Text(text = stringResource(R.string.height_field)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(16.dp)
                    .testTag("HeightField")
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text(text = stringResource(R.string.weight_field)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(16.dp)
                    .testTag("WeightField")
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (date.isNotEmpty() && height.isNotEmpty() && weight.isNotEmpty()) {
                        dbHelper?.insertMeasurement(date, height.toFloat(), weight.toFloat())
                    }
                },
                modifier = Modifier
                    .offset(16.dp)
                    .testTag("SaveButton")
            ) {
                Text("Save")
            }
        }
    }
}

data class Measurement(val date: String, val height: Float, val weight: Float)