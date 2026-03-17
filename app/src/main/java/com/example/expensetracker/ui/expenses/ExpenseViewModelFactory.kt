package com.example.expensetracker.ui.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository

@Suppress("UNCHECKED_CAST")
class ExpenseViewModelFactory(private val repository: ExpenseTrackerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpenseViewModel(repository) as T
    }
}