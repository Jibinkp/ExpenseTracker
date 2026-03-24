package com.example.expensetracker.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensetracker.data.Constants
import com.example.expensetracker.data.PrimaryTypes
import com.example.expensetracker.data.database.dao.TransactionDao
import com.example.expensetracker.data.database.entities.BudgetItem
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.ExpenseItem
import com.example.expensetracker.data.database.entities.GoalItem
import com.example.expensetracker.data.database.entities.IncomeItem
import com.example.expensetracker.data.database.entities.ReminderItem
import com.example.expensetracker.data.database.entities.TransactionsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [CategoryItem::class, TransactionsItem::class, BudgetItem::class, GoalItem::class, ReminderItem::class, IncomeItem::class, ExpenseItem::class],
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
            )
                .addCallback(roomCallback(context))
                .build()

        private fun roomCallback(context: Context) =
            object : RoomDatabase.Callback() {

                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        instance?.let {
                            seedDatabase(it.getTransactionDao())
                        }
                    }
                }
            }

        suspend fun seedDatabase(dao: TransactionDao) {
            if (dao.getCategoryCount() > 0) return

            val defaultCategories = listOf(
                CategoryItem("Rent", PrimaryTypes.NEEDS,null),
                CategoryItem("Groceries", PrimaryTypes.NEEDS,null),
                CategoryItem("Utilities", PrimaryTypes.NEEDS,null),
                CategoryItem("Transport", PrimaryTypes.NEEDS,null),
                CategoryItem("Medicine", PrimaryTypes.NEEDS,null),
                CategoryItem("Insurance", PrimaryTypes.NEEDS,null),

                CategoryItem("Dining", PrimaryTypes.WANTS,null),
                CategoryItem("Entertainment", PrimaryTypes.WANTS,null),
                CategoryItem("Shopping", PrimaryTypes.WANTS,null),
                CategoryItem("Subscription", PrimaryTypes.WANTS,null),
                CategoryItem("Travel", PrimaryTypes.WANTS,null),

                CategoryItem("Emergency Fund", PrimaryTypes.SAVINGS,null),
                CategoryItem("Investments", PrimaryTypes.SAVINGS,null),
                CategoryItem("SIP", PrimaryTypes.SAVINGS,null),
                CategoryItem("Dept Repayment", PrimaryTypes.SAVINGS,null),
            )
            dao.upsertCategories(defaultCategories)
        }
    }

    /*val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE users ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
        }
    }*/
}