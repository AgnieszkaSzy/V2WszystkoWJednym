import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.v2wszystkowjednym.AppNavHost
import com.example.v2wszystkowjednym.DatabaseHelper
import com.example.v2wszystkowjednym.R
import com.example.v2wszystkowjednym.ui.theme.V2WszystkoWJednymTheme
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun BmiScreen(navController: NavHostController, dbHelper: DatabaseHelper) {
    var gender by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .testTag("BmiScreen")
    ) {
        val (bmiInfoText, genderText, genderGroup, weightText, weightInput, heightText, heightInput, calculateButton, bmiResultText, clearDataButton, errorText) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.background1),
            contentDescription = "blue background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        IconButton(onClick = {navController.navigate("start")}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back arrow",
                modifier = Modifier
                    .size(30.dp)
                    .offset(6.dp, 6.dp)
                    .testTag("BackArrow")
            )
        }

        Text(
            text = stringResource(R.string.bmi_header),
            fontSize = 24.sp,
            color = Color(0xFF6200EA),
            modifier = Modifier
                .constrainAs(bmiInfoText) {
                top.linkTo(parent.top, margin = 46.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                .testTag("BmiCalculatorText")
        )

        Text(
            text = stringResource(R.string.gender_text),
            fontSize = 20.sp,
            color = Color(0xFF6200EA),
            modifier = Modifier
                .constrainAs(genderText) {
                top.linkTo(bmiInfoText.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
                .testTag("GenderText")
        )

        Row(
            modifier = Modifier
                .constrainAs(genderGroup) {
                    top.linkTo(genderText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                }
                .testTag("GenderGroup")
        ) {
            RadioButton(
                selected = gender == "Female",
                onClick = { gender = "Female" },
                modifier = Modifier.testTag("FemaleButton")
            )
            Text(
                stringResource(R.string.female_radiobutton_text), modifier = Modifier
                .testTag("FemaleButtonText")
                .align(Alignment.CenterVertically))

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = gender == "Male",
                onClick = { gender = "Male" },
                modifier = Modifier.testTag("MaleButton")
            )
            Text(
                stringResource(R.string.male_radiobutton_text), modifier = Modifier
                .testTag("MaleButtonText")
                .align(Alignment.CenterVertically))
        }

        Text(
            text = "Weight (kg)",
            fontSize = 20.sp,
            color = Color(0xFF6200EA),
            modifier = Modifier.constrainAs(weightText) {
                top.linkTo(genderGroup.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
                .testTag("WeightText")
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.constrainAs(weightInput) {
                top.linkTo(weightText.bottom, margin = 8.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end)
            }
                .testTag("WeightInput")
        )

        Text(
            text = "Height (cm)",
            fontSize = 20.sp,
            color = Color(0xFF6200EA),
            modifier = Modifier.constrainAs(heightText) {
                top.linkTo(weightInput.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
                .testTag("HeightText")
        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.constrainAs(heightInput) {
                top.linkTo(heightText.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                .testTag("HeightInput")
        )

        Button(
            onClick = {
                if ((gender == "Female" || gender == "Male") && weight.isNotEmpty() && height.isNotEmpty()) {
                    showError = false
                    val weightValue = weight.toDouble()
                    val heightValue = height.toDouble()
                    val bmi = calculateBMI(weightValue, heightValue, navController)
                    bmiResult = "Twoje BMI to: $bmi"
                } else {
                    showError = true
                }
            },
            modifier = Modifier.constrainAs(calculateButton) {
                top.linkTo(heightInput.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                .testTag("CalculateButton")
        ) {
            Text(text = "Calculate BMI")
        }

        if (showError) {
            Text(
                text = "Please fill all fields correctly",
                color = Color.Red,
                modifier = Modifier.constrainAs(errorText) {
                    top.linkTo(calculateButton.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                    .testTag("ErrorText")
            )
        }

        Text(
            text = bmiResult,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.constrainAs(bmiResultText) {
                top.linkTo(if (showError) errorText.bottom else calculateButton.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                .testTag("BmiResult")
        )

        Button(
            onClick = {
                gender = ""
                weight = ""
                height = ""
                bmiResult = ""
                showError = false
            },
            modifier = Modifier.constrainAs(clearDataButton) {
                top.linkTo(bmiResultText.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                .testTag("ClearDataButton")
        ) {
            Text(text = "Clear Data")
        }
    }
}

private fun calculateBMI(weight: Double, height: Double, navController: NavHostController): BigDecimal {
    return BigDecimal(weight / (height * height / 10000)).setScale(2, RoundingMode.HALF_UP)
}

@Preview(showBackground = true)
@Composable
fun BmiScreenPreview() {
    val navController = rememberNavController()
    val dbHelper = DatabaseHelper(navController.context)

    BmiScreen(navController = navController, dbHelper = dbHelper)
}
