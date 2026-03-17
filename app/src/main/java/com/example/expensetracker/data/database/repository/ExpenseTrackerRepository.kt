package com.example.expensetracker.data.database.repository

import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.ExpenseItem
import com.example.expensetracker.data.database.entities.IncomeItem
import com.example.expensetracker.data.database.entities.TransactionsItem

class ExpenseTrackerRepository(
    private val expenseTrackerDatabase: ExpenseTrackerDatabase
) {

    suspend fun upsertCategory(item: CategoryItem) =
        expenseTrackerDatabase.getTransactionDao().upsertCategory(item)

    suspend fun deleteCategory(item: CategoryItem) =
        expenseTrackerDatabase.getTransactionDao().deleteCategory(item)

    fun getCategory() = expenseTrackerDatabase.getTransactionDao().getCategories()

    // INCOME
    fun getIncomes() = expenseTrackerDatabase.getTransactionDao().getIncomes()

    suspend fun upsertIncome(incomeItem: IncomeItem) =
        expenseTrackerDatabase.getTransactionDao().upsertIncome(incomeItem)

    suspend fun deleteIncome(incomeItem: IncomeItem) =
        expenseTrackerDatabase.getTransactionDao().deleteIncome(incomeItem)

    fun getSumOfCurrentMonthIncome(selectedDate: String) =
        expenseTrackerDatabase.getTransactionDao().getCurrentMonthIncomeSum(selectedDate)

    fun getSumOfExpense(selectedDate: String) =
        expenseTrackerDatabase.getTransactionDao().getExpenseSum(selectedDate)

    // EXPENSE
    fun getExpenses() = expenseTrackerDatabase.getTransactionDao().getExpenses()

    suspend fun upsertExpense(expenseItem: ExpenseItem) =
        expenseTrackerDatabase.getTransactionDao().upsertExpense(expenseItem)

    suspend fun deleteExpense(expenseItem: ExpenseItem) =
        expenseTrackerDatabase.getTransactionDao().deleteExpense(expenseItem)

    // BUDGET
    fun getNeedsSpend(month: String) =
        expenseTrackerDatabase.getTransactionDao().getNeedsSpend(month)

    fun getWantsSpend(month: String) =
        expenseTrackerDatabase.getTransactionDao().getWantsSpend(month)

    fun getSavingsSpend(month: String) =
        expenseTrackerDatabase.getTransactionDao().getSavingsSpend(month)
}