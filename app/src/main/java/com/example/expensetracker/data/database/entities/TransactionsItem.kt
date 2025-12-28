package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.Constants

@Entity(tableName = Constants.TRANSACTION_TABLE_NAME)
data class TransactionsItem(
    @ColumnInfo(name = Constants.TRANSACTION_AMOUNT)
    var amount: Double,
    @ColumnInfo(name = Constants.TRANSACTION_CATEGORY_ID)
    var categoryId: Int,
    @ColumnInfo(name = Constants.TRANSACTION_NOTE)
    var note: String,
    @ColumnInfo(name = Constants.TRANSACTION_DATE)
    var date: String,
    @ColumnInfo(name = Constants.TRANSACTION_TYPE)
    var type: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
