package com.example.salarydivsion.service

import com.example.salarydivsion.model.Division

class DivisionSetting {
    private val divisions = mutableListOf<Division>()

    init {
        divisions.add(Division("Investments", 0.2))
        divisions.add(Division("Home", 0.1))
        divisions.add(Division("Studies", 0.2))
    }

    fun addDivision(division: Division) {
        divisions.add(division)
    }

    fun removeDivision(division: Division) {
        divisions.remove(division)
    }

    fun updateDivision(division: Division) {
        val index = divisions.indexOf(division)
        if (index != -1) {
            divisions[index] = division
        }
    }

    fun getDivisions(): List<Division> {
        return divisions
    }
}