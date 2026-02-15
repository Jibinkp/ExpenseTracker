package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.Constants

@Entity(tableName = Constants.BUDGET_TABLE_NAME)
data class BudgetItem(
    @ColumnInfo(name = Constants.BUDGET_AMOUNT)
    var budgetAmount: Double,
    @ColumnInfo(name = Constants.BUDGET_NOTE)
    var budgetNote: String,
    @ColumnInfo(name = Constants.BUDGET_START_DATE)
    var budgetStartDate: String,
    @ColumnInfo(name = Constants.BUDGET_END_DATE)
    var budgetEndDate: String,
    @ColumnInfo(name = Constants.BUDGET_STATUS)
    var budgetStatus: String,
    @ColumnInfo(name = Constants.BUDGET_ADDED_DATE)
    var budgetAddedDate: String,
    @ColumnInfo(name = Constants.BUDGET_UPDATED_DATE)
    var budgetUpdatedDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
