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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Salary Division",
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )

        val salary = remember { mutableStateOf("") }
        val divisionResult = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = salary.value,
            onValueChange = { newValue ->
                salary.value = newValue.replace(",",".")
            },
            label = { Text(text = "Enter your salary") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                val salaryValue = salary.value.toDoubleOrNull() ?: 0.0
                val investments = salaryValue * 0.2
                val home = salaryValue * 0.1
                val studies = salaryValue * 0.3

                divisionResult.value = """
                    |Investments: $investments
                    |Home: $home
                    |Studies: $studies
                    """.trimMargin()
            }
        ) {
            Text(text = "Calculate")
        }

        Spacer(modifier = Modifier.size(16.dp))
        Text(text = divisionResult.value)

    }
}

@Preview(showBackground = true)
@Composable
fun SalaryDivisionPreview() {
    SalaryDivsionTheme {
        SalaryDivision()
    }
}