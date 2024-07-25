package com.example.salarydivsion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salarydivsion.model.Division
import com.example.salarydivsion.service.DivisionSetting
import com.example.salarydivsion.ui.theme.SalaryDivsionTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SalaryDivsionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    SalaryDivision()
                }
            }
        }
    }
}

@Composable
fun SalaryDivision() {
    val divisionSetting = remember { DivisionSetting() }
    val salary = remember { mutableStateOf("") }
    val divisionResult = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Salary Division",
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.size(16.dp))
        SalaryInput(salary, errorMessage)

        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                val salaryValue = salary.value.toDoubleOrNull()
                if (salaryValue == null || salaryValue <= 0) {
                    errorMessage.value = "Please enter a valid salary."
                    divisionResult.value = ""
                } else {
                    errorMessage.value = ""
                    val amounts = divisionSetting.getDivisions().map { division ->
                        val amount = salaryValue * division.percentage
                        "${division.name}: ${"%.2f".format(amount)}"
                    }
                    divisionResult.value = amounts.joinToString(separator = "\n")
                }
            }
        ) {
            Text(text = "Calculate")
        }

        Spacer(modifier = Modifier.size(16.dp))
        if (errorMessage.value.isNotEmpty()) {
            Text(text = errorMessage.value, color = Color.Red)
        } else {
            DivisionResult(divisionResult)
        }
    }
}

@Composable
fun SalaryInput(salary: MutableState<String>, errorMessage: MutableState<String>) {
    TextField(
        value = salary.value,
        onValueChange = { newValue ->
            salary.value = newValue.replace(",", ".")
            errorMessage.value = ""
        },
        label = { Text(text = "Enter your salary") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = errorMessage.value.isNotEmpty()
    )
}

@Composable
fun DivisionResult(divisionResult: MutableState<String>) {
    Text(
        text = divisionResult.value,
        style = TextStyle(
            color = Color.LightGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SalaryDivisionPreview() {
    SalaryDivsionTheme {
        SalaryDivision()
    }
}
