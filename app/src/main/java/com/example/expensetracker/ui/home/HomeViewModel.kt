package com.example.expensetracker.ui.home

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository

class HomeViewModel(
    private val repository: ExpenseTrackerRepository
) : ViewModel() {

    fun getIncomeSum() = repository.getIncomeSum()

    fun getExpenseSum() = repository.getExpenseSum()

    fun getLastFiveCategories() = repository.getLastFiveCategories()

    fun getLastFiveTransactions() = repository.getLastFiveTransactions()
}