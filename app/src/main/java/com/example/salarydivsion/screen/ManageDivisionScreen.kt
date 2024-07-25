package com.example.salarydivsion.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.salarydivsion.model.Division
import com.example.salarydivsion.view.MainViewModel
import com.example.salarydivsion.view.Screen

@Composable
fun ManageDivisionsScreen(viewModel: MainViewModel) {
    val divisions by remember { mutableStateOf(viewModel.getDivisions()) }
    val newDivisionName = remember { mutableStateOf("") }
    val newDivisionPercentage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Manage Divisions", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        divisions.forEach { division ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(division.name)
                Text("${division.percentage * 100}%")
                IconButton(onClick = { viewModel.removeDivision(division) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newDivisionName.value,
            onValueChange = { newDivisionName.value = it },
            label = { Text("Division Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = newDivisionPercentage.value,
            onValueChange = { newDivisionPercentage.value = it },
            label = { Text("Division Percentage") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val name = newDivisionName.value
            val percentage = newDivisionPercentage.value.toDoubleOrNull()
            if (name.isNotBlank() && percentage != null) {
                viewModel.addDivision(Division(name, percentage / 100))
                newDivisionName.value = ""
                newDivisionPercentage.value = ""
            }
        }) {
            Text("Add Division")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.navigateTo(Screen.Main) }) {
            Text("Back")
        }
    }
}
