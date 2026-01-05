package com.example.expensetracker.ui.home

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ExpenseTrackerRepository,
) : ViewModel() {

    fun getIncomeSum() = repository.getIncomeSum()

    fun getExpenseSum() = repository.getExpenseSum()

    fun getLastFiveCategories() = repository.getLastFiveCategories()

    fun getLastFiveTransactions() = repository.getLastFiveTransactions()

    fun deleteCategory(item: CategoryItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteCategory(item)
    }
}