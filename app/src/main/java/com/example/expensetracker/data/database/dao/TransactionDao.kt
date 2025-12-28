package com.example.expensetracker.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.TransactionsItem

@Dao
interface TransactionDao {

    // ---- Category ----
    @Upsert
    suspend fun upsertCategory(item: CategoryItem)

    @Delete
    suspend fun deleteCategory(item: CategoryItem)

    @Query("SELECT * FROM categories ORDER BY id ASC")
    fun getCategories(): LiveData<List<CategoryItem>>

    // ---- Transaction ----
    @Upsert
    suspend fun upsertTransaction(item: TransactionsItem)

    @Delete
    suspend fun deleteTransaction(item: TransactionsItem)

    @Query("SELECT * FROM transactions ORDER BY id ASC")
    fun getTransactions(): LiveData<List<TransactionsItem>>


}