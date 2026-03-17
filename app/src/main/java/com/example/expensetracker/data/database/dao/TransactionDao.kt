package com.example.expensetracker.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.expensetracker.data.Constants
import com.example.expensetracker.data.database.entities.BudgetItem
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.ExpenseItem
import com.example.expensetracker.data.database.entities.ExpenseWithCategory
import com.example.expensetracker.data.database.entities.GoalItem
import com.example.expensetracker.data.database.entities.IncomeItem
import com.example.expensetracker.data.database.entities.ReminderItem
import com.example.expensetracker.data.database.entities.TransactionsItem

@Dao
interface TransactionDao {
    @Upsert
    suspend fun upsertCategory(item: CategoryItem)

    @Delete
    suspend fun deleteCategory(item: CategoryItem)

    @Query("SELECT * FROM ${Constants.CATEGORY_TABLE_NAME} ORDER BY ${Constants.CATEGORY_ID} DESC")
    fun getCategories(): LiveData<List<CategoryItem>>

    @Query("SELECT * FROM ${Constants.CATEGORY_TABLE_NAME} ORDER BY ${Constants.CATEGORY_ID} DESC LIMIT ${Constants.LIST_DEFAULT_LIMIT}")
    fun getLastFiveCategories(): LiveData<List<CategoryItem>>

    @Upsert
    suspend fun upsertTransaction(item: TransactionsItem)

    @Delete
    suspend fun deleteTransaction(item: TransactionsItem)

    @Query("SELECT * FROM ${Constants.TRANSACTION_TABLE_NAME} ORDER BY ${Constants.TRANSACTION_ID} DESC")
    fun getTransactions(): LiveData<List<TransactionsItem>>

    @Query("SELECT * FROM ${Constants.TRANSACTION_TABLE_NAME} ORDER BY ${Constants.TRANSACTION_ID} DESC LIMIT ${Constants.LIST_DEFAULT_LIMIT}")
    fun getLastFiveTransactions(): LiveData<List<TransactionsItem>>

    @Query("SELECT SUM(${Constants.TRANSACTION_AMOUNT}) FROM ${Constants.TRANSACTION_TABLE_NAME} WHERE ${Constants.TRANSACTION_TYPE} = 'INCOME'")
    fun getIncomeSum(): LiveData<Double?>

    @Query("SELECT SUM(${Constants.TRANSACTION_AMOUNT}) FROM ${Constants.TRANSACTION_TABLE_NAME} WHERE ${Constants.TRANSACTION_TYPE} = 'EXPENSE'")
    fun getExpenseSum(): LiveData<Double?>

    @Upsert
    suspend fun upsertBudget(item: BudgetItem)

    @Delete
    suspend fun deleteBudget(item: BudgetItem)

    @Query("SELECT * FROM ${Constants.BUDGET_TABLE_NAME} WHERE ${Constants.BUDGET_STATUS} = 'ACTIVE' ORDER BY ${Constants.BUDGET_ID} DESC")
    fun getBudgets(): LiveData<List<BudgetItem>>

    @Upsert
    suspend fun upsertGoal(item: GoalItem)

    @Delete
    suspend fun deleteGoal(item: GoalItem)

    @Query("SELECT * FROM ${Constants.GOAL_TABLE_NAME} WHERE ${Constants.GOAL_STATUS} = 'ACTIVE' ORDER BY ${Constants.GOAL_DEAD_LINE} DESC")
    fun getGoals(): LiveData<List<GoalItem>>

    @Upsert
    suspend fun upsertReminder(item: ReminderItem)

    @Delete
    suspend fun deleteReminder(item: ReminderItem)

    @Query("SELECT * FROM ${Constants.REMINDER_TABLE_NAME} WHERE ${Constants.REMINDER_STATUS} = 'ACTIVE' ORDER BY ${Constants.REMINDER_DATE} DESC")
    fun getReminders(): LiveData<List<ReminderItem>>

    // INCOME
    @Upsert
    suspend fun upsertIncome(incomeItem: IncomeItem)

    @Delete
    suspend fun deleteIncome(incomeItem: IncomeItem)

    @Query("SELECT * FROM income WHERE is_archived = '0' AND strftime('%Y-%m', date) = strftime('%Y-%m', 'now') ORDER BY date DESC")
    fun getIncomes(): LiveData<List<IncomeItem>>

    @Query("SELECT SUM(amount) FROM income WHERE is_archived = '0' AND strftime('%Y-%m', date) = :selectedDate")
    fun getCurrentMonthIncomeSum(selectedDate: String): LiveData<Double?>

    @Query("SELECT SUM(amount) FROM expense WHERE is_archived = '0' AND strftime('%Y-%m', date) = :selectedDate")
    fun getExpenseSum(selectedDate: String): LiveData<Double?>

    // EXPENSE
    @Upsert
    suspend fun upsertExpense(expenseItem: ExpenseItem)

    @Delete
    suspend fun deleteExpense(expenseItem: ExpenseItem)

    @Transaction
    @Query("SELECT * FROM expense WHERE is_archived = '0' AND strftime('%Y-%m', date) = strftime('%Y-%m', 'now') ORDER BY date DESC")
    fun getExpenses(): LiveData<List<ExpenseWithCategory>>

    // BUDGET
    @Query(
        """
    SELECT SUM(e.amount) 
    FROM expense e
    INNER JOIN categories c ON e.category_id = c.id
    WHERE c.primary_type = 'NEEDS'
    AND strftime('%Y-%m', e.date) = :month
    AND e.is_archived = 0
    """
    )
    fun getNeedsSpend(month: String): LiveData<Double?>

    @Query(
        """
    SELECT SUM(e.amount) 
    FROM expense e
    INNER JOIN categories c ON e.category_id = c.id
    WHERE c.primary_type = 'WANTS'
    AND strftime('%Y-%m', e.date) = :month
    AND e.is_archived = 0
    """
    )
    fun getWantsSpend(month: String): LiveData<Double?>

    @Query(
        """
    SELECT SUM(e.amount) 
    FROM expense e
    INNER JOIN categories c ON e.category_id = c.id
    WHERE c.primary_type = 'SAVINGS'
    AND strftime('%Y-%m', e.date) = :month
    AND e.is_archived = 0
    """
    )
    fun getSavingsSpend(month: String): LiveData<Double?>
}