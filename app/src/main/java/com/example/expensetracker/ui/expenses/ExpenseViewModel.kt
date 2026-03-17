package com.example.expensetracker.ui.expenses

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.database.entities.ExpenseItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseTrackerRepository) : ViewModel() {

    fun getExpenses() = repository.getExpenses()

    fun upsertExpense(expenseItem: ExpenseItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsertExpense(expenseItem)
    }

    fun deleteExpense(expenseItem: ExpenseItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteExpense(expenseItem)
    }

    fun getCategories() = repository.getCategory()
}