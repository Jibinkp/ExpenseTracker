package com.example.expensetracker.ui.transaction

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.database.entities.TransactionsItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: ExpenseTrackerRepository) : ViewModel() {
    fun getTransaction() = repository.getTransaction()

    fun upsertTransaction(item: TransactionsItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsertTransaction(item)
    }

    fun deleteTransaction(item: TransactionsItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteTransaction(item)
    }
}