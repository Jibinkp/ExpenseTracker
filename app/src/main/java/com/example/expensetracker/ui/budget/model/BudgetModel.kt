package com.example.expensetracker.ui.budget.model

data class BudgetModel(
    val type: String,
    val total: Double,
    val spend: Double,
    val remaining: Double
)
