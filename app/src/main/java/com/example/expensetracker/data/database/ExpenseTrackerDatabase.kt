package com.example.expensetracker.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensetracker.data.Constants
import com.example.expensetracker.data.database.dao.TransactionDao
import com.example.expensetracker.data.database.entities.BudgetItem
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.GoalItem
import com.example.expensetracker.data.database.entities.ReminderItem
import com.example.expensetracker.data.database.entities.TransactionsItem

@Database(
    entities = [CategoryItem::class, TransactionsItem::class, BudgetItem::class, GoalItem::class, ReminderItem::class],
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

    /*val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE users ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
        }
    }*/
}