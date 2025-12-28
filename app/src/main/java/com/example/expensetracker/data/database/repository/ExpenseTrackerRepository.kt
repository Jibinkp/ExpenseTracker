package com.example.expensetracker.data.database.repository

import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.TransactionsItem

class ExpenseTrackerRepository(
    private val expenseTrackerDatabase: ExpenseTrackerDatabase
) {

    suspend fun upsertCategory(item: CategoryItem) =
        expenseTrackerDatabase.getTransactionDao().upsertCategory(item)

    suspend fun deleteCategory(item: CategoryItem) =
        expenseTrackerDatabase.getTransactionDao().deleteCategory(item)

    fun getCategory() = expenseTrackerDatabase.getTransactionDao().getCategories()

    suspend fun upsertTransaction(item: TransactionsItem) =
        expenseTrackerDatabase.getTransactionDao().upsertTransaction(item)

    suspend fun deleteTransaction(item: TransactionsItem) =
        expenseTrackerDatabase.getTransactionDao().deleteTransaction(item)

    fun getTransaction() = expenseTrackerDatabase.getTransactionDao().getTransactions()

    fun getLastFiveCategories() =
        expenseTrackerDatabase.getTransactionDao().getLastFiveCategories()

    fun getLastFiveTransactions() =
        expenseTrackerDatabase.getTransactionDao().getLastFiveTransactions()

    fun getIncomeSum() = expenseTrackerDatabase.getTransactionDao().getIncomeSum()

    fun getExpenseSum() = expenseTrackerDatabase.getTransactionDao().getExpenseSum()

}