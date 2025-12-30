package com.example.expensetracker.ui.category

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: ExpenseTrackerRepository
) : ViewModel() {

    fun getCategory() = repository.getCategory()

    fun upsertCategory(item: CategoryItem) = CoroutineScope(Dispatchers.IO).launch{
        repository.upsertCategory(item)
    }

    fun deleteCategory(item: CategoryItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteCategory(item)
    }
}