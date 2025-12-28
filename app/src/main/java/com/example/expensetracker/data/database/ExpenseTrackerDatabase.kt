package com.example.expensetracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expensetracker.data.Constants
import com.example.expensetracker.data.database.dao.TransactionDao
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.TransactionsItem

@Database(
    entities = [CategoryItem::class, TransactionsItem::class],
    version = 1
)
abstract class ExpenseTrackerDatabase : RoomDatabase() {

    abstract fun getTransactionDao(): TransactionDao

    companion object {

        @Volatile
        private var instance: ExpenseTrackerDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ExpenseTrackerDatabase::class.java,
                name = Constants.DATABASE
            ).build()

    }
}