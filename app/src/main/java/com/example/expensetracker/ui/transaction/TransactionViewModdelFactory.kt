package com.example.expensetracker.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository

@Suppress("UNCHECKED_CAST")
class TransactionViewModelFactory(private val repository: ExpenseTrackerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionViewModel(repository) as T
    }
}