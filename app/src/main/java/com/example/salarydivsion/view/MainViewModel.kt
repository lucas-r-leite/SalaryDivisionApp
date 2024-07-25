package com.example.salarydivsion.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.salarydivsion.service.DivisionSetting

enum class Screen {
    Main, ManageDivisions
}

class MainViewModel : ViewModel() {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Main)
        private set

    val divisionSetting = DivisionSetting()

    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }
}
