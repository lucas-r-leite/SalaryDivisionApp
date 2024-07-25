package com.example.salarydivsion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salarydivsion.model.Division
import com.example.salarydivsion.view.MainViewModel
import com.example.salarydivsion.view.Screen

@Composable
fun ManageDivisionsScreen(viewModel: MainViewModel) {
    val divisionSetting = viewModel.divisionSetting
    val divisionName = remember { mutableStateOf("") }
    val divisionPercentage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Manage Divisions", fontSize = 24.sp)

        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = divisionName.value,
            onValueChange = { divisionName.value = it },
            label = { Text(text = "Division Name") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = divisionPercentage.value,
            onValueChange = { divisionPercentage.value = it },
            label = { Text(text = "Division Percentage") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                val name = divisionName.value
                val percentage = divisionPercentage.value.toDoubleOrNull() ?: 0.0
                if (name.isNotEmpty() && percentage > 0) {
                    divisionSetting.addDivision(Division(name, percentage / 100))
                    divisionName.value = ""
                    divisionPercentage.value = ""
                }
            }
        ) {
            Text(text = "Add Division")
        }

        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { viewModel.navigateTo(Screen.Main) }) {
            Text(text = "Back to Main")
        }

        Spacer(modifier = Modifier.size(16.dp))
        divisionSetting.getDivisions().forEach { division ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${division.name}: ${"%.2f".format(division.percentage * 100)}%")
                Row {
                    Button(onClick = {
                        divisionSetting.removeDivision(division)
                    }) {
                        Text(text = "Remove")
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Button(onClick = {
                        // Update functionality can be added here
                    }) {
                        Text(text = "Update")
                    }
                }
            }
        }
    }
}