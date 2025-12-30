package com.example.expensetracker.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository

@Suppress("UNCHECKED_CAST")
class CategoryViewModelFactory(private val repository: ExpenseTrackerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(repository) as T
    }
}