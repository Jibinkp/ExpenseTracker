package com.example.expensetracker.data

object PrimaryTypes {
    const val NEEDS = "NEEDS"
    const val WANTS = "WANTS"
    const val SAVINGS = "SAVINGS"

    fun getAll(): List<String> {
        return listOf(NEEDS, WANTS, SAVINGS)
    }
}