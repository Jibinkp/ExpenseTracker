package com.example.expensetracker.ui.income

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.database.entities.IncomeItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel(private val repository: ExpenseTrackerRepository) : ViewModel() {

    fun getIncomes() = repository.getIncomes()

    fun upsertIncome(incomeItem: IncomeItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsertIncome(incomeItem)
    }

    fun deleteIncome(incomeItem: IncomeItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteIncome(incomeItem)
    }

    fun getSumOfCurrentMonthIncome(selectedDate: String) = repository.getSumOfCurrentMonthIncome(selectedDate)
}