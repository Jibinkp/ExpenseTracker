package com.example.expensetracker.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.expensetracker.data.database.entities.BudgetItem
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.TransactionsItem

@Dao
interface TransactionDao {

    // CATEGORY
    @Upsert
    suspend fun upsertCategory(item: CategoryItem)

    @Delete
    suspend fun deleteCategory(item: CategoryItem)

    @Query("SELECT * FROM categories ORDER BY id DESC")
    fun getCategories(): LiveData<List<CategoryItem>>

    @Query("SELECT * FROM categories ORDER BY id DESC LIMIT 3")
    fun getLastFiveCategories(): LiveData<List<CategoryItem>>
    // END

    // TRANSACTION
    @Upsert
    suspend fun upsertTransaction(item: TransactionsItem)

    @Delete
    suspend fun deleteTransaction(item: TransactionsItem)

    @Query("SELECT * FROM transactions ORDER BY id DESC")
    fun getTransactions(): LiveData<List<TransactionsItem>>

    @Query("SELECT * FROM transactions ORDER BY id DESC LIMIT 3")
    fun getLastFiveTransactions(): LiveData<List<TransactionsItem>>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Income'")
    fun getIncomeSum(): LiveData<Double?>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Expense'")
    fun getExpenseSum(): LiveData<Double?>
    // END

    // BUDGET
    @Upsert
    suspend fun upsertBudget(budgetItem: BudgetItem)

}