package com.example.v2wszystkowjednym


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.v2wszystkowjednym.ui.theme.V2WszystkoWJednymTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            V2WszystkoWJednymTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun StartScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background1),
            contentDescription = "blue background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }

    Column(
        modifier = Modifier.padding(all = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.welcome_text),
            style = TextStyle(
                fontSize = 30.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 3.sp
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.choice_text),
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { navController.navigate("bmi")}) {
                Text(text = stringResource(R.string.bmi_button),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { navController.navigate("measurement")}) {
                Text(text = stringResource(R.string.measurement_button),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { navController.navigate("measurements_list")}) {
                Text(text = stringResource(R.string.measurement_list_button),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }


    }

}
