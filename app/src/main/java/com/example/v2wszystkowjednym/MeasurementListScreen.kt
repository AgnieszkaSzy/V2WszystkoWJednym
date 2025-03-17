package com.example.v2wszystkowjednym.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.v2wszystkowjednym.DatabaseHelper
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.example.v2wszystkowjednym.R


@Composable
fun MeasurementListScreen(navController: NavHostController, dbHelper: DatabaseHelper) {
    val measurements = dbHelper.getAllMeasurements()

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
                .testTag("MeasurementListScreen")
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
                "Your Measurements",
                fontSize = 24.sp,
                modifier = Modifier
                    .offset(16.dp),
                color = Color(0xFF6200EA),

                )

            Spacer(modifier = Modifier.height(16.dp))

            if (measurements.isEmpty()) {
                Text(
                    "No measurements found.",
                    modifier = Modifier.offset(16.dp)
                )
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
}
