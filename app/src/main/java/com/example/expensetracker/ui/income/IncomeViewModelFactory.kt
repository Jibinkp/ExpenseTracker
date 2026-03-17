package com.example.expensetracker.ui.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository

@Suppress("UNCHECKED_CAST")
class IncomeViewModelFactory(private val repository: ExpenseTrackerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return IncomeViewModel(repository) as T
    }
}